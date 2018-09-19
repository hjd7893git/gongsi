import com.google.common.collect.TreeMultiset

import scala.util.Try

/**
  * Created by Administrator on 2018/8/1.
  */
object TreeMultisetTest extends App{
  val t1:TreeMultiset[String] = TreeMultiset.create[String]()
  t1 add "q"
  t1 add "q"
  t1 add "q"
  t1 add "q"
  t1 add "q"

  println(t1.toString)

  val fqfqf = List(1,2,3).foldLeft(10)((sum,i)=>sum+i)
  print(">>"+(0 /: List(1,2,3))(_+_))
  print(fqfqf)

  val map1 = Map("key1" -> 1, "key2" -> 3, "key3" -> 5)
  val map2 = Map("key2" -> 4, "key3" -> 6, "key5" -> 10)
  val mapAdd2 = (map1 /: map2)((map, kv) => {
    map + (kv._1 -> (kv._2 + map.getOrElse(kv._1, 0)))
  })
  println(mapAdd2)

  /**
    * map 中的Key值累加
    * @param map1
    * @param map2
    * @return
    */
  def fs(map1:Map[String,Int],map2:Map[String,Int]) = (map1 /: map2)((map, kv) => {
    map + (kv._1 -> (kv._2 + map.getOrElse(kv._1, 0)))
  })

  val v1 = Vector(Map("console,SMENC" -> 1), Map("console" -> 2), Map("console,SMENC" -> 1), Map("console" -> 1)).reduce((a,b)=>fs(a,b))
  println(v1)

  ////////////////////////////

  val nsd = Vector(Map("console,SMENC" -> (1,2)), Map("console" -> (2,2)), Map("console,SMENC" -> (1,3)), Map("console" -> (1,4))).reduce((a,b)=>(a /: b)((map,kv)=>{
    map + (kv._1 -> (kv._2._1,kv._2._2 + map.getOrElse(kv._1, (0,0))._2))
  }))
  println(nsd)

  ///////////////////////////

  val summ = (2 /: List(1, 2, 3, 4))((sum, i) => {
    println(s"sum=${sum} i=${i}")
    sum+i
  })

  println(summ)

  val Try1 = Try(1/0)
  println(if(Try1.isFailure) 1234)
  println(Try1.getOrElse(123))

  println("-----------------------")

  println((1 to 2).foldLeft(10)(_+_))
  println((10 /:[Int] (1 to 2))(_+_))
  println((10 /: (1 to 2))(_+_))

}
