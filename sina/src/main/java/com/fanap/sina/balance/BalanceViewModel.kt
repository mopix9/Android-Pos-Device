package com.fanap.sina.balance

import android.app.Application
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.base.BaseViewModel


class BalanceViewModel(application: Application) : BaseViewModel(application) {

    fun init() {}

    fun makeTransaction(track2: String, pinBlock: String) =
        HashMap<IsoFields, String>().apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.ProcessCode, "310000")
            put(IsoFields.CardNumber,track2.split("=")[0])
            put(IsoFields.LastStan, stanList[0].toString())
            put(IsoFields.Stan, stanList[1].toString())
            put(IsoFields.TransactionTime, SinaUtils.getTime())
            put(IsoFields.TransactionDate, SinaUtils.getDate())
            put(IsoFields.CardExpireDate,track2.split("=")[1].take(4))
            put(IsoFields.Track2,track2)
            put(IsoFields.Serial, serial)
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Merchant, merchant)
            put(IsoFields.PinBlock,pinBlock)
            put(IsoFields.Status, TransactionStatus.TransactionSent.name)
        }

    fun makeReceipt(track2: String, data: HashMap<IsoFields, String>): HashMap<IsoFields, String> {
        var balance = ""
        data[IsoFields.Balance]?.let{
            balance = Utils.removeZeros(it.take(12))
        }
        data.apply {
            put(IsoFields.Type,TransactionType.Balance.name)
            put(IsoFields.Balance,balance)
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, StringBuilder(SinaUtils.getTime().take(4)).insert(2, ":").toString())
            put(IsoFields.TransactionDate, SinaUtils.getPersianDate())
            put(IsoFields.TypeName, "موجودی")
            put(IsoFields.Status, TransactionReceiptStatus.Success.name)
            put(IsoFields.Track2,track2)
        }
        return data
    }

}