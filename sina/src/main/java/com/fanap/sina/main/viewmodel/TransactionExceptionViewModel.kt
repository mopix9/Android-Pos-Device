package com.fanap.sina.main.viewmodel

import android.app.Application
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.base.BaseViewModel
import com.fanap.sina.utils.SinaResponse

class TransactionExceptionViewModel (application: Application) : BaseViewModel(application) {

    fun makeReceipt(input: Transaction?): HashMap<IsoFields, String>? {
        input?.let {
            return when(it.processCode){
                "000000" -> makeBuyReceipt(input)
                "170000" -> makeBillReceipt(input)
                "190000" -> makeChargeReceipt(input,false)
                "180000" -> makeChargeReceipt(input,true)
                else -> null
            }
        }
        return null
    }

    private fun makeBuyReceipt(transaction: Transaction): HashMap<IsoFields, String> {
        val data = HashMap<IsoFields, String>()

        when (transaction.response) {
            "00" -> data[IsoFields.Status] =  TransactionReceiptStatus.Success.name
            "-1" -> {
                data[IsoFields.Status] = TransactionReceiptStatus.UnReceivedResponse.name
                data[IsoFields.FailMessage] = "خطا در انجام تراکنش"
            }
            else -> {
                data[IsoFields.Status] = TransactionReceiptStatus.Fail.name
                data[IsoFields.FailMessage] = SinaResponse.getResponse(transaction.response ?: "")
            }
        }

        return data.apply {
            put(IsoFields.Type, TransactionType.Buy.name)
            put(IsoFields.Amount, transaction.amount?: "0")
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, SinaUtils.getTimeForReceipt(transaction.date?.takeLast(6) ?: ""))
            put(IsoFields.TransactionDate, SinaUtils.getShamsiDateFromString(transaction.date?.take(6)?: ""))
            put(IsoFields.TypeName, "خرید")
            put(IsoFields.Track2, transaction.card ?: "")
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Rrn, transaction.rrn ?: "")
            put(IsoFields.Stan, transaction.stan ?: "")
            put(IsoFields.IsAgainReceipt, true.toString())
        }
    }

    private fun makeBillReceipt(transaction: Transaction): HashMap<IsoFields, String> {
        val data = HashMap<IsoFields, String>()

        when (transaction.response) {
            "00" -> data[IsoFields.Status] =  TransactionReceiptStatus.Success.name
            "-1" -> {
                data[IsoFields.Status] = TransactionReceiptStatus.UnReceivedResponse.name
                data[IsoFields.FailMessage] = "خطا در انجام تراکنش"
            }
            else -> {
                data[IsoFields.Status] = TransactionReceiptStatus.Fail.name
                data[IsoFields.FailMessage] = SinaResponse.getResponse(transaction.response ?: "")
            }
        }

        data[IsoFields.Response] = transaction.response ?: ""

        data.apply {
            put(IsoFields.Type, TransactionType.Bill.name)
            put(IsoFields.Amount,transaction.amount?:"")
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, SinaUtils.getTimeForReceipt(transaction.date?.takeLast(6) ?: ""))
            put(IsoFields.TransactionDate, SinaUtils.getShamsiDateFromString(transaction.date?.take(6)?: ""))
            put(IsoFields.TypeName, transaction.description ?: "")
            put(IsoFields.Track2,transaction.card ?: "")
            put(IsoFields.BillId, transaction.description2 ?: "")
            put(IsoFields.PayId, transaction.description3 ?: "")
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Rrn, transaction.rrn ?: "")
            put(IsoFields.Stan, transaction.stan ?: "")
            put(IsoFields.IsAgainReceipt, true.toString())
        }
        return data
    }

    private fun makeChargeReceipt(transaction: Transaction, isTopUp: Boolean): HashMap<IsoFields, String> {
        val data = HashMap<IsoFields, String>()

        when (transaction.response) {
            "00" -> data[IsoFields.Status] =  TransactionReceiptStatus.Success.name
            "-1" -> {
                data[IsoFields.Status] = TransactionReceiptStatus.UnReceivedResponse.name
                data[IsoFields.FailMessage] = "خطا در انجام تراکنش"
            }
            else -> {
                data[IsoFields.Status] = TransactionReceiptStatus.Fail.name
                data[IsoFields.FailMessage] = SinaResponse.getResponse(transaction.response ?: "")
            }
        }

        if(isTopUp)
            data[IsoFields.Type] =  TransactionType.Topup.name
        else
            data[IsoFields.Type] =  TransactionType.Voucher.name

        data.apply {
            put(IsoFields.Amount,transaction.amount ?: "")
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, SinaUtils.getTimeForReceipt(transaction.date?.takeLast(6) ?: ""))
            put(IsoFields.TransactionDate, SinaUtils.getShamsiDateFromString(transaction.date?.take(6)?: ""))
            put(IsoFields.TypeName, "خرید شارژ")
            put(IsoFields.Track2,transaction.card ?: "")
            put(IsoFields.ChargePin,"468464535448548")
            put(IsoFields.ChargeOrganization, transaction.description ?: "")
            put(IsoFields.PhoneNumber, transaction.description2 ?: "")
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Rrn, transaction.rrn ?: "")
            put(IsoFields.Stan, transaction.stan ?: "")
            put(IsoFields.IsAgainReceipt, true.toString())
        }
        return data
    }

    fun makeAdvice(result: HashMap<IsoFields, String>, track2: String , lastStan : String , stan : String? = null) = HashMap<IsoFields, String>().apply {
        put(IsoFields.Mti,"0220")
        put(IsoFields.CardNumber,track2.take(16))
        put(IsoFields.ProcessCode,"000000")
        put(IsoFields.Amount, result[IsoFields.Amount] ?: "0")
        put(IsoFields.LastStan, lastStan)
        if (stan.isNullOrEmpty())
            put(IsoFields.Stan, (stanList[1]).toString())
        else
            put(IsoFields.Stan, stan)
        put(IsoFields.TransactionTime, SinaUtils.getTime())
        put(IsoFields.TransactionDate, SinaUtils.getDate())
        put(IsoFields.Serial, serial)
        put(IsoFields.Terminal, terminal)
        put(IsoFields.Merchant, merchant)
        put(IsoFields.TransactionDateTime, result[IsoFields.TransactionDate] + result[IsoFields.TransactionTime])
        put(IsoFields.TransactionStan, result[IsoFields.Stan]?:"")
        put(IsoFields.TransactionRrn, result[IsoFields.Rrn]?:"")
    }

    fun makeReverse(transaction: Transaction , lastStan : String , stan : String? = null) = HashMap<IsoFields, String>().apply {
        put(IsoFields.Mti,"0400")
        put(IsoFields.CardNumber, transaction.card ?: "")
        put(IsoFields.ProcessCode,"000000")
        put(IsoFields.Amount, transaction.amount ?: "")
        put(IsoFields.LastStan, lastStan)
        if (stan.isNullOrEmpty())
            put(IsoFields.Stan, (stanList[1]).toString())
        else
            put(IsoFields.Stan, stan)
        put(IsoFields.TransactionTime, SinaUtils.getTime())
        put(IsoFields.TransactionDate, SinaUtils.getDate())
        put(IsoFields.Serial, serial)
        put(IsoFields.Terminal, terminal)
        put(IsoFields.Merchant, merchant)
        put(IsoFields.TransactionDateTime, transaction.date ?: "")
        put(IsoFields.TransactionStan,transaction.stan ?: "")
    }
}
