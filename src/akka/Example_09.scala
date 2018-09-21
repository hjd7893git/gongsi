package akka

import akka.actor.{ActorLogging, ActorRef}

/**
  * Created by Administrator on 2018/9/21.
  * ActorPath  及  actorSelection 应用
  * [创建路径]      [根据路径获取ActorRef]
  */
object Example_09 extends App {

  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.Props

  class FirstActor extends Actor with ActorLogging {
    //通过context.actorOf方法创建Actor
    var child: ActorRef = _

    override def preStart(): Unit = {
      log.info("preStart() in FirstActor")
      //通过context上下文创建Actor
      child = context.actorOf(Props[MyActor], name = "myActor")
    }

    def receive = {
      //向MyActor发送消息
      case x => child ! x; log.info("received " + x)
    }
  }

  class MyActor extends Actor with ActorLogging {
    def receive = {
      case "test" => log.info("received test");
      case _ => log.info("received unknown message");
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  //获取ActorPath
  val myActorPath = system.child("firstActor").child("myActor")
  systemLog.info("firstActorPath--->{}", myActorPath)


  //通过system.actorSelection方法获取ActorRef
  val myActor1 = system.actorSelection(myActorPath)

  //直接指定其路径
  val myActor2 = system.actorSelection("/user/firstActor/myActor")
  //使用相对路径
  val myActor3 = system.actorSelection("../firstActor/myActor")


  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  myActor1 ! "test"
  myActor1 ! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}

