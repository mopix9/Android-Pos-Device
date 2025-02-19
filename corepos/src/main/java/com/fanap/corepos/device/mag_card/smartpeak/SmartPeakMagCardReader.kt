/*
package com.fanap.corepos.device.mag_card.smartpeak

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.pos.sdk.cardreader.PosCardReaderManager
import com.pos.sdk.cardreader.PosMagCardReader
import com.pos.sdk.utils.PosUtils


class SmartPeakMagCardReader(context: Context?) : MagCardInterface {


 private lateinit var cardTrack2LiveData: MutableLiveData<String>
 var cardReader: PosMagCardReader? = null
val context: Context? = null
 var ret = 0

 fun SmartPeakMagCardReader(context: Context){
//  cardTrack2LiveData = null
  cardTrack2LiveData = MutableLiveData<String>()
  cardReader = PosCardReaderManager.getDefault(context).magCardReader
  Log.d("cardReader", "SmartPeakMagCardReader constractor")
 }
 fun cardReader(){
  val ret = cardReader!!.open()
  Log.d("cardReader", "open:: " + if (ret == 0) "ok" else "fail")
  if (ret != 0) {
   cardReader!!.close()
   this.ret = cardReader!!.open()
   Log.d("cardReader", "second try open:: " + if (ret == 0) "ok" else "fail")
 }
  var counter = 1
  while (counter < 200) {
   Log.d("while", counter.toString())
   if (cardReader!!.detect() == 0) {
    var track2: ByteArray
    track2 = cardReader!!.getTraceData(2)
    if (track2 != null) {
     cardTrack2LiveData!!.postValue(String(track2))
     Log.d("cardReader", "detect::ok")
     break
    } else {
     cardTrack2LiveData!!.postValue("")
     Log.d("cardReader", "stripDataBytes null")
    }
   }
   PosUtils.delayms(50)
   counter++
  }
  this.ret = cardReader!!.close()
  Log.d("cardReader", "close:: " + if (ret == 0) "ok" else "fail")
 }

 override fun read() {
  cardReader()
 }

 override fun getCardTrack2LiveData(): LiveData<String?> {
  return cardTrack2LiveData
 }

 override fun closeCardReader() {
  try {
//   cardTrack2LiveData = null
   ret = cardReader!!.close()
   Log.d("cardReader", "close:: " + if (ret == 0) "ok" else "fail")
  } catch (e: Exception) {
  }
 }


}






*/






/*

 override fun read() {

  cardReader = PosCardReaderManager.getDefault(context).magCardReader
  if (cardReader != null) {
   LOGD("****** Mag test******")
   val ret = cardReader!!.open()
   LOGD("open:: " + if (ret == 0) "ok" else "fail")
  } else {
   LOGD("Mag cardreader is not support!")
  }
 }

 override fun getCardTrack2LiveData(): LiveData<String> {
  cardTrack2LiveData = MutableLiveData()
  return cardTrack2LiveData
 }

 private fun CardTest() {
  LOGD("start to detect")
  var cnt = 0
  var detected = false
  while (cnt++ < MAX_TRY_CNT) {
   if (cardReader!!.detect() == 0) {
    detected = true
    break
   }
   PosUtils.delayms(50)
  }
  LOGD("detect:: " + if (detected) "ok" else "fail")
  if (detected) {
   var stripDataBytes: ByteArray? = null
   for (i in PosMagCardReader.CARDREADER_TRACE_INDEX_1..PosMagCardReader.CARDREADER_TRACE_INDEX_3) {
    stripDataBytes = cardReader!!.getTraceData(i)
    if (stripDataBytes != null) {
     LOGD("getTraceData:: strip" + i + "'s data= " + String(stripDataBytes))
    }
   }
  }
 }
 override fun closeCardReader() {
  ret = cardReader!!.close()
  LOGD("close:: " + if (ret == 0) "ok" else "fail")
 }

 override fun onCreateView(inflater: LayoutInflater?): View? {
  TODO("Not yet implemented")
 }

 override fun onInitView() {
  TODO("Not yet implemented")
 }
*/
// =========================================================================================
/*
var cardReader: PosMagCardReader? = null
 var ret = -1
 var context: Context? = null
 private var cardTrack2LiveData: MutableLiveData<String>? = null

 fun SmartPeakMagCardReader(context: Context) {
  cardTrack2LiveData = null
  cardTrack2LiveData = MutableLiveData<String>()
  cardReader = PosCardReaderManager.getDefault(context).magCardReader
  this.context = context
  Log.d("cardReader", "XchengMagCardReader constractor")
 }

 private fun cardReader() {
  ret = cardReader!!.open()
  Log.d("cardReader", "open:: " + if (ret == 0) "ok" else "fail")
  if (ret != 0) {
   cardReader!!.close()
   ret = cardReader!!.open()
   Log.d("cardReader", "second try open:: " + if (ret == 0) "ok" else "fail")
  }

  //while (cnt++ < MAX_TRY_CNT)
  // if is detectTrue break -
  //if duration is finish break -
  //endMagnet is true => break
  var counter = 1
  while (counter < 200) {
   Log.d("while", counter.toString())
   if (cardReader!!.detect() == 0) {
    var track2: ByteArray
    track2 = cardReader!!.getTraceData(2)
    if (track2 != null) {
     cardTrack2LiveData!!.postValue(String(track2))
     Log.d("cardReader", "detect::ok")
     break
    } else {
     cardTrack2LiveData!!.postValue("")
     Log.d("cardReader", "stripDataBytes null")
    }
   }
   PosUtils.delayms(50)
   counter++
  }
  ret = cardReader!!.close()
  Log.d("cardReader", "close:: " + if (ret == 0) "ok" else "fail")
 }

 override fun read() {
  cardReader()
 }

 override fun getCardTrack2LiveData(): LiveData<String?>? {
  return cardTrack2LiveData
 }

 override fun closeCardReader() {
  try {
   cardTrack2LiveData = null
   ret = cardReader!!.close()
   Log.d("cardReader", "close:: " + if (ret == 0) "ok" else "fail")
  } catch (e: Exception) {
  }
 }


}*/
