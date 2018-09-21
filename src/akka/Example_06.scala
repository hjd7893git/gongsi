package akka

import akka.actor.{ActorLogging, ActorRef}

/**
  * Created by Administrator on 2018/9/20.
  * sender（）
  * slef()
  */
/*
 *Actor API:成员变量self及sender()方法的使用
 */
object Example_06 extends App {

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
    self ! "message from self reference"  //self : 向自身发送消息
    println("----------")

    def receive = {
      case "test" => log.info("received test"); sender() ! "message from MyActor" //向上一级本例中为FirstActo发送消息
      case "message from self reference" => log.info("message from self refrence")
      case _ => log.info("received unknown message 123");
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor ! "test"
  myactor ! 123
//  myactor ! 123
  Thread.sleep(2000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}