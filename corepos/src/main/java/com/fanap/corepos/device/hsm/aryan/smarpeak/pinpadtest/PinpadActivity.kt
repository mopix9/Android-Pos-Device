package com.fanap.corepos.device.hsm.aryan.smarpeak.pinpadtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TextView
import com.basewin.aidl.OnPinInputListener
import com.basewin.define.GlobalDef
import com.basewin.log.LogUtil
import com.basewin.services.ServiceManager
import com.basewin.utils.BCDHelper
import com.fanap.corepos.R
import com.fanap.corepos.device.hsm.aryan.smarpeak.SmartPeakHSM
import java.io.Serializable

class PinpadActivity : Activity(), OnPinInputListener {
 private var buffer: StringBuffer? = null
 private var key0: TextView? = null
 private var key1: TextView? = null
 private var key2: TextView? = null
 private var key3: TextView? = null
 private var key4: TextView? = null
 private var key5: TextView? = null
 private var key6: TextView? = null
 private var key7: TextView? = null
 private var key8: TextView? = null
 private var key9: TextView? = null
 private var clean: TextView? = null
 private var confirm: TextView? = null
 private var password: TextView? = null
 private var cardNo: TextView? = null
 private var close: ImageButton? = null
 private var keyBoard: TableLayout? = null
 private val delayTimeout = 150
 private val isInputOnline = true

 /*    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initPinPad();
        }
    };*/

 interface PinResult : Serializable {
  fun onPinResult(pinBlock: String?)
 }

 private var pinResult: PinResult? = null

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_pinpad)
  initView()
//  DeviceTools.openScreen(this)
//  DeviceTools.power_shut()
  pinResult = SmartPeakHSM.provideListener()

  //must have this, After the interface  init. Wait for 150ms
  // before you can apply for operation to obtain keyboard data.
