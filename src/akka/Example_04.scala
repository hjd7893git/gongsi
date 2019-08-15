package akka

import akka.actor.ActorLogging

/**
  * Created by Administrator on 2018/9/20.
  */
object Example_04 extends App {
  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.Props


  class FirstActor extends Actor with ActorLogging{
    //通过context.actorOf方法创建Actor
    val mn = "ABCDEFG"
    println("RT:>"+mn)
    /**
      * 也就是说context.actorOf和system.actorOf的差别是system.actorOf创建的actor为顶级Actor，
      * 而context.actorOf方法创建的actor为调用该方法的Actor的子Actor
      */
    val child = context.actorOf(Props[MyActor], name = "myChild") //MyActor是FirstActor 的子Actor
    def receive = {
      case x => child ! x
        log.info("received "+x)
    }

  }

  class MyActor extends Actor with ActorLogging{
    def receive = {
      case "test" => log.info("received test")
      case _      => log.info("received unknown message")
    }
  }

  val system = ActorSystem("MyActorSystem")   //根节点 MyActorSystem
  val systemLog=system.log                    //系统日志对象

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
//  myactor!"test"
  myactor! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}
