import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Created by Administrator on 2018/8/9.
  */
class ActorDemo extends Actor {
  override def receive: Receive = {
    case "HHH" => println("HHH")
    case _ =>
  }
}

object Demo extends App {
  val system = ActorSystem("AskDemoSystem")
  val myActor: ActorRef = system.actorOf(Props[ActorDemo], name = "myActor")
  myActor ! "HHH"
}
