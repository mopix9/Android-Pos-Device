package com.fanap.corepos.device.print.amp

import android.graphics.Bitmap
import android.graphics.Paint
import android.util.Log
import com.fanap.corepos.device.print.PrinterInterface
import com.fanap.corepos.device.print.utils.PrinterUtils
import com.pos.device.printer.PrintCanvas
import com.pos.device.printer.PrintTask
import com.pos.device.printer.Printer
import com.pos.device.printer.PrinterCallback
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AmpPrinter : PrinterInterface {
    private val printTask: PrintTask = PrintTask()
    private var bitmapReceipt: Bitmap? = null

    override suspend fun printBitmap(bitmap: Bitmap): Boolean? = suspendCoroutine {

        bitmapReceipt = bitmap
        val printCanvas = PrintCanvas()
        val paint = Paint()
        if (bitmapReceipt != null) {
            bitmapReceipt = PrinterUtils.getResizedBitmap(bitmapReceipt, printerWidth)
            printCanvas.drawBitmap(bitmapReceipt, paint)
        }
        // Set print canvas
        printTask.setPrintCanvas(printCanvas)
        // Set the amount of feed paper
        printTask.addFeedPaper(0)
        // Get the gray value of the task
        printTask.gray = 160
        // Start print task

        val printerCallback = PrinterCallback { arg0: Int, arg1: PrintTask ->
            Log.d(TAG, "Printer Callback onResult")
            val str = "Printer Callback onResult " + arg0 + "   Gray: " + arg1.gray
            Log.d(TAG, str)
            if (arg0 == 0) {
                it.resume(true)
                recycle()
            } else {
                it.resume(false)
                recycle()
            }
        }

        Printer.getInstance().startPrint(printTask, printerCallback)
    }

    override fun hasPaper(): Boolean {
        return try {
            Printer.getInstance().status == 0
        } catch (e: Exception) {
            false
        }
    }

    private fun recycle() {
        bitmapReceipt!!.recycle()
        bitmapReceipt = null
    }

    companion object {
        const val TAG = "MYDEBUG"
        const val printerWidth = 383
    }

}
