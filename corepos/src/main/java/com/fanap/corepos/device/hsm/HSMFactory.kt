package com.fanap.corepos.device.hsm

import android.content.Context
import com.fanap.corepos.device.hsm.aryan.newland.NewLandHSMAryan
import com.fanap.corepos.device.hsm.aryan.smarpeak.SmartPeakHSM
import com.fanap.corepos.device.hsm.aryan.tiantu.TianYuHSM
import com.fanap.corepos.device.hsm.dotin.amp.AmpHSMDotin
import com.fanap.corepos.device.hsm.dotin.justtide.JusttideHSMDotin
import com.fanap.corepos.device.hsm.sayan.amp.AmpHSMSayan
import com.fanap.corepos.device.hsm.sayan.justtide.JusttideHSMSayan
import com.fanap.corepos.device.hsm.sina.amp.AmpHSMSina
import com.fanap.corepos.device.hsm.sina.justtide.JusttideHSMSina
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.di.IsoProtocol

object HSMFactory {

    private var protocol : IsoProtocol = DependencyManager.protocol

    fun getAmpHSM(): HSMInterface{
        return when(protocol){
            IsoProtocol.SINA -> AmpHSMSina()
            IsoProtocol.DOTIN -> AmpHSMDotin()
            IsoProtocol.SAYAN -> AmpHSMSayan()
            IsoProtocol.ARYAN -> AmpHSMSayan()
            else -> AmpHSMSayan()
        }
    }

    fun getJusttideHSM(): HSMInterface{
        return when(protocol){
            IsoProtocol.SINA -> JusttideHSMSina()
            IsoProtocol.DOTIN -> JusttideHSMDotin()
            IsoProtocol.SAYAN -> JusttideHSMSayan()
            IsoProtocol.ARYAN -> JusttideHSMSayan()
            else -> JusttideHSMSayan()
        }
    }



    fun getSmartPeakHSM(context: Context?): HSMInterface{
        return when(protocol){
            IsoProtocol.ARYAN -> SmartPeakHSM()
            else -> SmartPeakHSM()
        }
    }

    fun getTianYuHSM(): HSMInterface{
        return when(protocol){
            IsoProtocol.ARYAN -> TianYuHSM()
            else -> TianYuHSM()
        }
    }

    fun getNewLandHSM(context: Context?): HSMInterface{
        return when(protocol){
         /* IsoProtocol.SINA -> XchengHSMSina(context)
            IsoProtocol.DOTIN -> XchengHSMDotin(context)
            IsoProtocol.SAYAN -> XchengHSMSayan(context)*/

            IsoProtocol.ARYAN -> NewLandHSMAryan()
            else -> NewLandHSMAryan()
        }
    }

}
