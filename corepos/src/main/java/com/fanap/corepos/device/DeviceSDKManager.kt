package com.fanap.corepos.device

import android.content.Context
import android.util.Log
import com.basewin.services.ServiceManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.amp.AmpBeep
import com.fanap.corepos.device.beep.justtide.JusttideBeep
import com.fanap.corepos.device.beep.newland.NewLandBeep
import com.fanap.corepos.device.beep.smartpeak.SmartPeakBeep
import com.fanap.corepos.device.beep.tianyu.TianYuBeep
import com.fanap.corepos.device.date.DateTimeInterface
import com.fanap.corepos.device.date.amp.ApmDateTime
import com.fanap.corepos.device.date.justtide.JusttideDateTime
import com.fanap.corepos.device.date.newland.NewLandDateTime
import com.fanap.corepos.device.date.smartpeak.SmartPeakDateTime
import com.fanap.corepos.device.date.tinayu.TianYuDateTime
import com.fanap.corepos.device.date.xcheng.XchengDateTime
import com.fanap.corepos.device.hsm.HSMFactory
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.device.install.InstallInterface
import com.fanap.corepos.device.install.amp.AmpInstallApp
import com.fanap.corepos.device.install.justtide.JusttideInstallApp
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.fanap.corepos.device.mag_card.amp.AmpMagCardReader
import com.fanap.corepos.device.mag_card.justtide.JusttideMagCardReader
import com.fanap.corepos.device.mag_card.newland.NewLandMagCardReader
import com.fanap.corepos.device.mag_card.smartpeak.SmartPeakMagCard
import com.fanap.corepos.device.mag_card.tian.TianYouMagCard
import com.fanap.corepos.device.print.PrinterInterface
import com.fanap.corepos.device.print.amp.AmpPrinter
import com.fanap.corepos.device.print.justtide.JusttidePrinter
import com.fanap.corepos.device.print.newland.NewLandPrinter
import com.fanap.corepos.device.print.smartpeak.SmartPeakPrinter
import com.fanap.corepos.device.print.tianyu.TianYuPrinter
import com.fanap.corepos.device.print.xcheng.XchengPrinter
import com.fanap.corepos.device.serial.SerialInterface
import com.fanap.corepos.device.serial.newland.NewLandSerial
import com.fanap.corepos.device.serial.smartpeak.SmartPeakSerial
import com.fanap.corepos.device.serial.tianyu.TianYuSerial
import com.pos.device.SDKManager
import com.pos.device.sys.SystemManager

object DeviceSDKManager {

    fun getPrinterInterface(context: Context?): PrinterInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.NEWLAND.nameAndModel -> NewLandPrinter()
            DeviceName.TIANYU.nameAndModel -> TianYuPrinter()
            DeviceName.JUSTTIDE.nameAndModel -> JusttidePrinter(context)
            DeviceName.XCHENG.nameAndModel -> XchengPrinter(context)
            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel -> AmpPrinter()
            else -> null
        }
    }

    fun getPrintSmatPeakInterface(context: Context?) : PrinterInterface {
        return SmartPeakPrinter()
    }


    fun getMagCardInterfaceSmartPeak(context: Context?): MagCardInterface {
        return SmartPeakMagCard(context!!)
    }


    fun getMagCardInterface(context: Context?): MagCardInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.TIANYU.nameAndModel -> TianYouMagCard()

//            DeviceName.SMARTPEAK.nameAndModel -> SmartPeakMagCard(context!!)

            DeviceName.NEWLAND.nameAndModel -> NewLandMagCardReader()
            DeviceName.JUSTTIDE.nameAndModel -> JusttideMagCardReader()

//            DeviceName.XCHENG.nameAndModel -> XchengMagCardReader(context)

            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel -> AmpMagCardReader(
                context
            )

            else -> null
        }
    }

    fun getHSMSmartPeakInterface(context: Context? = null): HSMInterface {
        return HSMFactory.getSmartPeakHSM(context)
    }


    fun getHSMTianYuInterface(): HSMInterface {
        return HSMFactory.getTianYuHSM()
    }



    fun getHSMInterface(context: Context? = null): HSMInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.NEWLAND.nameAndModel -> HSMFactory.getNewLandHSM(context)
            DeviceName.TIANYU.nameAndModel -> HSMFactory.getTianYuHSM()
            DeviceName.SMARTPEAK.nameAndModel -> HSMFactory.getSmartPeakHSM(context)
