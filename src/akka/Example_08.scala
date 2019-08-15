package akka

import akka.actor.{ActorLogging, ActorRef}

/**
  * Created by Administrator on 2018/9/21.
  * context隐式对象获取父Actor和子Actor的ActorRef
  *
  * （1）通过ActorSystem的actorOf方法，不过这种方式是通过创建Actor，然后返回其ActorRef
  * （2）通过context.actorOf方法，这种方式也是通过创建Actor,然后返回其ActorRef
  * （3）通过context.parent、context.self、context.children方法获取当前Actor的父Actor、当前Actor及子Actor的ActorRef，这种方式是获取已经创建好的Actor的ActorRef
  * （4）通过val myActor1=system.actorSelection(myActorPath)方法来获取ActorRef，这种方式也是获取已经创建好的Actor的ActorRef
  * *
  */
object Example_08 extends App {

  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.Props

  class FirstActor extends Actor with ActorLogging {
    //通过context.actorOf方法创建Actor
    var child: ActorRef = _

    override def preStart(): Unit = {
      log.info("preStart() in FirstActor")
      //通过context上下文创建Actor [建立关系]
      child = context.actorOf(Props[MyActor], name = "myActor")
    }

    def receive = {
      //向MyActor发送消息
      case x => child ! x; log.info("received FU " + x)
    }
  }

  class MyActor extends Actor with ActorLogging {
    var parentActorRef: ActorRef = _

    override def preStart(): Unit = {
      //通过context.parent获取其父Actor的ActorRef
      /**
        * 类似与sender()
        */
      println("通过context.parent获取其父Actor的ActorRef")
      parentActorRef = context.parent
    }

    def receive = {
      case "test" => log.info("received test"); parentActorRef ! "message from ParentActorRef"
      case _ => log.info("received unknown message");
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor") //先决条件，指定父子关系
  //获取ActorPath[构造]
  val myActorPath = system.child("firstActor")
  //通过system.actorSelection方法获取ActorRef
  val myActor1 = system.actorSelection(myActorPath)
  systemLog.info("准备向myactor发送消息:")
  //向myActor1发送消息
  myActor1 ! "test"
  myActor1 ! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}