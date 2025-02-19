package com.fanap.corepos.iso.manager.sina

import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.packer.base.Packer
import com.fanap.corepos.iso.socket.Sender
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.sina.SinaUtils

class SinaTransaction : IIso {

    private val isoMakerFactory = DependencyManager.provideIsoMakerFactory()
    private val iPackerFactory = DependencyManager.provideIsoPackerFactory()
    private val iUnPackerFactory = DependencyManager.provideIsoUnPackerFactory()
private val hsm: HSMInterface? = DeviceSDKManager.getHSMSmartPeakInterface()
    //    private val hsm: HSMInterface? = DeviceSDKManager.getHSMInterface()


    override suspend fun doTransaction(msg: HashMap<IsoFields, String>): HashMap<IsoFields, String>? {
        try {
            val isoMaker : Maker = isoMakerFactory.getIsoMaker(msg)
            val isoMsg = isoMaker.makeIso(msg)

            val isoPacker: Packer = iPackerFactory.getPacker(msg)
            isoPacker.message = isoMsg.fields

            val bytes = isoPacker.pack()

            val sender = Sender(bytes!!)
            val result = sender.send()

            val receivedMac = IsoUtil.bytesToHex(hsm!!.calcMac(result!!.sliceArray( SinaUtils.NII_LENGTH + 4 ..result.size - 9)))

            val isoUnPacker: UnPacker = iUnPackerFactory.getUnPacker(result)
            isoUnPacker.message = result

            val returnResult = isoUnPacker.unpack()

            return if (returnResult[IsoFields.Mti] == "0810")
                returnResult
            else {
                if (returnResult[IsoFields.Mac] == receivedMac)
                    returnResult
                else {
                    returnResult[IsoFields.Response] = "12"
                    returnResult
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            return null
        }
    }
}