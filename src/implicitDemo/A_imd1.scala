package implicitDemo

/**
  * Created by Administrator on 2018/12/27.
  */
object A_imd1 extends App {
  object MyConversions {
    implicit def String2Int(str: String) = str.toInt
    implicit def Double2Int(num: Double) = num.toInt
  }
  import MyConversions.Double2Int
  val x: Int = 12.0
  println(x)
//-----------------------------------------------------------
  case class Person(name: String, age: Int) {
    def +(x: Int) = age + x
    def +(p: Person) = age + p.age
  }
  val person = Person("xiaohong", 1)
  println(person + 1 )// 2  ==>  person.+(1)

  implicit def Add1(x: Int) = Person("", x) // implicit def Add2(x: Person) = x.age
  implicit def Add2(x: Person) = x.age+1
  println(1 + person) // 2  println(1.+(person))
//-----------------------------------------------------------
  implicit class MyRange(start: Int) {
    def -->(end: Int) = start to end
  }
  println((1 --> 10).sum) // 55
 // ------------------------------------------------
  implicit val a = 2
  implicit val b = "B"

  def fun(implicit x: Int, y: String) = {  //只能加一个implicit
    x + y
  }

  println(fun) // 2B
  println(fun(1,"A")) // 1A
}
