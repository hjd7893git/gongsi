import com.google.common.collect.TreeMultiset

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

}
