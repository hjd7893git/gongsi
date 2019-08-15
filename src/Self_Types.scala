/**
  * Created by Administrator on 2018/11/20.
  */
class Self {
  self => //self是this别名
  val tmp="Scala"
  def foo = self.tmp + this.tmp
}
trait S1
class S2 { this:S1 => } //限定：实例化S2时，必须混入S1类型
class S3 extends S2 with S1
class s4 {this:{def init():Unit} =>} //也能用于结构类型限定

trait T { this:S1 => } //也能用于trait
object S4 extends T with S1

object Self_Types {

  def main(args: Array[String]) {
    class Outer { outer =>
      val v1 = "Spark"
      class Inner {
        println(outer.v1)  //使用外部类的属性
      }
    }
    val c = new S2 with S1 //实例化S2时必须混入S1类型
  }

  val s = new Self
  println(s.foo)

}