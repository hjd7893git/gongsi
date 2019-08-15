/**
  * Created by Administrator on 2018/8/15.
  */
object h3 extends App {
  val lng: Long = -1
  val ssd = List(1, 2, 3, 4, 5, 6)
  val sdw = ssd.takeRight(1)
  for (cl <- 0 until 2)
    println(cl)
  println(sdw)

  val m = String.format("%03d", new Integer(Integer.toHexString(23)));
  println(m)

  val nt = "GET:/service/JournalBiz"
  val find = "GET:/service/Journal[/]?".r.findFirstIn(nt)
  println("find:" + find)

  var linksX = Seq[(String, Int)]()
  linksX = linksX.:+("1", 1)

  val as = Map[String, (Int, Int, String)]("2" -> (1, 2, "c"), "3" -> (2, 3, "c"), "4" -> (3, 5, "d"), "5" -> (1, 4, "c"), "6" -> (3, 1, "d"), "7" -> (6, 3, "d"))
  val vd = as.groupBy(f => (f._2._3)).map(_._2.map(_._2._3)).flatten
  println(vd)
}
