package com.fanap.corepos.device.hsm.aryan.tiantu

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.TianYuSetup
import com.fanap.corepos.device.hsm.HSMInterface
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi
import com.whty.smartpos.tysmartposapi.modules.pinpad.PinPadConfig
import com.whty.smartpos.tysmartposapi.modules.pinpad.PinPadConstant
import com.whty.smartpos.tysmartposapi.modules.pinpad.PinPadResult
import com.whty.smartpos.tysmartposapi.modules.pinpad.PinPadResultListener
import com.whty.smartpos.tysmartposapi.utils.GPMethods
import java.util.Arrays

class TianYuHSM:
 HSMInterface {
var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi

 protected var activity: Activity? = null


 private lateinit var passwordMutableLiveData : MutableLiveData<String>

 companion object {

  private lateinit var TianYuHSM: TianYuHSM

  lateinit var pinResult: PinPadResult

 }

 init {
  TianYuHSM =this@TianYuHSM

 }
 //NEW
 override fun loadMasterKey(masterKey: String): Boolean {
  var masterKey = masterKey
//  format()
//  if (masterKey.length == 16) masterKey += masterKey
  return try {
   val iRet = smartPosApi.updateMainKey(0,masterKey )

   if (iRet != null) {
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
   var  iRet = smartPosApi.updateWorkKey(0,
    PinPadConstant.KeyType.MACKEY,
    macKey ,
    null
   )
   if (iRet != null) {
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

  fun loadTransKey(macKey: String): Boolean {
  return try {
   var  i = smartPosApi.updateTransKey("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF")
   if (i != null) {
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
   var  iRet = smartPosApi.updateWorkKey(0,
    PinPadConstant.KeyType.PINKEY,
    pinKey ,
    null
   )
   if (iRet != null) {
    Log.d("pin ", "iRet  : $iRet")
//    GlobalData.getInstance().pinkeyFlag = true
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
   var  iRet = smartPosApi.updateWorkKey(0,
    PinPadConstant.KeyType.TDKEY,
    dataKey ,
    null
   )

   if (iRet != null) {
    Log.d("data ", "load Data Key Success!$dataKey")
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
  var macOutput:String? = null
try {

 macOutput = smartPosApi.calculateMac(
  macData,
  PinPadConstant.MacAlgorithm.X919
 ).data

}catch (e:Exception){
 e.printStackTrace()
}
  return macOutput!!.toByteArray()

 }




 override fun encryptionData(data: String): String {
  return try {
 smartPosApi.encryptData(
    PinPadConstant.KeyId.KEY_ID_FIRST_PINKEY,
    GPMethods.str2bytes(data),
    PinPadConstant.EncryptMode.ENCRYPT
   )
  } catch (e: Exception) {
   e.printStackTrace()
   null
  }.toString()
 }

// /decryptionData RSA
 override fun decryptionData(encryptedData: String): String {
  return try {
   var data = GPMethods.str2bytes(encryptedData)
    smartPosApi.decryptDataRSA(data)
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

// keyBoard


 // TODO TIAN INPUT PIN


 override fun inputPin(cardNumber: String, context: Context) {

  var card = cardNumber.split("=")[0]
  
  var config = PinPadConfig.create()
   .setCardNum(card)
   .setTimeout(60)
   .setMinLen(4)
   .setMaxLen(4)
   .setConfirmText("تایید")
   .setCancelText("لغو")
   .setClearText("حذف")
   .isTurnOnSound(true)
   .isDisplayBar(true)
//   .isBypass(false)
   .setPinFormat(PinPadConstant.PinFormat.FORMAT_0)
   .setUpTipText("لطفا رمز کارت خود را وارد نمایید" )
//   .isOfflinePin(true)
   .setHintText("این صفحه امن می باشد")
//   .setKeyIndex(0)
  Log.e("cardnumber" , card)

  smartPosApi.startPinPad(config, MyPinResultListener())

 }

 override fun getMutablePassword(): MutableLiveData<String> {
  passwordMutableLiveData = MutableLiveData()
  return passwordMutableLiveData
 }

 fun canellPinPad(){
  smartPosApi.cancelPinPad()
 }

 internal inner class MyPinResultListener : PinPadResultListener {
  override fun onKeyDown(count: Int) {

  }


  override fun onGetPinPadResult(pinResult: PinPadResult) {

//   if (pinResult.resultCode == 0 && pinResult.pinBlock !=null)
   passwordMutableLiveData.postValue(pinResult.pinBlock)
 /*  (pinResult.resultCode == -1 && pinResult.pinBlock ==null)
    passwordMutableLiveData.postValue("")
   canellPinPad()
   smartPosApi.cancelPinPad()*/
   Log.d("Livepassword", pinResult.pinBlock)

  }
 }

}