//            DeviceName.XCHENG.nameAndModel -> HSMFactory.getXchengHSM(context)
            DeviceName.SMARTPEAK.nameAndModel -> HSMFactory.getSmartPeakHSM(context)

//            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel-> HSMFactory.getAmpHSM()
            else -> null
        }
    }


    fun getSmartPeakBeepInterface(): BeepInterface? {
        return SmartPeakBeep()
    }


    fun getBeepInterface(context: Context? = null): BeepInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.JUSTTIDE.nameAndModel -> JusttideBeep()
//            DeviceName.XCHENG.nameAndModel -> XchengBeep()
            DeviceName.TIANYU.nameAndModel -> TianYuBeep()
            DeviceName.SMARTPEAK.nameAndModel -> SmartPeakBeep()
            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel -> AmpBeep()
            DeviceName.NEWLAND.nameAndModel -> NewLandBeep()
            else -> null
        }
    }

    fun getDateTimeSmartPeakInterface(context: Context?): DateTimeInterface? {
        return SmartPeakDateTime(context)
    }

    fun getDateTimeInterface(context: Context?): DateTimeInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.NEWLAND.nameAndModel -> NewLandDateTime()
            DeviceName.TIANYU.nameAndModel -> TianYuDateTime(context)
            DeviceName.JUSTTIDE.nameAndModel -> JusttideDateTime()
            DeviceName.XCHENG.nameAndModel -> XchengDateTime(context)
            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel -> ApmDateTime()
            else -> null
        }
    }

    fun getInstallInterface(): InstallInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.JUSTTIDE.nameAndModel -> JusttideInstallApp()
//            DeviceName.XCHENG.nameAndModel -> XchengInstallApp()
            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel -> AmpInstallApp()
            else -> null
        }
    }

    fun getSerialSmartPeak(): SerialInterface? {
        return SmartPeakSerial()
    }

    fun getSerialTianYu(): SerialInterface{
        return TianYuSerial()
    }



    fun getSerialInterface(): SerialInterface? {
        return when (DeviceUtils.getDeviceName()) {
            DeviceName.NEWLAND.nameAndModel -> NewLandSerial()
            DeviceName.TIANYU.nameAndModel -> TianYuSerial()
//            DeviceName.JUSTTIDE.nameAndModel -> JusttideSerial()
//            DeviceName.XCHENG.nameAndModel -> XchengSerial()
            DeviceName.SMARTPEAK.nameAndModel -> SmartPeakSerial()

//            DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel, DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel-> AmpSerial()
            else -> null
        }
    }

    fun Smartsdk(context: Context?) {
        ServiceManager.getInstence().init(context!!)

    }

    fun setupSDK(context: Context?) {
        when (DeviceUtils.getDeviceName()) {

            DeviceName.NEWLAND.nameAndModel -> NewLandSetup.setup(context!!)
            DeviceName.TIANYU.nameAndModel -> TianYuSetup.setup(context!!)
            //            DeviceName.SMARTPEAK.nameAndModel -> ServiceManager.getInstence().init(context!!)
            /*     DeviceName.JUSTTIDE.nameAndModel -> JusttideSetup.startBindService(context)
               DeviceName.AMP.nameAndModel, DeviceName.AMP6500.nameAndModel, DeviceName.AMP8000.nameAndModel,*/
            DeviceName.AMP8200.nameAndModel, DeviceName.AMP_DEBUG1.nameAndModel, DeviceName.AMP_DEBUG2.nameAndModel -> {
                SDKManager.init(context) {
                    Log.d("AMP INIT", "TRUE")
                    SystemManager.setHomeRecentAppKeyEnable(false, false)
                    SystemManager.setDisableStatusBar(true)
                    SystemManager.writeLauncherPrioAppInfo(
                        "com.fanap.sina",
                        "com.fanap.sina.main.MainActivity"
                    )
                    SystemManager.setDefaultLauncher(
                        "com.fanap.sina",
                        "com.fanap.sina.main.MainActivity"
                    )
                    DeviceConfig.onServiceConnected.postValue(true)
                }
            }

        }
    }
}