package com.fanap.sina

import android.app.Application
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.hsm.util.GlobalData
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.di.IsoProtocol
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.receipt.util.ReceiptProtocol
import com.fanap.corepos.tms.utils.UpdateChecker
import com.fanap.corepos.utils.NetworkChecker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.InternalCoroutinesApi

@HiltAndroidApp
class App : Application() {


    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        DependencyManager.protocol = IsoProtocol.SINA
        ReceiptFactory.protocol = ReceiptProtocol.SINA

        DeviceSDKManager.setupSDK(this)

        UpdateChecker.check(applicationContext, BuildConfig.VERSION_CODE.toString())
        NetworkChecker.check(applicationContext)

        GlobalData.getInstance().init(applicationContext)
    }
}