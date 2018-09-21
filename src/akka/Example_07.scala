package akka

import akka.actor.ActorLogging

/**
  * Created by Administrator on 2018/9/20.
  * unhandled
  */
/*
*Actor API:unhandled方法的使用
*/
object Example_07 extends App {

  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.Props


  class FirstActor extends Actor with ActorLogging {
    def receive = {
      //向MyActor发送消息
      case "test" => log.info("received test")
      //    case _  =>log.info("unhandled message ")
    }

    //重写unhandled方法
    //类似与 case _=>  即没有匹配到的
    //存在 case _ => 时，自动屏蔽该方法
    override def unhandled(message: Any): Unit = {
      log.info("unhandled message is {}", message)
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
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}
