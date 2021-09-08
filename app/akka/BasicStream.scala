package akka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.{Done, NotUsed}
//import java.util.concurrent.Future
import scala.concurrent.Future

/**
 * Question about Akka Stream framework https://doc.akka.io/docs/akka/current/stream/index.html
 *
 * Complete the code (replace the ???)
 */
object BasicStream {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("ActorSystem")

    val numbers = 1 to 1000

    val numberSource: Source[Int, NotUsed] = Source.fromIterator(() => numbers.iterator)

    //Only let pass even number through the flow
    val isEvenFlow: Flow[Int, Int, NotUsed] = Flow[Int].filter(n => n%2 == 0)

    //Create a Source of even numbers by combining the number Source with the even Flow
    val evenNumberSource: Source[Int, NotUsed] = numberSource.via(isEvenFlow)

    //A Sink that will write its input onto the console
    val consoleSink: Sink[Int, Future[Done]] = Sink.foreach[Int](println(_))

    //Connect the Source with the Sink and run it
    val sum: Future[Done] = evenNumberSource.runWith(consoleSink)
  }
}
