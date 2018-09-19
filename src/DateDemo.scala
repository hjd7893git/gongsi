/**
  * Created by Administrator on 2018/8/14.
  */
object DateDemo extends App {

  import java.text.SimpleDateFormat
  import java.util.Calendar

  val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  var date = sdf.parse("2012-07-25 21:00:00")
  val ca = Calendar.getInstance
  ca.setTime(date)
  ca.add(Calendar.HOUR_OF_DAY, -3)
  System.out.println(sdf.format(ca.getTime))


}

class ADemo(y1:String,y2:String){
  def apply(y1: String, y2: String): ADemo = new ADemo(y1, y2)
}
