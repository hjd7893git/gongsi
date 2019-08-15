package akka

import akka.actor.{ActorLogging, ActorRef}

/**
  * Created by Administrator on 2018/9/25.
  * 停止Actor的运行
  */
object Example_10 extends App {

  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.Props
  import akka.actor.PoisonPill

  class FirstActor extends Actor with ActorLogging {

    var child: ActorRef = context.actorOf(Props[MyActor], name = "myActor")

    def receive = {
      //向child发送PoisonPill停止其运行
      case "stop1" => context.stop(child) //停止所指定的actor
      case "stop2" => child ! PoisonPill  //停止所指定的actor
      case "stop3" => context.system.shutdown() //context.system获取根Actor，此为结束所有Actor至程序结束
      case x => {
        //向MyActor发送消息
        child ! x
        log.info("received " + x)
      }

    }

    override def postStop(): Unit = {
      log.info("postStop In FirstActor")
    }
  }

  class MyActor extends Actor with ActorLogging {
    def receive = {
      case "test" => log.info("received test");
      case _ => log.info("received unknown message");
    }

    override def postStop(): Unit = {
      log.info("postStop In MyActor")
    }
  }

  val system: ActorSystem = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向firstactor发送消息")
  //向firstactor发送消息
  firstactor ! "test"
  firstactor ! 123
  //  firstactor ! "stop1"
  firstactor ! "stop2"
//  firstactor ! "stop3"
  //  Thread.sleep(5000)
  //  system.stop(firstactor)
  //  system.shutdown() //停止所有 Actor的运行

}
