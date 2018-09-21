package com.hxtc.hsm


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object IcFk1 extends App {
  val threads = 1
  val times = 5000
  val start = System.currentTimeMillis()
  println(s"start: $start")
  for (j <- 1 to threads) {
    val f = Future {
      for (i <- 1 to times) {
       println(Thread.currentThread().getName)
      }
    }
    f.onSuccess {
      case _ =>
        val end = System.currentTimeMillis()
//        println(s"Thread $j - TPS ${times * 1000 / (end - start)}")
    }

  }

  Thread.sleep(10000000)

}
