package com.fanap.corepos.device.print.newland

import android.graphics.Bitmap
import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.print.PrinterInterface
import com.newland.nsdk.core.api.common.ModuleType
import com.newland.nsdk.core.api.common.exception.NSDKException
import com.newland.nsdk.core.api.internal.printer.Printer
import com.newland.nsdk.core.api.internal.printer.PrinterStatus
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NewLandPrinter : PrinterInterface {

    private val mPrinter: Printer = NewLandSetup.moduleManager.getModule(ModuleType.PRINTER) as Printer


    override suspend fun printBitmap(bitmap: Bitmap): Boolean = suspendCoroutine {

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)

        try {

            mPrinter.printImage(baos.toByteArray(), 0, 384, bitmap.height) { status ->
                if(status == 0)
                    it.resume(true)
                else
                    it.resume(false)
            }
        } catch (e: NSDKException) {
            it.resume(false)
        }
    }

    override fun hasPaper(): Boolean {
        val status: PrinterStatus?
        return try {
            status = mPrinter.status
            status.code == 0
        } catch (e: NSDKException) {
            e.printStackTrace()
            false
        }
    }

}
