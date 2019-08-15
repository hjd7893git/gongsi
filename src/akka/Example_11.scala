package akka

import akka.actor.ActorRef

/**
  * Created by Administrator on 2018/9/25.
  */
/**
  * 消息处理：!(Fire-Forget)
  */
object Example_11 extends App {

  import akka.actor.Actor
  import akka.actor.Props
  import akka.event.Logging
  import akka.actor.ActorSystem

  //定义几种不同的消息
  case class Start(var msg: String)

  case class Run(var msg: String)

  case class Run1(var msg: String)

  case class Run2(var msg: String)

  case class Stop(var msg: String)

  class ExampleActor extends Actor {
    val other = context.actorOf(Props[OtherActor], "OtherActor")
    val log = Logging(context.system, this)
    println(">>>>>>>w:"+sender())

    def receive = {
      case Start(msg) =>{
        println(">>>>>>>n:"+sender())
        other ! msg
      }
      case Run(msg) => other.tell(msg, self)
      case Run1(msg) => other.tell(msg, sender())
      case Run2(msg) => other.tell(msg, null)
      case _ => log.info("NO")
    }
  }

  class OtherActor extends Actor {
    val log = Logging(context.system, this)
    println(">>>>>>>2w:"+sender())
    def receive = {
      case s: String =>{
        println(">>>>>>>2n:"+sender())
        log.info("received message:\n" + s)
      }
      case _ ⇒ log.info("received unknown message")
    }
  }

  val system = ActorSystem("MessageProcessingSystem")
  val exampleActor = system.actorOf(Props[ExampleActor], name = "ExampleActor")
  exampleActor ! Run("Running")
  exampleActor ! Run1("Running1")
  exampleActor ! Run2("Running1")
  exampleActor ! Start("Starting")
  system.shutdown()
}
