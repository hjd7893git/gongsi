/**
  * Created by hjd on 2017/5/23.
  */
case class A(a: Int, b: String)

object Test02 {
  def main(args: Array[String]): Unit = {
    var seq: Seq[A] = Seq.empty[A]
    seq = seq :+ A(1, "hello") :+ A(2, "asdsa") :+ A(23, "qwe") :+ A(23, "qwe") :+ A(0, "0")
    //   val fin =  seq.find((f:String)=>f.toString=="23")
    println {
      distinct(seq)
    }
    println(seq.distinct)

    def distinct(praSeq: Seq[A]) = if (praSeq.nonEmpty) praSeq.map(f => f.a).distinct.flatten { f => praSeq.find(pr => pr.a == f) } else null

  }
}
