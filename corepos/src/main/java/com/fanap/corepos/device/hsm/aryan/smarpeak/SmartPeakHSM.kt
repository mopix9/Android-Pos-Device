package com.fanap.corepos.device.hsm.aryan.smarpeak

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.basewin.define.KeyType
import com.basewin.services.ServiceManager
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.device.hsm.aryan.smarpeak.pinpadtest.PinpadActivity
import com.fanap.corepos.device.hsm.util.smartpeak.GlobalData
import java.util.Arrays

class SmartPeakHSM():
 HSMInterface, PinpadActivity.PinResult {
 var TAG = "smartpeak"

 //    private Activity mActivity = null;
 private val area = 40
 private val index = 2
 private val type = KeyType.PIN_KEY
 private val tmkindex = 50
 private val macindex = 2
 private val pinindex = 3

 //     private int tdkindex = 0x02;
 private val tdkindex = 0x04
 private lateinit var passwordMutableLiveData : MutableLiveData<String>

 companion object {


  private lateinit var smartPeakHSM: SmartPeakHSM



  @JvmStatic
  fun provideListener(): PinpadActivity.PinResult? {
   return smartPeakHSM
  }
 }

 init {
  smartPeakHSM =this@SmartPeakHSM
 }
 //NEW
 override fun loadMasterKey(masterKey: String): Boolean {
  var masterKey = masterKey
  format()
  if (masterKey.length == 16) masterKey += masterKey
  return try {
   var iRet = false
   iRet = ServiceManager.getInstence().pinpad.loadMainKeyByArea(area, tmkindex, masterKey)
   if (iRet) {
    GlobalData.getInstance().tmkId = tmkindex
    Log.d("master ", "load Main Key Success!$masterKey")
    true
   } else {
    Log.d("master ", "load Main Key failed!")
    false
   }
  } catch (e: Exception) {
   e.printStackTrace()
   Log.d("master", "loadMainKey error !" + e.message)
   false
  }
 }

 override fun loadMacKey(macKey: String): Boolean {
  return try {
   var iRet = false
   iRet = ServiceManager.getInstence().pinpad.loadMacKeyByArea(area, tmkindex, macKey, null)
   if (iRet) {
    Log.d("mac ", "load Mac Key Success!$macKey")
    true
   } else {
    Log.d("mac ", "load Mac Key failed!")
    false
   }
  } catch (e: Exception) {
   e.printStackTrace()
   Log.d("mac", "loadMacKey error !")
   false
  }
 }

 override fun loadPinKey(pinKey: String): Boolean {
  return try {
   var iRet = false
   iRet = ServiceManager.getInstence().pinpad.loadPinKeyByArea(area, tmkindex, pinKey, null)
   if (iRet) {
    Log.d("pin ", "iRet  : $iRet")
    GlobalData.getInstance().pinkeyFlag = true
    Log.d("pin ", "load pin Key Success!$pinKey")
    true
   } else {
    Log.d("pin ", "load pin Key failed!")
    false
   }
  } catch (e: Exception) {
   e.printStackTrace()
   Log.d("pin", "loadPinKey error !" + e.message)
   false
  }
 }

 override fun loadDataKey(dataKey: String): Boolean {
  return try {
   var iRet = false
   iRet = ServiceManager.getInstence().pinpad.loadMacKeyByArea(area, tmkindex, dataKey, null)
   if (iRet) {
    Log.d("data ", "load Mac Key Success!$dataKey")
    true
   } else {
    Log.d("data ", "load Data Key failed!")
    false
   }
  } catch (e: Exception) {
   e.printStackTrace()
   Log.d("data", "loadDataKey error !")
   false
  }
 }

 override fun calcMac(macData: ByteArray): ByteArray {
  return ByteArray(0)
 }

 override fun inputPin(cardNumber: String, context: Context) {
  val intent = Intent(context, PinpadActivity::class.java)
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  intent.putExtra("cardNo", cardNumber)
  context.startActivity(intent)
 }

 override fun encryptionData(data: String): String {
  return try {
   ServiceManager.getInstence().pinpad.encryptDataByArea(area, tmkindex, type, data)
  } catch (e: Exception) {
   e.printStackTrace()
   null
  }.toString()
 }

 override fun decryptionData(encryptedData: String): String {
  return try {
   ServiceManager.getInstence().pinpad.decryptDataByArea(
    area,
    tmkindex,
    type,
    encryptedData
   )
  } catch (e: Exception) {
   e.printStackTrace()
   null
  }.toString()
 }

 private fun encryptDataPadding(originalData: ByteArray): ByteArray {
  val originalLength = originalData.size
  var dataLength = originalLength
  while (dataLength % 8 != 0) dataLength++
  val dados = ByteArray(dataLength)
  Arrays.fill(dados, 0xFF.toByte())
  System.arraycopy(originalData, 0, dados, 0, originalLength)
  return dados
 }

 private fun EncryptDataPadding(originalData: ByteArray): ByteArray {
  val originalLength = originalData.size
  var dataLength = originalLength
  while (dataLength % 8 != 0) dataLength++

  // Completa o padding com bytes 0xFF
  val dados = ByteArray(dataLength)
  Arrays.fill(dados, 0xFF.toByte())
  System.arraycopy(originalData, 0, dados, 0, originalLength)
  return dados
 }

 fun format() {
  try {
   ServiceManager.getInstence().pinpad.format()
   Log.d(TAG, "format success!")
  } catch (e: Exception) {
   e.printStackTrace()
  }
 }

 override fun getMutablePassword(): MutableLiveData<String> {
  passwordMutableLiveData = MutableLiveData()
  return passwordMutableLiveData
 }

 override fun onPinResult(pinBlock: String?) {
  passwordMutableLiveData.postValue(pinBlock)
  Log.d("Livepassword", pinBlock!!)
 }


}