//package com.hxtc.hsm
//
//import com.hxtc.hsm.service.SynchroService
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import net.liftweb.json.{JObject, parse}
//import scala.concurrent.Future
//
//object IcFk extends App{
//  val threads =4
//  val start = System.currentTimeMillis()
//  println(s"start: $start")
//  for (j <- 1 to threads) {
//    var end = 0l
//    var count = 0
////    val f = Future {
//      do {
////        val req = s"""14ICC_gainICCard2{"kek1":"IC-ZMK-X","kek":"IC-ZEK-X","cardBin":"000002","certSN" :"000002","keyMode":1024,"ef04":"1234567890123457","ef06":"1234567890123457","ef08":"1234567890123457","ef10":"1234567890123457","ef22":"1234567890123457","ef23":"1234567890123457","iccPAN":"62297568345678901234","t9301":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","v9301":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00","iccFormat01":"04","iccExpires01":"1219","checkCode01":"0000","t9302":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","v9302":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00","iccFormat02":"04","iccExpires02":"1219","checkCode02":"0000","t9303":"","v9303":"","iccFormat03":"","iccExpires03":"","checkCode03":"","iccFormatPine":"","iccExpiresPine":"","checkCodePine":"","specs":"pboc"} """
//          val req = s"""14ICC_gainICCard2{"kek1":"IC-ZMK-S","kek":"IC-ZEK-S","cardBin":"888888", "certSN":"888888","keyMode":256,"ef04":"1234567890123457","ef06":"1234567890123457","ef08":"1234567890123457","ef10":"1234567890123457","ef22":"1234567890123457","ef23":"1234567890123457","iccPAN":"62297568345678901234","t9301":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","v9301":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00","iccFormat01":"04","iccExpires01":"1219","checkCode01":"0000","t9302":"5F24,5A,9F07,8E,9F0D,9F0E,9F0F,5F28,82","v9302":"5F24031911305A0A6223220000000094FFFF5F3401009F0702FF008E0C000000000000000002031F009F0D05D86004A8009F0E0500109800009F0F05D86804F8009F280201567C00","iccFormat02":"04","iccExpires02":"1219","checkCode02":"0000","t9303":"","v9303":"","iccFormat03":"","iccExpires03":"","checkCode03":"","iccFormatPine":"","iccExpiresPine":"","checkCodePine":"","specs":"pboc"} """
////          val req =s"""09SVC_210112{"MAK":"BSM-001-ZAK-X","data":"1234567890"}"""
////          val req =s"""02NC2{}"""
//          val res = parse(SynchroService.connHost("127.0.0.1", 21201, "03ICC", req)).asInstanceOf[JObject]
//        end = System.currentTimeMillis()
//          count+=1
//      }while(end-start <= 1000)
////    }
////    f.onSuccess {
////      case _ =>
////        println(s"Thread $j - 完成的数：${count}")
////    }
//  }
//  Thread.sleep(20000000)
//}
