

///////////////////////////////////////////////////////////////////////کد اصلی/////////این اوکیه تقریبا


package com.fanap.corepos.device.mag_card.tian

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.TianYuSetup
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.pos.sdk.utils.PosUtils
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi
import com.whty.smartpos.tysmartposapi.modules.cardreader.CardInfo
import com.whty.smartpos.tysmartposapi.modules.cardreader.CardReaderListener

/*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.TianYuSetup
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.pos.sdk.utils.PosUtils
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi
import com.whty.smartpos.tysmartposapi.modules.cardreader.CardInfo
import com.whty.smartpos.tysmartposapi.modules.cardreader.CardReaderListener

class TianYouMagCard : MagCardInterface {

 var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi

 var ret = -1

 private var cardTrack2LiveData = MutableLiveData<String>()



 private fun cardReader() {

  try {
   smartPosApi.setReadCardConfig(true, false, false)
//                   smartPosApi.isTrackEncrypt(false);
   var cardInfo:CardInfo = smartPosApi.readCard(
    "1",
    "2024",
    0.toByte(),
    60.toByte(),
    MyCardReaderListener()
   )

//   smartPosApi!!.startSearchCard(true, true, true, 60)
//   val atr = smartPosApi!!.atr

   Log.d("cardReader1", "open:: " + if (ret == 0) "ok" else "fail")
   cardInfo = smartPosApi.magCardData!!
   ret = Log.d("cardReader2", "second try open::  " + if (ret == 0) "ok" else "fail")

   var counter = 1
   var detected = false
   while (counter++ < counter + 1) {
    Log.d("while card", counter.toString())
    if (smartPosApi.startSearchCard(true, true, true, 60) == 0) {
     detected = true
     break
    }
    PosUtils.delayms(50)
//    Thread.sleep(50)
   }
   if (detected) {
       smartPosApi.isTrackEncrypt(false);

       var  cardReader = smartPosApi.magCardData

//  var track2: ByteArray? = null
//    track2 = cardReader!!.encTrack2.toByteArray()


   var track2 = cardReader.encTrack2
   var track = track2.replace('d', '=')
//    var cardNumber = extractCardNumber(track2)

    if (track2 != null) {
     cardTrack2LiveData.postValue(track)
//     cardTrack2LiveData.postValue(String(track2))
     Log.d("cardReader3", "detect::ok    " +track)
//     Log.d("cardReader3", "detect::ok    " + String(track2))
    } else {
     cardTrack2LiveData.postValue("")
     Log.d("cardReader4", "stripDataBytes null")
     smartPosApi.cancelReadCard()
     smartPosApi.stopSearchCard()
     Log.d("cardReader5", "close::    " + if (ret == 0) "ok" else "fail")
    }
   }
  }catch (e:Exception){

  }
 }

 override fun read() {
  cardReader()
 }

 override fun getCardTrack2LiveData(): LiveData<String> {
  cardTrack2LiveData = MutableLiveData()
  return cardTrack2LiveData
 }

 override fun closeCardReader() {
  try {
   cardTrack2LiveData = MutableLiveData()
//            cardTrack2LiveData = null;
   smartPosApi.cancelReadCard()
//   ret = smartPosApi.stopSearchCard()
   cardTrack2LiveData.postValue("")
   Log.d("cardReader6", "closeee:: " + if (ret == 0) "ok" else "fail")
  } catch (ignored: Exception) {
  }
 }
  class MyCardReaderListener : CardReaderListener {
  override fun onReadCardType(cardInfo: CardInfo) {


  }

  override fun onReadCard(cardInfo: CardInfo) {


//   smartPosApi.isTrackEncrypt(false);



  }
 }

}

*/



///////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////این اوکیه تقریبا



class TianYouMagCard : MagCardInterface {

    var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi

    lateinit var cardTrack2LiveData: MutableLiveData<String>


    override fun read() {

        try {

            smartPosApi.setReadCardConfig(true, false, false)

            Log.d("CardReaderFragment", "********* open card reader.")
            val cardInfo = smartPosApi.readCard(
                "1",
                "2024",
                0.toByte(),
                60.toByte(),
                cardReaderListener
            )

            var counter = 1
            var detected = false
            while (counter++ < counter + 1) {
                Log.d("while card", counter.toString())
                if (smartPosApi.startSearchCard(true, false, false, 60) == 0) {
                    detected = true
                    break
                }
                PosUtils.delayms(50)
            }

            if (detected) {

                Log.d("CardReaderFragment", "********* Card reader opened.")

                var card = smartPosApi.magCardData
                smartPosApi.isTrackEncrypt(false);
                var track2 = card.encTrack2
                var track = track2.replace('d', '=')

                smartPosApi.isTrackEncrypt(false)
                var cardReader = smartPosApi.magCardData

                cardTrack2LiveData.postValue(track)
                Log.d("CardReaderFragmentTrack", track)
            }
                /*       break
            }*/
            } catch (E:Exception){
                E.printStackTrace()

                cardTrack2LiveData.postValue("")
            }


    }

    override fun getCardTrack2LiveData(): LiveData<String> {
        cardTrack2LiveData = MutableLiveData()
        return cardTrack2LiveData

    }

    override fun closeCardReader() {
        try {
            Log.d("CardReaderFragment", "********* Cancel card reader.")
            smartPosApi.stopSearchCard()
            smartPosApi.cancelReadCard()

            Log.d("CardReaderFragment", "********* Card reader Cancelled.")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private val cardReaderListener: CardReaderListener = object : CardReaderListener {
        override fun onReadCardType(cardInfo: CardInfo) {

          var type =  cardInfo.cardType
           Log.d("type" , type.toString())

        }
        override fun onReadCard(result: CardInfo) {

        }
    }
}
