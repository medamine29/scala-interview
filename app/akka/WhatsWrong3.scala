package akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 *
 * -> yes , multiple access to same resource simultaneously , a second call to mutate internalState can reach the actor while
 * the first one isn't done which will result in false value of internalState
 *
 * What would you do to fix it ?
 *
 * -> Implement a Semaphore system to control access to the resource "InternalState" , specifically a Semaphore
 * of type mutex to know if there's a call changing the resource and give permits only when the resource is free.
 *
 * Do not mind about the not implemented code
 */

class WhatsWrong3 extends Actor {

  var internalState = "internal state"

  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
