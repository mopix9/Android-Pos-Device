package com.fanap.sina.settings.view.seller.report.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.sina.base.BaseViewModel
import com.fanap.sina.utils.SinaResponse
import com.fanap.corepos.utils.sina.SinaUtils

class SingleReportViewModel(application: Application) : BaseViewModel(application) {

    var isSearchOpen: ObservableBoolean = ObservableBoolean(false)
    var focusOnEditText: MutableLiveData<Boolean> = MutableLiveData()

    fun setIsSearchOpen() {
        isSearchOpen.set(!isSearchOpen.get())
        focusOnEditText.postValue(isSearchOpen.get())
    }

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
}
