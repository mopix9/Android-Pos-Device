package com.fanap.corepos.device

import android.content.Context
import com.newland.nsdk.core.api.common.utils.LogLevel
import com.newland.nsdk.core.api.internal.NSDKModuleManager
import com.newland.nsdk.core.internal.NSDKModuleManagerImpl

object NewLandSetup {

    lateinit var moduleManager: NSDKModuleManager

    fun setup(context : Context){
        moduleManager = NSDKModuleManagerImpl.getInstance()
        moduleManager.setDebugMode(LogLevel.VERBOSE)
        try {
            moduleManager.init(context)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}
