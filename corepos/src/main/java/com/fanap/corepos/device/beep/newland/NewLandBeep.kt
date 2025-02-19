package com.fanap.corepos.device.beep.newland

import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.newland.nsdk.core.api.common.ModuleType
import com.newland.nsdk.core.api.internal.beeper.Beeper

class NewLandBeep : BeepInterface {

    override fun beep(beepType: BeepType?) {
        val beeper: Beeper = NewLandSetup.moduleManager.getModule(ModuleType.BEEPER) as Beeper
        beeper.beep(10, 10)
    }
}
