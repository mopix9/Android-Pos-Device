package com.fanap.corepos.device

import androidx.lifecycle.MutableLiveData
import com.justtide.service.dev.aidl.DeviceProvider
import com.justtide.service.dev.aidl.util.UtilsProvider
import com.justtider.justtideserver.DeviceDateProvider

object DeviceConfig {
    lateinit var deviceProvider: DeviceProvider // for justtide device
    lateinit var utils: UtilsProvider // for justtide device
    lateinit var deviceDateProvider: DeviceDateProvider // for justtide device

    var onServiceConnected = MutableLiveData<Boolean>()
}