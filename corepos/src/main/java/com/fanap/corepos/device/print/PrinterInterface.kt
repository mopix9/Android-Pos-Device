package com.fanap.corepos.device.print

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

interface PrinterInterface {

    suspend fun printBitmap(bitmap: Bitmap): Boolean?
    fun hasPaper(): Boolean

}