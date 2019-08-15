package akka

import akka.actor.{ActorLogging, ActorRef}

/**
  * Created by Administrator on 2018/9/20.
  * sender（）  发送者
  * slef()      当前接收者
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
    log.info("111111111111111111w:" + sender)
    def receive = {
      //向MyActor发送消息
      case x => child ! x; log.info("received " + x); log.info("111111111111111111n~~:" + sender+"、"+x) //只在行为人本身有效，所以不要关闭它把它发布到其他线程
      case 123 => log.info("sender" + 123)
    }
  }


  class MyActor extends Actor with ActorLogging {
    self ! "message from self reference" //self : 向自身发送消息
    log.info(">>>>>>>>>>>>>>>>>>>MyActor:" + sender) //只在行为人本身有效，所以不要关闭它把它发布到其他线程
//    log.info("SELF>>>>>>>>>>>>>>>>>>>:" + self) //只在行为人本身有效，所以不要关闭它把它发布到其他线程
    def receive = {
      case "test" => {
        log.info("received test")
        /**
          * sender()：实际上是获得FirstActor 的ActorRef 代理对象，通过这个对象发送消息处理
          */
        val act: ActorRef = sender()
        log.info(">>>>>>>>>>>>>>>>>>>n:" + sender())
//        log.info("SELF>>>>>>>>>>>>>>>>>>>:" + self)
        act ! 123 //向上一级本例中为FirstActo发送消息
      };

      case "message from self reference" => log.info("接受到自身发送的消息：message from self refrence")
      case _ => log.info("received unknown message 123");
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")
//  systemLog.info("-------------->>>>>>>>>>>>>>>>>>>:" + myactor)
  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor ! "test"
//    myactor ! 123
  //  myactor ! 123
  Thread.sleep(2000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}