package com.fanap.corepos.device.serial.tianyu

import com.fanap.corepos.device.TianYuSetup
import com.fanap.corepos.device.serial.SerialInterface
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi

class TianYuSerial() : SerialInterface {

    var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi


    override fun getSerial(): String {


        val sn = smartPosApi.deviceSN
        return sn
    }
}