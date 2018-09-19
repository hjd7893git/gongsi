/**
  * Created by Administrator on 2018/8/15.
  */
object h3 extends App{
  val lng:Long = -1
  val ssd = List(1,2,3,4,5,6)
  val sdw= ssd.takeRight(1)

  for(cl<- 0 until 2)
    println(cl)

  println(sdw)


  val m = String.format("%03d",new Integer(Integer.toHexString(23)));
  println(m)
}
