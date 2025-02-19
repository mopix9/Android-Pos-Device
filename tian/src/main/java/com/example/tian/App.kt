package com.example.tian

import android.app.Application
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.di.IsoProtocol
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.receipt.util.ReceiptProtocol
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class APP : Application(){
    override fun onCreate() {
        super.onCreate()

        DependencyManager.protocol = IsoProtocol.ARYAN

        ReceiptFactory.protocol = ReceiptProtocol.ARYAN

        DeviceSDKManager.setupSDK(this)
    }

}