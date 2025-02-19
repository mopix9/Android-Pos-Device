package com.fanap.sina.bill

import android.app.Application
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseViewModel
import com.fanap.corepos.utils.sina.SinaUtils

class BillDetailViewModel(application: Application) : BaseViewModel(application) {

    fun makeTransaction(track2: String, pinBlock: String, amount : String ,billId:String, payId : String) =
        HashMap<IsoFields, String>().apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.ProcessCode, "170000")
            put(IsoFields.CardNumber,track2.split("=")[0])
            put(IsoFields.Amount,amount)
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
            put(IsoFields.BillId,billId)
            put(IsoFields.PayId,payId)
        }


    fun makeReceipt(track2: String, data: HashMap<IsoFields, String>, billId: String, payId: String): HashMap<IsoFields, String> {
        val amount = Utils.removeZeros(data[IsoFields.Amount]?: "")
        val billType = billId.substring(billId.length - 2, billId.length - 1)

        data.apply {
            put(IsoFields.Type, TransactionType.Bill.name)
            put(IsoFields.Amount,amount)
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, StringBuilder(SinaUtils.getTime().take(4)).insert(2, ":").toString())
            put(IsoFields.TransactionDate, SinaUtils.getPersianDate())
            put(IsoFields.TypeName, Utils.getBillName(billType))
            put(IsoFields.Status, TransactionReceiptStatus.Success.name)
            put(IsoFields.Track2,track2)
            put(IsoFields.BillId,billId)
            put(IsoFields.PayId,payId)

        }
        return data
    }
}