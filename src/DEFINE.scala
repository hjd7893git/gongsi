/**
  * Created by hjd on 2017/6/2.
  */
object DEFINE extends  App{
  val index:Option[String] = Some("2")
  if (index.isDefined) println(1) else println(2)
  println(ecameecal1("03")._2)
  println(ecameecal1("03")._1)

  def ecameecal1(ee:String):(String,String) ={
      val ecale = if((ee.indexOf("03") != -1 || ee.indexOf("3") != -1 )&& ee.indexOf("3")!=3) 2 else 6
      val eca =if(ecale==2) "03" else "010001"
      val ecalength ="0" +eca.length/2
      (eca,ecalength)
  }



}
