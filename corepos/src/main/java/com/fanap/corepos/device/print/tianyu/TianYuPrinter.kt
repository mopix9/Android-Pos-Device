package com.fanap.corepos.device.print.tianyu

import android.graphics.Bitmap
import com.fanap.corepos.device.TianYuSetup
import com.fanap.corepos.device.print.PrinterInterface
import com.newland.nsdk.core.api.common.exception.NSDKException
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi
import com.whty.smartpos.tysmartposapi.modules.printer.PrinterInitListener
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TianYuPrinter : PrinterInterface {

    var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi

    var res: Boolean = smartPosApi.initPrinter(MyPrinterInitListener())


    override suspend fun printBitmap(bitmap: Bitmap): Boolean = suspendCoroutine {

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)

        try {

           val status  = smartPosApi.printBitmap(bitmap)

            if (status == 0 )
               it.resume(true)

                else

                it.resume(false)

            smartPosApi.releasePrinter()


        } catch (e: NSDKException) {
            it.resume(false)
        }

    }

    override fun hasPaper(): Boolean {
//        val status: PrinterStatus?
        var status = smartPosApi.printerStatus
        return try {
            status = smartPosApi.printerStatus
            status == 0
        } catch (e: NSDKException) {
            e.printStackTrace()
            false
        }

    }


    internal class MyPrinterInitListener : PrinterInitListener {
        override fun onPrinterInit(isSuccess: Boolean) {


        }
    }

}
