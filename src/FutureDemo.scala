import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object FutureDemo extends App {

  val f = Future {
    println("working on future task")
    Thread.sleep(100)
    1+1
  }
//单线程阻塞式，只有当前此线程执行完后再执行下列任务
  println("waiting for future task complete")
  val result = Await.result(f, 1 second)
  println(result)
  /**
    * 执行异步任务需要上下文, ExecutionContext.Implicits.global是使用当前的全局上下文作为隐式上下文.
      引入.duration._允许我们使用1 second, 200 milli, 2 minute这样的时间间隔字面值.
      上述示例中Await.result使用阻塞的方式等待Future任务完成, 若Future超时未完成则抛出TimeoutException异常
    */
}

/**
  * 非阻塞，异步操作-----------------------------------
  */

import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object FutureNotBlock extends App {
  println("starting calculation ...")
  val f = Future {
    Thread.sleep(Random.nextInt(500))
    42
  }

  println("before onComplete")
  f.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => e.printStackTrace
  }

  // do the rest of your work
  println("A ...")
  Thread.sleep(100)
  println("B ....")
  Thread.sleep(100)
  println("C ....")
  Thread.sleep(100)
  println("D ....")
  Thread.sleep(100)
  println("E ....")
  Thread.sleep(100)

  Thread.sleep(2000)
}

/**
  *
  * -----------------------------------方法返回future
  */

import scala.concurrent.{Await, Future, future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object ReturnFuture extends App{
  implicit val baseTime = System.currentTimeMillis

  // `future` method is another way to create a future
  // It starts the computation asynchronously and retures a Future[Int] that
  // will hold the result of the computation.
  def longRunningComputation(i: Int): Future[Int] = future {
    Thread.sleep(100)
    i + 1
  }

  // this does not block
  longRunningComputation(11).onComplete {
    case Success(result) => println(s"result = $result")
    case Failure(e) => e.printStackTrace
  }

  // keep the jvm from shutting down
  Thread.sleep(1000)
}