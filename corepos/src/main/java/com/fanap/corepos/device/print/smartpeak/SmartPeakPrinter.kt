package com.fanap.corepos.device.print.smartpeak

import android.graphics.Bitmap
import android.util.Log
import com.basewin.aidl.OnPrinterListener
import com.basewin.define.GlobalDef
import com.basewin.models.BitmapPrintLine
import com.basewin.models.PrintLine
import com.basewin.services.PrinterBinder
import com.basewin.services.ServiceManager
import com.basewin.utils.TimerCountTools
import com.fanap.corepos.device.print.PrinterInterface
import com.pos.sdk.printer.PosPrinter
import com.pos.sdk.printer.PosPrinterInfo

class SmartPeakPrinter : PrinterInterface {
 private val printer_callback = PrinterListener()
 private var bitmapReceipt: Bitmap? = null
 private var timeTools: TimerCountTools? = null
 private val timeTools1: TimerCountTools? = null
 val bitmapPrintLine = BitmapPrintLine()





  override suspend fun printBitmap(bitmap: Bitmap): Boolean {

   try {
 /*   timeTools = TimerCountTools()
    timeTools!!.start()*/

    ServiceManager.getInstence().printer.cleanCache()
    ServiceManager.getInstence().printer.printTypesettingType = PrintLine.BITMAP


    bitmapPrintLine.bitmap = bitmap
    ServiceManager.getInstence().printer.addPrintLine(bitmapPrintLine)

    ServiceManager.getInstence().printer.beginPrint(printer_callback)
   }
   catch(e:Exception){
    e.printStackTrace()
   }
return true
 }

 override fun hasPaper(): Boolean {
  TODO("Not yet implemented")
 }


 internal class PrinterListener : OnPrinterListener  {
  private var timeTools: TimerCountTools? = null
  private var timeTools1: TimerCountTools? = null
  private var printMaxNum = 0
  private val printType = 1
  private val TAG = "Print"
  override fun onStart() {
   // TODO 打印开始
   // Print start
   Log.e(TAG, "onStart")
/*
   timeTools1 = TimerCountTools()
   timeTools1!!.start()
*/

  }


  override fun onFinish() {
   // TODO 打印结束
   // End of the print
/*   timeTools = TimerCountTools()
   timeTools!!.stop()*/
/*   timeTools1!!.stop()
timeTools!!.processTime*/

  }

  override fun onError(errorCode: Int, detail: String) {
   // TODO 打印出错
   // print error

  }
 }


}
