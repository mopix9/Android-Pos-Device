package com.fanap.corepos.receipt.aryan

import android.content.Context
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.Receipt
import com.fanap.corepos.receipt.enum.TransactionType

object AryanReceiptFactory {

    fun getReceipt(context: Context , data: HashMap<IsoFields, Any>) : Receipt{
        return when(data[IsoFields.Type]){
            TransactionType.Settings.name -> AryanSettingsReceipt(context,data as HashMap<IsoFields, String>)
            TransactionType.Total.name -> AryanTotalReceipt(context,data as HashMap<IsoFields, String>)
            TransactionType.DetailList.name -> AryanListReceipt(context,data)
            else -> AryanTransactionReceipt(context,data as HashMap<IsoFields, String>)
        }
    }

}