package com.fanap.corepos.device.date.newland

import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.date.DateTimeInterface
import com.newland.nsdk.core.api.common.ModuleType
import com.newland.nsdk.core.api.internal.devicemanager.DeviceManager
import java.lang.Exception
import java.text.SimpleDateFormat

class NewLandDateTime : DateTimeInterface {

    override fun setDateTime(stamp: Int) {
        try {
            val mDeviceBasic: DeviceManager = NewLandSetup.moduleManager.getModule(ModuleType.DEVICE_MANAGER) as DeviceManager
            val formatter = SimpleDateFormat("yyyyMMddHHmmss")
            val date = formatter.parse(stamp.toString())
            mDeviceBasic.posDate = date
        } catch (e: Exception) { }

    }

    override fun setDateTime(stamp: String?) {
        try {
            val mDeviceBasic: DeviceManager = NewLandSetup.moduleManager.getModule(ModuleType.DEVICE_MANAGER) as DeviceManager
            val formatter = SimpleDateFormat("yyyyMMddHHmmss")
            val date = formatter.parse(stamp)
            mDeviceBasic.posDate = date
        } catch (e: Exception) { }    }
}
