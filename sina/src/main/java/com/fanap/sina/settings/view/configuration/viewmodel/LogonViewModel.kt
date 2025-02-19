package com.fanap.sina.settings.view.configuration.viewmodel

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

class LogonViewModel  : ViewModel() {

    var protocol: ObservableField<Int> = ObservableField(0)
    var serialNo: ObservableField<String> = ObservableField("")
    var terminalNo:ObservableField<String> = ObservableField("")
    var merchantNo:ObservableField<String> = ObservableField("")
    var ip:ObservableField<String> = ObservableField("")
    var tms:ObservableField<String> = ObservableField("")
    var tmsPort:ObservableField<String> = ObservableField("")
    var port:ObservableField<String> = ObservableField("")
    var onConfirmClicked: MutableLiveData<Boolean> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()

    fun setOnConfirmClicked() {
        if (serialNo.get() != "") {
            if (terminalNo.get() != "") {
                if (merchantNo.get() != "") {
                    if (ip.get() != "" && Patterns.IP_ADDRESS.matcher(ip.get()).matches()) {
                        if (port.get() != "") {
                            if (tms.get() != "" && Patterns.IP_ADDRESS.matcher(tms.get()).matches()) {
                                if (port.get() != "") {
                                    onConfirmClicked.postValue(true)
                                }else  onError.postValue("شماره port تی.ام.اس را وارد کنید.")
                            } else onError.postValue("لطفا یک آدرس تی.ام.اس معتبر وارد کنید.")
                        } else onError.postValue("شماره port را وارد کنید.")
                    } else onError.postValue("لطفا یک آدرس ip معتبر وارد کنید.")
                } else onError.postValue("شناسه فروشگاه را وارد کنید.")
            } else onError.postValue("شماره پایانه را وارد کنید.")
        } else onError.postValue("سریال پایانه را وارد کنید.")
    }

    fun makeTmsAddress(): String {
        return "${if (protocol.get() == 0) "http" else "https"}://${tms.get()}:${tmsPort.get()}/"
    }

    fun setTmsAddress(address: String) {
        try {
            val temp = address.replace("/", "").split(":")
            if (temp[0] == "http") protocol.set(0) else protocol.set(1)
            tms.set(temp[1])
            tmsPort.set(temp[2])
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
