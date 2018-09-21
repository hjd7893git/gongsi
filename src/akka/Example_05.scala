package akka

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

/**
  * Created by Administrator on 2018/9/20.
  * Actor生命周期
  */
object Example_05 extends App {


  class FirstActor extends Actor with ActorLogging {
    //通过context.actorOf方法创建Actor
    var child: ActorRef = _

    //Hook方法，preStart()，Actor启动之前调用，用于完成初始化工作
    override def preStart(): Unit = {
      log.info("preStart() in FirstActor")
      //通过context上下文创建Actor
      child = context.actorOf(Props[MyActor], name = "myChild")
    }

    def receive = {
      //向MyActor发送消息
      case x => child ! x; log.info("received " + x)
    }

    //Hook方法，postStop()，Actor停止之后调用 [等待子方法调用完之后执行]
    override def postStop(): Unit = {
      context.stop(child)
      log.info("postStop() in FirstActor")
    }
  }


  class MyActor extends Actor with ActorLogging {
    //Hook方法，preStart()，Actor启动之前调用，用于完成初始化工作
    override def preStart(): Unit = {
      log.info("preStart() in MyActor")
    }

    def receive = {
      case "test" => log.info("received test")
      case _ => log.info("received unknown message")
    }

    //Hook方法，postStop()，Actor停止之后调用
    override def postStop(): Unit = {
      log.info("postStop() in MyActor")
    }
  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")  //在建立父actor 的时候也把子actor建立完成了 异步

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息,一起发送的
  myactor ! "test"
  myactor ! 123
  myactor ! 123
  myactor ! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}