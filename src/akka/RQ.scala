package akka

/**
  * Created by Administrator on 2018/9/28.
  */
object RQ {
  trait Action{
    val message: String
    val time: Int
  }

  case class TurnOnLight(time: Int) extends Action {   // 开灯消息
    val message = "Turn on the living room light"
  }

  case class BoilWater(time: Int) extends Action {   // 烧水消息
    val message = "Burn a pot of water"
  }

}
