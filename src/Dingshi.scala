/**
  * Created by hjd on 2017/8/3.
  */

import java.util

import DemoTest8.system
import akka.actor.{ActorSystem, Props, _}
import akka.util.Timeout

import scala.concurrent.duration._

class MyActor extends Actor {
  var i = 0

  def receive = {
    case para: String =>
      i = i + 1
      println(i)
    case _ =>
  }
}

object DemoTest8 {
  val system = ActorSystem("msSystem")
  val act1 = system.actorOf(Props[MyActor], "first")
  implicit val time = Timeout(1 seconds)

  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    //    system.scheduler.scheduleOnce(2 seconds, act1, "foo") //延迟信息发送
    /*2.system.scheduler.scheduleOnce(2 seconds){
        act1 ? "Hello"
    }*/
    //3.这将会计划0ms后每50ms向tickActor发送 Tick-消息
    val Tick = "tick"
    val cancellable = system.scheduler.schedule(2 seconds, 1 seconds, act1, Tick)
    //这会取消未来的Tick发送
    //    cancellable.cancel()
    println(cancellable.isCancelled)
  }
}

object HelloWorld extends App {

  import scala.util.Properties
  import scala.concurrent.ExecutionContext.Implicits.global

  Properties.setProp("scala.time", "true")

  //  for (i <- 0 to 100000)
  //    println("Hello World!")



//  var vers: scala.collection.mutable.Map[String, Cancellable] = scala.collection.mutable.Map()
//  var nd: Cancellable = _
//  nf(Duration.Zero)
//
//  def nf(t: FiniteDuration) {
//    nd = system.scheduler.schedule(0 second,t, new Runnable {
//      override def run(): Unit = {
//        println("-----")
//        nf(2 second)
//      }
//    }) //延迟信息发送
//    if (vers.get("Test").isEmpty && !nd.isCancelled) {
//      vers ++= Map("Test" -> nd)        //添加定时任务
//    }
//    else {
//      println(vers("Test").isCancelled)
//      vers ++= Map("Test" -> nd)
//      println(vers("Test").cancel())
//    }
//  }

}