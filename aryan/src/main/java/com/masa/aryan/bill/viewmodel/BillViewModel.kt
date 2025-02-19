package com.masa.aryan.bill.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.sayan.SayanUtils
import com.masa.aryan.BuildConfig3
import com.masa.aryan.base.BaseViewModel

class BillViewModel(application: Application) : BaseViewModel(application) {

    var billId = ObservableField("")
    var payId = ObservableField("")
    var amount = ObservableField("")

    var onConfirmClicked: MutableLiveData<Boolean> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()

    fun setOnConfirmClicked() {
        if ((billId.get()?.length ?: 0) in 6..13) {
            if (Utils.getBasis11(billId.get()!!.take(billId.get()!!.length -1), billId.get()!!.substring(billId.get()!!.length-1).toInt())) {
                if ((payId.get()?.length ?: 0) in 6..13) {
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

    fun makeTransaction() =
        HashMap<IsoFields, String>().apply {
            put(IsoFields.Mti,"0100")
            put(IsoFields.ProcessCode, "190000")
            put(IsoFields.Stan, stanList[1].toString())
            put(IsoFields.TransactionTime, SayanUtils.getTime())
            put(IsoFields.TransactionDate, SayanUtils.getDate())
            put(IsoFields.NiiCode, DependencyManager.nii)
            put(IsoFields.ConditionCode, "00")
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Merchant, merchant)
            //            TODO SMART< TIAN , NEWLAND
//            put(IsoFields.Serial, "00001504P6000003690")
//            TODO NEWLAND
            put(IsoFields.Serial, serial)
            put(IsoFields.SoftwareVersion, BuildConfig3.VERSION_CODE.toString())
            put(IsoFields.TerminalLanguageCode, "0")
            put(IsoFields.BillId, billId.get()?:"")
            put(IsoFields.PayId, payId.get()?:"")
            put(IsoFields.ConnectionType, "4")
            put(IsoFields.Status, TransactionStatus.TransactionSent.name)
        }


}