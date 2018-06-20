import akka.actor._

case object PingMessage
case object PongMessage_A
case object StartMessage
case object StopMessage

//构造类参数传递actor
class Ping(pong: ActorRef) extends Actor{
  var count = 0
  def incrementAndPrint {count += 1; println(s"$count:ping")}
  def receive = {
    case StartMessage =>
      incrementAndPrint
      pong ! PongMessage_A
    case PingMessage =>
      incrementAndPrint
      if(count > 99) {
        sender ! StopMessage
        println("ping stopped")
        context.stop(self)
      }
      else
        sender ! PongMessage_A
    case _ => println("Ping got unexpected information")
  }
}

class Pong extends Actor {
  var count = 0
  def receive = {
    case StopMessage =>
      println("pong stopped")
      context.stop(self)
    case PongMessage_A =>
      count += 1
      println(s"$count:pong")
      sender ! PingMessage
    case _ => println("Pong got unexpected information")
  }
}

object PingPangTest extends App {
  val system = ActorSystem("PingPongTest")
  val pongActor = system.actorOf(Props[Pong], name = "pong")
  val pingActor = system.actorOf(Props(new Ping(pongActor)),
    name = "ping")
  pingActor ! StartMessage
}
/** 创建ActorSystem之后；
    创建Pong的actor实例（pongActor对象其实是ActorRef的实例）；
    之后创建Ping的actor实例，其构造函数接受ActorRef参数；
    通过给pingActor发送一个StartMessage消息来启动pingActor和pongActor的具体动作；
    Ping Actor和Pong Actor通过PingMessage和PongMessage相互发送消息，sender用来引用消息发送源Actor；
    Ping通过计数，知道进行了100次消息的发送之后，发送StopMessage来终止actor。分别调用自己的context.stop方法来结束
/////////////////////////////////////////////////////////////////

    在ActorSystem层面，通过system.stop(actorRef)来终止一个actor；在actor内部，使用context.stop(actorRef)来结束一个actor。
    如果当前有正在处理的消息，对该消息的处理将在actor被终止之前完成，但是邮箱中的后续消息将不会被处理。缺省情况下这些消息会被送到
ActorSystem的dead letter mailbox, 但是这取决于邮箱的实现。

  */