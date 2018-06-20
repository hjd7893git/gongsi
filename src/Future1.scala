import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


/**
  * Created by hjd on 2017/7/17.
  */
class Future1 {
 def as :Future[String] = Future{
   for(i<- 1 to 2){println(i)}
   ""
 }

}




import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
object IcFk extends App{
  val threads = 2
  val start = System.currentTimeMillis()
  println(s"Start Time: $start")
  for (j <- 1 to threads) {
    var end = 0l
    var count = 0
    val f = Future {
      do {
        {
          new Future1().as
          end = System.currentTimeMillis()
          count+=1
        }
      }while(end-start <= 1000)
    }
    f.onSuccess {
      case ons =>
        println(s"Thread $j - Run Time ${end - start}ms")
        println(s"Thread $j - 完成的数：$count")
    }
    f.onFailure{
      case one=>
        println("NO")
    }
  }
  Thread.sleep(200000)

}


