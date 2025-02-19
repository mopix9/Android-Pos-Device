package com.fanap.sina.bill

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseViewModel

class BillViewModel(application: Application) : BaseViewModel(application) {

    var billId = ObservableField("")
    var payId = ObservableField("")
    var amount = ObservableField("")

    var onConfirmClicked: MutableLiveData<Boolean> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()

    fun setOnConfirmClicked() {
        if (billId.get()?.length ?: 0 in 6..13) {
            if (Utils.getBasis11(billId.get()!!.take(billId.get()!!.length -1), billId.get()!!.substring(billId.get()!!.length-1).toInt())) {
                if (payId.get()?.length ?: 0 in 6..13) {
                    if (Utils.getBasis11(payId.get()!!.take(payId.get()!!.length -2 ), payId.get()!!.substring(payId.get()!!.length-2, payId.get()!!.length-1).toInt())) {
                        onConfirmClicked.postValue(true)
                        amount.set(
                            (payId.get()!!
                                .substring(0, payId.get()!!.length - 5) + "000").replaceFirst(
                                Regex("^0+(?!$)"),
                                ""
                            )
                        )
                    }else
                        onError.postValue("شناسه پرداخت معتبر نیست")
                } else
                    onError.postValue("طول شناسه پرداخت معتبر نیست")
            }else
                onError.postValue("شناسه قبض معتبر نیست")
        } else
            onError.postValue("طول شناسه قبض معتبر نیست")
    }

}