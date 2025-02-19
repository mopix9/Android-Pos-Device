package com.fanap.corepos.device.serial.newland

import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.serial.SerialInterface
import com.newland.nsdk.core.api.common.ModuleType
import com.newland.nsdk.core.api.internal.devicemanager.DeviceManager

class NewLandSerial : SerialInterface {

    override fun getSerial(): String {
        var mDeviceBasic: DeviceManager = NewLandSetup.moduleManager.getModule(ModuleType.DEVICE_MANAGER) as DeviceManager
        val deviceInfo = mDeviceBasic.deviceInfo
        return deviceInfo.sn
//        return  "NA8900167985"    // for hard code sn
    }
}
