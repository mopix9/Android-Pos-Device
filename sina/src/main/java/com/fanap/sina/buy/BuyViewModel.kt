package com.fanap.sina.buy

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.database.base.IUserRepository
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseViewModel
import com.fanap.corepos.utils.sina.SinaUtils

class BuyViewModel(application: Application) : BaseViewModel(application) {

    var amount = ObservableField("")
    var onConfirmClicked: MutableLiveData<Boolean> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()

    init {
        //Utils.openKeyboard(getApplication())
    }

    fun setOnConfirmClicked() {
        //Utils.hideKeyboard(getApplication())
        val temp = amount.get()?.replace(",","") ?: ""
        if (temp.isNotBlank()){
            if (temp.toLong() < 500_000_000){
                onConfirmClicked.postValue(true)
            } else onError.postValue("لطفا مبلغ معتبر وارد کنید.")
        } else onError.postValue("لطفا مبلغ معتبر وارد کنید.")
    }

    fun makeTransaction(track2: String, pinBlock: String) =
        HashMap<IsoFields, String>().apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.ProcessCode, "000000")
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
            put(IsoFields.Amount, amount.get()?.replace(",","")?: "0")
            put(IsoFields.Status, TransactionStatus.TransactionSent.name)

        }

    fun makeReceipt(track2: String, data: HashMap<IsoFields, String>): HashMap<IsoFields, String> {
        val amount = Utils.removeZeros(data[IsoFields.Amount]?: "")

        data.apply {
            put(IsoFields.Type,TransactionType.Buy.name)
            put(IsoFields.Amount,amount)
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, StringBuilder(SinaUtils.getTime().take(4)).insert(2, ":").toString())
            put(IsoFields.TransactionDate, SinaUtils.getPersianDate())
            put(IsoFields.TypeName, "خرید-مشتری")
            put(IsoFields.Status, TransactionReceiptStatus.Success.name)
            put(IsoFields.Track2,track2)
        }
        return data
    }
}