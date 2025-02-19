package com.fanap.corepos.receipt

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.util.ReceiptUtils

abstract class Receipt {

    abstract fun generate() : Bitmap

    fun View.convertViewToBitmap() : Bitmap {
        return ReceiptUtils.getBitmapFromView(this)
    }
}