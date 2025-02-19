package com.fanap.corepos.utils

import android.content.Context
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object NetworkChecker : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    var onConnectionLost = MutableLiveData(false)

    fun check(context: Context) {
        launch {
            while (true) {
                delay(10_000)
                if (!(Utils.isCellularDataEnabled(context) || Utils.isWifiConnected(context)))
                    onConnectionLost.postValue(true)
                else if (!(Utils.getBatteryLevel(context) >= 10 || Utils.isCharging(context)))
                    onConnectionLost.postValue(true)
                else
                    onConnectionLost.postValue(false)
            }
        }
    }

}