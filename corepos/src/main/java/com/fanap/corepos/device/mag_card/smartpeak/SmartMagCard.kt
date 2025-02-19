/*
package com.fanap.corepos.device.mag_card.smartpeak

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.pos.sdk.cardreader.PosCardReaderManager
import com.pos.sdk.cardreader.PosMagCardReader
import com.pos.sdk.utils.PosUtils

class SmartPeakMagCard(context: Context) : MagCardInterface {
 var cardReader: PosMagCardReader? = null
 var ret = 0
 var context: Context


 private fun cardReader() {
  ret = cardReader!!.open()
  Log.d("cardReader", "open:: " + if (ret == 0) "ok" else "fail")
  if (ret != 0) {
   cardReader!!.close()
   ret = cardReader!!.open()
   Log.d("cardReader", "second try open:: " + if (ret == 0) "ok" else "fail")
  }

  */
/* while (cnt++ < MAX_TRY_CNT)
         if is detectTrue break -
        if duration is finish break -
        endMagnet is true => break*//*

  var counter = 1
  while (counter < 200) {
   Log.d("while", counter.toString())
   if (cardReader!!.detect() == 0) {
    var track2: ByteArray?
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

 override fun getCardTrack2LiveData(): MutableLiveData<String>? {
  return cardTrack2LiveData
 }

 override fun closeCardReader() {
  try {
   Companion.cardTrack2LiveData = null
   ret = cardReader!!.close()
   Log.d("cardReader", "close:: " + if (ret == 0) "ok" else "fail")
  } catch (e: Exception) {
  }
 }

 companion object {
  var cardTrack2LiveData: MutableLiveData<String>? = null
 }

 init {
  Companion.cardTrack2LiveData = MutableLiveData()
  cardReader = PosCardReaderManager.getDefault(context).magCardReader
  this.context = context
  Log.d("cardReader", "SmartPeakMagCard constractor")
 }
}
*/
