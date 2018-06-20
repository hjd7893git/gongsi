import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.concurrent.duration._

case object AskNameMessage

class TestActor extends Actor {
  def receive = {
    case AskNameMessage => // respond to the 'ask' request
      sender ! "Fred"
    case _ => println("that was unexpected")
  }
}
object AskDemo extends App{
  //create the system and actor
  val system = ActorSystem("AskDemoSystem")
  val myActor:ActorRef = system.actorOf(Props[TestActor], name="myActor")

  //阻塞
  // (1) this is one way to "ask" another actor for information
  implicit val timeout = Timeout(5 seconds)
  val future = myActor ? AskNameMessage
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(result)

  //非阻塞
  // (2) a slightly different way to ask another actor for information
  val future2: Future[String] = ask(myActor, AskNameMessage).mapTo[String]
  val result2 = Await.result(future2, 1 second)
  println(result2)

  system.shutdown
}