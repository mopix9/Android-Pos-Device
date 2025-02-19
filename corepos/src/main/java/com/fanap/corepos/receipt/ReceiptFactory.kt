package com.fanap.corepos.receipt

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.aryan.AryanReceiptFactory
import com.fanap.corepos.receipt.aryan.AryanTransactionReceipt
import com.fanap.corepos.receipt.sina.SinaTransactionReceipt
import com.fanap.corepos.receipt.util.ReceiptProtocol
import com.fanap.corepos.receipt.util.ReceiptUtils

object ReceiptFactory {

    lateinit var protocol : ReceiptProtocol

    fun getReceiptBitmap(context: Context, data: Any): Bitmap{
        return when(protocol){
            ReceiptProtocol.ARYAN -> AryanReceiptFactory.getReceipt(context, data as HashMap<IsoFields, Any>).generate()
            ReceiptProtocol.SINA -> SinaTransactionReceipt(context, data as HashMap<IsoFields, String>).generate().convertViewToBitmap()
            ReceiptProtocol.PASARGOD -> SinaTransactionReceipt(context, data as HashMap<IsoFields, String>).generate().convertViewToBitmap()


        }
    }

    private fun View.convertViewToBitmap() : Bitmap {
        return ReceiptUtils.getBitmapFromView(this)
    }

}