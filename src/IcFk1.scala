import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object IcFk1 extends App {
  val threads = 10
  val times = 1000
  val start = System.currentTimeMillis()
  println(s"start: $start")
  for (j <- 1 to threads) {
    val f = Future {
      for (i <- 1 to times) {
        val reqsm2 = s"""14ICC_gainICCard2{"iccExpires01":"1219","ef10":"0000000000000000","iccExpires02":"1219","cardBin":"222222","keyMode":256,"certSN":"222222","specs":"pboc","kek1":"IC-ZMK-S","iccFormatPine":"","ef04":"0000000000000000","iccExpires03":"","ef06":"0000000000000000","checkCodePine":"","ef08":"0000000000000000","kek":"IC-ZEK-S","ef23":"0000000000000000","ef22":"0000000000000000","iccFormat01":"04","iccFormat03":"","iccFormat02":"04","t9301":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","t9303":"","t9302":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","iccPAN":"62297568345678901234","iccExpiresPine":"","checkCode01":"0000","checkCode02":"0000","checkCode03":"","v9303":"","v9302":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00","v9301":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00"} """
//        val res = parse(SynchroService.connHost("127.0.0.1", 21205, "07console", reqsm2)).asInstanceOf[JObject]     // 107 (10000)   31 (2000*10)

//        val reqrsa = s"""14ICC_gainICCard2{"iccExpires01":"1219","ef10":"1234567890123457","iccExpires02":"1219","cardBin":"111111","keyMode":1024,"certSN":"111111","specs":"pboc","kek1":"IC-ZMK-X","iccFormatPine":"","ef04":"1234567890123457","iccExpires03":"","ef06":"1234567890123457","checkCodePine":"","ef08":"1234567890123457","kek":"IC-ZEK-X","ef23":"1234567890123457","ef22":"1234567890123457","iccFormat01":"04","iccFormat03":"","iccFormat02":"04","t9301":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","t9303":"","t9302":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","iccPAN":"62297568345678901234","iccExpiresPine":"","checkCode01":"0000","checkCode02":"0000","checkCode03":"","v9303":"","v9302":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00","v9301":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00"}"""
//        val res = parse(SynchroService.connHost("127.0.0.1", 21205, "07console", reqrsa)).asInstanceOf[JObject]   // 58 (10000)    11 (2000*10)
      }
    }
    f.onSuccess {
      case _ =>
        val end = System.currentTimeMillis()
        //        println(s"Thread $j - Run Time ${end - start}ms")
        println(s"Thread $j - TPS ${times * 1000 / (end - start)}")
    }
  }

  Thread.sleep(10000000)

}
