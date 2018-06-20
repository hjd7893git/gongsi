/**
  * Created by hjd on 2017/5/23.
  */
object Test01  {
  def main(args: Array[String]): Unit ={

    val colMaps = Map[String, String]("id" -> "id1","symbol" -> "symbol1")
    val validCond = Map[String,String]("id" -> "id",  "name" -> "name", "nameEn" -> "name_en", "summary" -> "summary")
//    val rawCond = colMaps.map((f: (String, String)) => validCond(f._1))
//    println(rawCond)
    println(colMaps.keySet -- validCond.keySet) //以前者为依据，移除相同的
    println(colMaps.keySet | validCond.keySet)  //取出两者不同的
    println(colMaps.keySet & validCond.keySet)  //取出两者相同的


    val as = List(1,2,3,4,5)
    val as1 = as.map(row=>row*2)
    println(as1)

    val a1 = 123
    val a2 = "A2"
    def either:Either[Int,String]={
      if(3>2)
        Left(a1)
      else
        Right(a2)
    }
    println(either match {
      case Right(o) =>o
      case Left(o)=>o

    })

    val op =None
    println(op.getOrElse("b","a"))
    isE
  }
 asd

  def asd()={
    if(2>17) throw new Exception("srckey error")
  }

  def isE()={
    val a:Option[String]=Some("")
    println("Some()"+a.isDefined) //t
    println("Some()"+a.isEmpty) //f
    println("Some()"+a.nonEmpty)  //t
    println("Some()"+a.get.nonEmpty) //f

    val b:Option[String]=None
    println("None："+b.isDefined) //f
    println("None："+b.isEmpty)  //t
    println("None："+b.nonEmpty)  //f
    println("None："+b.get.nonEmpty) //error
  }

}

