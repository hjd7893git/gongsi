/**
  * Created by Administrator on 2017/4/26.
  */
object map extends App {
  val mapS: Map[String, Integer] = Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4,"e"->null)
//  map.foreach((f: (String, Integer)) => println(f._2))
//  map.foreach((f: (_, _)) => println(f._2))
//  map.foreach(x => print(x._1))

  val key = mapS.map(x=>x._2+x._1).filter(_!=(null))
  val key1 = mapS.map(x => x -> x._2 + x._1)
  println("key:"+key1)
  println("RES:"+new StringBuilder("%02d%s").format("console".length, "console"))

  //  println("---------------")
  //  val x = List(1, 2, 3, 4)
  //  x.foreach((i:Int) => println(i))
  //  x.foreach(i => println(i))
  //  x.foreach(println(_))

  for (i <- 1 to 5) yield i

  val as = List(0,1,2,3,4,5)
  var sd = as.filterNot(f=> f ==2 || f==0 )
  println(sd)

//assert(2<1,"cuowu")

  def combinationF2(s: String, len: Int): String = {
    if (s.length() < len)
      s.padTo(len, "F").mkString
    else
      s
  }

  println("————————————————————"+combinationF2("a1cd",8))
}