//        handler.sendEmptyMessageDelayed(1, delayTimeout);
  initPinPad()
 }


 private val mHandler: Handler = object : Handler() {
  override fun handleMessage(msg: Message) {
   when (msg.what) {
    1 -> {
     val bundle = msg.obj as Bundle
     val pinLen: Int = bundle.getInt("pinLenth")
     Log.d("pinLenth",pinLen.toString())

     var pinBlock = bundle.getByteArray("pinBlock")
     Log.d("pinBlockByte", pinBlock.toString())
//     val pinBlockStr = "PIN Block=" + if (pinBlock!!.size == 0) "null" else IsoUtil.hexString(pinBlock)
//     val pinBlockStr = "PIN Block=" + if (pinBlock!!.size == 0) "null" else BCDHelper.bcdToString(pinBlock,0,pinLen)
//     val pinBlockStr = "PIN Block=" + if (pinBlock!!.size == 0) "null" else pinBlock
//     Log.d("pinBlockStr" , pinBlockStr)
//     val displayMessage = String.format("%s,%s", pinBlockStr)
//     Toast.makeText(this@PinpadActivity, displayMessage, Toast.LENGTH_LONG).show();
//     pinResult!!.onPinResult(StringUtils.toHexString(pinBlock))
//     pinResult!!.onPinResult( IsoUtil.hexString(pinBlock))
//     pinResult!!.onPinResult(BCDHelper.bcdToString(pinBlock,0,pinLen))
     val pinResulttt = pinBlock?.let { String(it) }
     pinResult!!.onPinResult(pinResulttt)
//     Log.d("pinresult",pinResult!!.onPinResult(BCDHelper.bcdToString(pinBlock,0,pinLen)).toString())

    }


    2 -> {
     val len = msg.obj as Int
     buffer = StringBuffer()
     var i = 0
     while (i < len) {
      buffer!!.append(" * ")
      i++
     }
     password!!.text = buffer.toString()
    }

    else -> {}
   }
  }
 }



 private fun initPinPad() {
  val cardno = intent.getStringExtra("cardNo")?.split("=")?.get(0)
  try {
   ServiceManager.getInstence().pinpad.setPinpadMode(GlobalDef.MODE_FIXED)
   ServiceManager.getInstence().pinpad.setOnPinInputListener(this)
   if (isInputOnline) {
//    ServiceManager.getInstence().pinpad.inputOnlinePinByArea(40, 50, cardno!!, byteArrayOf(0,4,5,6,7,8,9,10,11,12)
    ServiceManager.getInstence().pinpad.inputOnlinePinByArea(40, 50, cardno!!, byteArrayOf(0, 4, 6, 12)
    )
   }
   cardNo!!.text  = cardno

  } catch (e: Exception) {
   e.printStackTrace()
  }
 }

 override fun onDestroy() {
  DeviceTools.closeScreen(this)
  DeviceTools.power_open()
  super.onDestroy()
 }

 private fun initView() {
  keyBoard = findViewById(R.id.keyboard)
  key0 = findViewById(R.id.position0)
  key1 = findViewById(R.id.position1)
  key2 = findViewById(R.id.position2)
  key3 = findViewById(R.id.position3)
  key4 = findViewById(R.id.position4)
  key5 = findViewById(R.id.position5)
  key6 = findViewById(R.id.position6)
  key7 = findViewById(R.id.position7)
  key8 = findViewById(R.id.position8)
  key9 = findViewById(R.id.position9)
  clean = findViewById(R.id.clean)
  confirm = findViewById(R.id.confirm)
  close = findViewById(R.id.close)
  password = findViewById(R.id.password)
  cardNo = findViewById(R.id.cardNo)
  keyBoard!!.setVisibility(View.INVISIBLE)
 }

 /**
  * Get the key position
  *
  * @param widget
  * @return
  */
 private fun getWidgetPosition(widget: View?): ByteArray {
  val location = IntArray(2)
  widget!!.getLocationOnScreen(location)
  val leftx: Int
  val lefty: Int
  val rightx: Int
  val righty: Int
  leftx = location[0]
  lefty = location[1]
  rightx = location[0] + widget.width
  righty = location[1] + widget.height
  val pos = ByteArray(8)
  // 0,768 0x0000 0x02fc
  // 0x00,0x00,0x02,0xfc
  val tmp = BCDHelper.intToBytes2(leftx)
  val tmp1 = BCDHelper.intToBytes2(lefty)
  val tmp2 = BCDHelper.intToBytes2(rightx)
  val tmp3 = BCDHelper.intToBytes2(righty)
  Log.d("keyboard", "getWidgetPosition")
  pos[0] = tmp[2]
  pos[1] = tmp[3]
  pos[2] = tmp1[2]
  pos[3] = tmp1[3]
  pos[4] = tmp2[2]
  pos[5] = tmp2[3]
  pos[6] = tmp3[2]
  pos[7] = tmp3[3]
  return pos
 }

 //copy bytes
 private fun addToByteArray(src: ByteArray, dest: ByteArray, position: Int): Int {
  var position = position
  System.arraycopy(src, 0, dest, position, src.size)
  return src.size.let { position += it; position }
 }


  @Throws(Exception::class)
 override fun onInput(len: Int, key: Int) {
  var msg = ""
  for (i in 0 until len) {
   msg += " " + "*"
  }

  password!!.text = msg
 }

 @Throws(Exception::class)
 override fun onError(errorCode: Int) {
  pinResult!!.onPinResult("")
  finish()
 }

 @Throws(Exception::class)
 override fun onConfirm(pinBlock: ByteArray, isNonePin: Boolean) {

  val msg = mHandler.obtainMessage(1)
  val bundle = Bundle()
//  bundle.putInt(PINPAD_RETURN_TYPE, PINPAD_RETURN_CONFIRM)
// bundle.putInt("pinLenth",pinBlock.size)

/*val pinResulttt = String(pinBlock)

  pinResult!!.onPinResult(pinResulttt)

Log.d("pinResult", pinResulttt)
  */
  bundle.putByteArray("pinBlock", pinBlock ?: ByteArray(0))
  msg.obj = bundle
  msg.sendToTarget()
  finish()

 }

 @Throws(Exception::class)
 override fun onCancel() {

  pinResult!!.onPinResult("")
  finish()
 }



 @Throws(Exception::class)
 override fun onPinpadShow(bytes: ByteArray) {
  if (bytes != null) {
   Log.d("byte", bytes.toString())
   keyBoard!!.visibility = View.VISIBLE
   setPWLayout(bytes)
   keyLayout
  } else {
   keyBoard!!.visibility = View.INVISIBLE
  }
 }

 @Throws(Exception::class)
 private fun setPWLayout(keys: ByteArray) {
  key1!!.text = (keys[0] - 0x30).toString()
  key2!!.text = (keys[1] - 0x30).toString()
  key3!!.text = (keys[2] - 0x30).toString()
  key4!!.text = (keys[3] - 0x30).toString()
  key5!!.text = (keys[4] - 0x30).toString()
  key6!!.text = (keys[5] - 0x30).toString()
  key7!!.text = (keys[6] - 0x30).toString()
  key8!!.text = (keys[7] - 0x30).toString()
  key9!!.text = (keys[8] - 0x30).toString()
  key0!!.text = (keys[10] - 0x30).toString()
 }

 /**
  * Get the keyboard position
  *
  * @return keyboard layout
  */
 val keyLayout: ByteArray
  get() {
   var pos = 0
   LogUtil.i(javaClass, "getKeyLayout")
   val keylayout = ByteArray(104)
   pos = addToByteArray(getWidgetPosition(key1), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key2), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key3), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key4), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key5), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key6), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key7), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key8), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key9), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(close), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(key0), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(confirm), keylayout, pos)
   pos = addToByteArray(getWidgetPosition(clean), keylayout, pos)
   LogUtil.i(javaClass, "getKeyLayout = " + BCDHelper.hex2DebugHexString(keylayout, keylayout.size))
   try {
    ServiceManager.getInstence().pinpad.setPinpadLayout(keylayout)
   } catch (e: Exception) {
    e.printStackTrace()
   }
   return keylayout
  }

 companion object {
  const val PINPAD_REQUESTCODE = 1
  const val PINPAD_RESULTCODE = 2
  const val PINPAD_RETURN_TYPE = "pinpad_type"
  const val PINPAD_RETURN_ERROR = 0
  const val PINPAD_RETURN_CANCLE = 1
  const val PINPAD_RETURN_CONFIRM = 2
  const val PINPAD_RETURN_ERROR_CODE = "pinpad_errCode"
  const val PINPAD_RETURN_CONFIRM_NOPIN = "pinpad_confirm_NOPIN"
  const val PINPAD_RETURN_CONFIRM_PINBLOCK = "pinpad_pinblock"
 }
}