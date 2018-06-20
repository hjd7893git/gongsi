package collection

/**
  * Created by hjd on 2018/5/21.
  */
object dropWhile_Test {
  def main(args: Array[String]): Unit = {
    var a1: Seq[String] = Seq.empty
    a1 ++= Seq("a", "b", "c", "E", "G")
    a1 = (a1 ++ Seq("a", "b", "c", "E", "G")).distinct
    println(a1.filterNot(p => p == "a"))
  }
}
