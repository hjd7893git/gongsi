package akka

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by Administrator on 2018/8/9.
  */
class ActorDemo extends Actor {
  println("模块初始化.....")
  override def receive: Receive = {
    case "HHH" => self ! "A"
    case "sender" => sender ! "A1"
    case "A" => println("printlnA")
    case _ => println("。。。。。。。")
  }
}

object Demo extends App {
  val system = ActorSystem("AskDemoSystem")
  val myActor: ActorRef = system.actorOf(Props[ActorDemo], name = "myActor")
  myActor ! "HHH"
  myActor ! "sender"
}



object Example_01 extends App {
  import akka.actor.{Actor, ActorSystem, Props}

  class MyActor extends Actor {
    val log = Logging(context.system, this)

    def receive = {
      case "test" => log.info("received test")
      case _      => log.info("received unknown message")
    }
  }
  //创建ActorSystem对象
  val system = ActorSystem("MyActorSystem")
  //返回ActorSystem的LoggingAdpater
  val systemLog=system.log
  //创建MyActor,指定actor名称为myactor
  val myactor = system.actorOf(Props[MyActor], name = "myactor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor!"test"
  myactor! 123

  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}