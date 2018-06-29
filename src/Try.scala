import util._
import concurrent.ExecutionContext.Implicits.global
import concurrent.Future
import concurrent.duration._
/**
  * Created by hjd on 2018/6/21.
  */
object TryDecm {

  def main(args: Array[String]): Unit = {
    //Try捕捉异常
    println("1:"+Try(10 / 0).isSuccess)
    println(Try(10).flatMap { x => Try(x + "abc") })
    println(Try(10 / 0).flatMap { x => Try(x + "abc") })
    //会丢Exception
    println(Try(10).toOption)
    println(Try(10).map { x => x + 2 })
    println(Try(10 / 0) match {
      case Success(x) => x
      case Failure(error) => -1
    })
    //Future使用, 异步5秒后会打印hi
    Future { Thread.sleep(3000); println("hi") }
    println("waiting")
    Thread.sleep(5000)

    //异步执行Future, 并用回调函数获得结果
    def sayHello(s: String): String = {
      Thread.sleep(3000)
      s + " call Future"
    }
    val futureCaller = Future sequence Seq(Future(sayHello("sky")), Future(sayHello("bill")))
    futureCaller onSuccess {
      case Seq(x, y) => println(x + "," + y)
    }
    println("waiting again")
    Thread.sleep(5000)

    //同步调用Future, 如果在指定时间异步调用返回结果，则返回结果。
    val maxTime = Duration(10, SECONDS)
    println(concurrent.Await.result(Future(sayHello("sky")), maxTime))
    //同步调用Future, 如果在指定时间异步调用没有返回结果，则抛出异常。
    val maxTime1 = Duration(2, SECONDS)
    println(concurrent.Await.result(Future(sayHello("sky")), maxTime1))

  }
}
