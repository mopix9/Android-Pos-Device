package com.fanap.corepos.device.beep.tianyu

import android.util.Log
import com.fanap.corepos.device.TianYuSetup
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi

class TianYuBeep : BeepInterface {

    var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi

    override fun beep(beepType: BeepType?) {
        smartPosApi.beep()
        Log.d("beep", "ok")
    }
}
