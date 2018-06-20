import akka.actor._
/**
  * 在ActorSystem层面，通过调用system.actorOf方法来创建actors；在actor内部，通过调用context.actorOf方法来创建子actor。
    下面给出一个ParentChild示例：
  * @param name
  */


case class CreateChild (name: String)
case class Name (name: String)

class Child extends Actor {
  var name = "No name"
  override def postStop: Unit = {
    println(s"D'oh! They killed me ($name): ${self.path}")
  }
  def receive = {
    case Name(name) =>
      this.name = name
      println(s"the child is $name")
    case _ => println(s"Child $name got message.")
  }
}

class Parent extends Actor {
  def receive = {
    case CreateChild(name) =>
      // Parent creates a new Child here
      println(s"Parent about to create Child ($name) ...")
      val child = context.actorOf(Props(classOf[Child]), name=s"$name")//-----------------子actorOf
      child ! Name(name)
    case _ => println(s"Parent got some other message.")
  }

  override def postStop(): Unit = {
    println("终止Parent的Actor")
  }
}

object ParentChildDemo extends App{
  val actorSystem = ActorSystem("ParentChildTest")
  val parent = actorSystem.actorOf(Props[Parent], name="Parent")

  // send messages to Parent to create to child actors
  parent ! CreateChild("XiaoMing")
  parent ! CreateChild("XiaoLiang")
  Thread.sleep(500)

  println("Sending XiaoMing a PoisonPill ... ")
//  val xiaoming = actorSystem.actorSelection("/user/Parent/XiaoMing")
//  xiaoming ! PoisonPill
  println("XiaoMing was killed")


//  Thread.sleep(5000)
//  actorSystem.shutdown //在 ActorSystem.shutdown被调用时, 系统根监管actor会被终止，以上的过程将保证整个系统的正确终止。

  /**
    *三种停止Actor的方式
    */
  //  actorSystem.stop(parent)
  //  parent ! PoisonPill

  //  context.stop(self)
  //context.system.shutdown // 停止当前ActorSystem系统


  println(s"关闭所有线程")
}


import akka.actor._

class Number5 extends Actor {
  def receive = {
    case _ =>
      println("Number 5 got a message")
      self ! "123"
  }
  override def preStart { println("Number 5 is alive")}
  override def postStop { println("Number 5::postStop called")}
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Number 5::preRestart called")
  }
  override def postRestart(reason: Throwable): Unit = {
    println("Number 5::postRestart called")
  }
}

object KillTest extends App{
  val system = ActorSystem("KillTestSystem")
  val number5 = system.actorOf(Props[Number5], name="Number5")
  number5 ! "hello"
//  number5 ! Kill
//  system.shutdown
}