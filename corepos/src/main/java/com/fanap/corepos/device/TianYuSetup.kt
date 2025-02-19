package com.fanap.corepos.device

import android.content.Context
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi

object TianYuSetup {

    //    lateinit var moduleManager: NSDKModuleManager
  lateinit   var smartPosApi: ITYSmartPosApi
    fun setup(context: Context) {
        smartPosApi = ITYSmartPosApi.get(
            context
        )
        try {

smartPosApi.deviceSN

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
