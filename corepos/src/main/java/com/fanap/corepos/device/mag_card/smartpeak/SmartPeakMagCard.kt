package com.fanap.corepos.device.mag_card.smartpeak

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.pos.sdk.cardreader.PosCardReaderManager
import com.pos.sdk.cardreader.PosMagCardReader
import com.pos.sdk.utils.PosUtils

class SmartPeakMagCard(context: Context) : MagCardInterface {
// var cardReader: PosMagCardReader? = null
 var ret = -1
 var context: Context

 private var cardTrack2LiveData = MutableLiveData<String>()

 var  cardReader = PosCardReaderManager.getDefault(context).magCardReader


 init {
  this.context = context
 }

 private fun cardReader() {
  ret = cardReader!!.open()
  Log.d("cardReader", "open:: " + if (ret == 0) "ok" else "fail")
  if (ret != 0) {
   cardReader!!.close()
   ret = cardReader!!.open()
   Log.d("cardReader", "second try open:: " + if (ret == 0) "ok" else "fail")
  }
  var counter = 1
  var detected = false
  while (counter++ < counter + 1) {
   Log.d("while card", counter.toString())
   if (cardReader!!.detect() == 0) {
    detected = true
    break
   }
   PosUtils.delayms(50)
  }
  if (detected) {
   var track2: ByteArray? = null
   track2 = cardReader!!.getTraceData(2)
   if (track2 != null) {
    cardTrack2LiveData.postValue(String(track2))
    Log.d("cardReader", "detect::ok" + String(track2))
   } else {
    cardTrack2LiveData.postValue("")
    Log.d("cardReader", "stripDataBytes null")
    ret = cardReader!!.close()
    Log.d("cardReader", "close:: " + if (ret == 0) "ok" else "fail")
   }
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
   ret = cardReader!!.close()
   cardTrack2LiveData.postValue("")
   Log.d("cardReader", "closeee:: " + if (ret == 0) "ok" else "fail")
  } catch (ignored: Exception) {
  }
 }


}