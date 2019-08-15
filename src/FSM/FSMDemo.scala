package FSM

import akka.actor._

sealed trait Seasons   //States
case object Spring extends Seasons
case object Summer extends Seasons
case object Fall extends Seasons
case object Winter extends Seasons

//sealed trait SeasonData  //Data
case class SeasonInfo(talks: Int, month: Int)


object FillSeasons {
  sealed trait Messages    //功能消息
  case object HowYouFeel extends Messages
  case object NextMonth extends Messages

  def props = Props(new FillSeasons)
}

class FillSeasons extends FSM[Seasons,SeasonInfo] with ActorLogging {
  import FillSeasons._
  startWith(Spring,SeasonInfo(0,1))  //起始状态
  when(Spring) {   //状态在春季
    case Event(HowYouFeel,seasonInfo) =>
      val numtalks = seasonInfo.talks + 1
      log.info(s"It's ${stateName.toString}, 春 feel so gooood! You've asked me ${numtalks} times.")
      stay using seasonInfo.copy(talks = numtalks)
  }
  when(Summer) {  //夏季状态
    case Event(HowYouFeel,_) =>
      val numtalks = stateData.talks + 1
      log.info(s"It's ${stateName.toString}, 夏 it's so hot! You've asked me ${numtalks} times")
      stay().using(stateData.copy(talks = numtalks))
  }
  when(Fall) {  //秋季状态
    case Event(HowYouFeel,SeasonInfo(tks,mnth)) =>
      val numtalks = tks + 1
      log.info(s"It's ${stateName.toString}, 秋 it's no so bad. You've asked me ${numtalks} times.")
      stay using SeasonInfo(numtalks,mnth)
  }
  when(Winter) {  //冬季状态
    case Event(HowYouFeel,si@ SeasonInfo(tks,_)) =>
      val numtalks = tks + 1
      log.info(s"It's ${stateName.toString}, 冬 it's freezing cold! You've asked me ${numtalks} times.")
      stay using si.copy(talks = numtalks)
  }


  whenUnhandled {  //所有状态未处理的Event
    case Event(NextMonth,seasonInfo) =>
      val mth = seasonInfo.month
      if (mth <= 3) {
        log.info(s"未处理状态：It's month ${mth+1} of ${stateName.toString}")
        stay using seasonInfo.copy(month = mth + 1)
      }
      else {
        goto(nextSeason(stateName)) using SeasonInfo(0,1)
      }
  }
  onTransition {
    case Spring -> Summer => log.info(s"春天Season changed from Spring to Summer month ${nextStateData.month}")
    case Summer -> Fall => log.info(s"夏天Season changed from Summer to Fall month ${nextStateData.month}")
    case Fall -> Winter => log.info(s"秋天Season changed from Fall to Winter month ${nextStateData.month}")
    case Winter -> Spring => log.info(s"冬天Season changed from Winter to Spring month ${nextStateData.month}")
  }

  initialize()  //设定起始状态
  log.info(s"It's month 1 of ${stateName.toString}")


  //季节转换顺序
  def nextSeason(season: Seasons): Seasons =
    season match {
      case Spring => Summer
      case Summer => Fall
      case Fall => Winter
      case Winter => Spring
    }
}

object FSMDemo extends App {
  import scala.util.Random
  val fsmSystem = ActorSystem("fsmSystem")
  val fsmActor = fsmSystem.actorOf(FillSeasons.props,"fsmActor")

  (1 to 15).foreach { _ =>
    (1 to Random.nextInt(3)).foreach{ _ =>
      fsmActor ! FillSeasons.HowYouFeel
    }
    fsmActor ! FillSeasons.NextMonth
  }


  scala.io.StdIn.readLine()
  fsmSystem.terminate()
}