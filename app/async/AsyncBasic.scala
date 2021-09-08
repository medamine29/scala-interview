package async

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String) : Int = {

    var sum: Int = 0
    //declaring ws1 vars
    var ws1: Int = 0
    var optionWS1: Option[Int] = Some(0)

    //declaring ws2 vars
    var ws2: Int = 0
    var optionWS2: Either[String,Int] = Right(0)

    // ws1 handling

    Webservice1.call(id).onComplete({
      case Success(value) => {
          optionWS1= value
      }
      case Failure(exception) => {
        println("failed to receive webservice1 result")
      }
    })
    Await.ready(Webservice1.call(id),Duration.Zero)

    if(optionWS1.isDefined){
      ws1 = optionWS1.get
    } else {
      println("failed to get value from webservice1")
    }

    // ws2 handling
    Webservice2.call(id).onComplete({
      case Success(value) => {
        optionWS2= value
      }
      case Failure(exception) => {
        println("failed to receive webservice2 result")
      }
    })

    Await.ready(Webservice2.call(id),Duration.Zero)

     optionWS2 match {
       case Left(x) => println(x)
       case Right(x) => if(x<(Int.MaxValue-ws1)) {if(ws1!=0) sum = ws1 + x} else {println("sum is too large to be an int")}
     }

    return sum
  }

  def main(args: Array[String]): Unit = {
    println(compute("10"))
  }

}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }

}
