package com.fanap.corepos.iso.manager.aryan
import android.util.Log
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
import com.fanap.corepos.utils.aryan.AryanUtils


class AryanTransaction() : IIso {


 private val isoMakerFactory = DependencyManager.provideIsoMakerFactory()
 private val iPackerFactory = DependencyManager.provideIsoPackerFactory()
 private val iUnPackerFactory = DependencyManager.provideIsoUnPackerFactory()

// TODO NEWLAND
// private val hsm: HSMInterface? = DeviceSDKManager.getHSMInterface()
//
// TODO SMARTPEAK
// private val hsm: HSMInterface = DeviceSDKManager.getHSMSmartPeakInterface()

// TODO TIANYU
 private val hsm: HSMInterface = DeviceSDKManager.getHSMTianYuInterface()
// private val hsm: HSMInterface = DeviceSDKManager.getHSMTianYuInterface(context)
// private val hsm: HSMInterface = DeviceSDKManager.getHSMSmartPeakInterface()


 override suspend fun doTransaction(msg: HashMap<IsoFields, String>): HashMap<IsoFields, String>? {
  try {
   val isoMaker : Maker = isoMakerFactory.getIsoMaker(msg)
   val isoMsg = isoMaker.makeIso(msg)

   Log.d("isoMaker","salama" + isoMaker.toString())
   Log.d("isoMsg", isoMsg.toString())

   val isoPacker: Packer = iPackerFactory.getPacker(msg)
   isoPacker.message = isoMsg.fields

   Log.d("isoPacker", isoPacker.toString())

   Log.d("isoPackerMsg", isoPacker.message.toString())

   val bytes = isoPacker.pack()
   Log.d("bytes", bytes.toString())

   val sender = Sender(bytes!!)
   val result = sender.send()

   Log.d("resultt",result.toString())




 val receivedMac = IsoUtil.bytesToHex(hsm.calcMac(result!!.sliceArray( AryanUtils.TPDU_LENGTH + 2 .. result.size - 9)))!!

//      val receivedMac = IsoUtil.bytesToHex(hsm.calcMac(result!!.sliceArray( AryanUtils.TPDU_LENGTH + 2 .. result.size - 9)))

//      Log.d("receivedMac", receivedMac)

/*
      val isoUnPacker: UnPacker = iUnPackerFactory.getUnPacker(result)
   isoUnPacker.message = result*/

      val isoUnPacker: UnPacker = iUnPackerFactory.getUnPacker(result)
      isoUnPacker.message = result

   Log.d("isoUnPackerMsg", isoUnPacker.toString())
   Log.d("isoUnPackerMsg", result.toString())

   val returnResult = isoUnPacker.unpack()
   Log.d("returnResultt", returnResult.toString())



      return if (returnResult[IsoFields.Mti] == "0810")
          returnResult
      else {
          returnResult

          /*if (returnResult[IsoFields.Mac] == receivedMac)
              returnResult
          else {
              returnResult[IsoFields.Response] = "12"
              returnResult
          }*/
      }
  }catch (e:Exception){
      e.printStackTrace()
      return null
  }
 }
}