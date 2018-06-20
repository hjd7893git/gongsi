/**
  * Created by hjd on 2017/8/3.
  */
import akka.actor.{ActorSystem, Props, _}
import akka.util.Timeout

import scala.concurrent.duration._
class MyActor extends Actor{
 var i = 0
  def receive = {
    case para:String =>
      i=i+1
      println(i)
    case _ =>
  }
}
object DemoTest8{
  val system = ActorSystem("msSystem")
  val act1 = system.actorOf(Props[MyActor],"first")
  implicit val time = Timeout(1 seconds)
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    //system.scheduler.scheduleOnce(2 seconds, act1, "foo") //延迟信息发送
    /*2.system.scheduler.scheduleOnce(2 seconds){
        act1 ? "Hello"
    }*/
    //3.这将会计划0ms后每50ms向tickActor发送 Tick-消息
    val Tick = "tick"
    val cancellable = system.scheduler.schedule(2 seconds,1 seconds,act1,Tick)
    //这会取消未来的Tick发送
    println(cancellable.isCancelled)
//    cancellable.cancel()
  }
}
object HelloWorld extends App{
  import scala.util.Properties
  Properties.setProp("scala.time","true")

  for(i<- 0 to 100000)
  println("Hello World!")
}