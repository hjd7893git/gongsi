/**
  * Created by hjd on 2018/6/15.
  */
object h2 extends App {
  val a = Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4)
  a.filter(_._1 != "a").foreach(println _)

  val m1 = "v"
  m1 match {
    //      case x if x =="a" || x == "b" =>println("a or b")
    case "a" | "b" => println("a or b")
    case _ =>
  }

  def mf(a2: String) = (b2: String) => println(a2 + "/t" + b2)

  val mf1 = mf("1")
  mf1("2")

  /////////////////////////// 返回类型为为函数
  val sum = (a1: Int, b1: String) => (a1 + b1).toString
  val m: (Int => String) = sum(_, "m")
  println(m(1))


  val c = Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4)



  val va = Seq[String]("张三","李四")
  println(va.map(s=>s))
  println(va.map(s=>s).mkString(" + "))

  case class B(name: String)
  val vb = Seq(B("张三"),B("李四"))
  println(vb.map(_.name))
  println(vb.map(_.name).mkString(" + "))
  println(vb.map(p=>p.name).mkString(" + "))


  println((1 to 9).map(_ * 3))
  println((1 to 9).map(i=>i))
  println((1 to 9).map(identity))
  println(vb.map(identity))

  class Person[T,S](val name:S,val age:T)
  val p:Person[String,Int]=null


}
