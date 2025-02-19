package com.fanap.corepos.iso.packer.aryan

import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.iso.model.AryanIsoField
import com.fanap.corepos.iso.packer.aryan.schema.AryanAdviseReversePackSchema
import com.fanap.corepos.iso.packer.base.Packer
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.aryan.AryanUtils
import java.util.TreeMap

class AryanReversePacker  : Packer() {

    override fun pack(): ByteArray? {
        return try {
            val fields: TreeMap<Int, String?> = message
            val tempByteList = mutableListOf<Byte>()

            tempByteList.addAll(IsoUtil.hexStringToByteArray(AryanUtils.TPDU).asList())
            tempByteList.addAll(IsoUtil.hexStringToByteArray(fields[0]).asList()) // Mti
            tempByteList.addAll(IsoUtil.hexStringToByteArray(IsoUtil.binaryToHex(IsoUtil.createBitmap(fields.keys))).asList()) // Bitmap

            for ((key, value) in fields) { // Loop and append fields
                if (key > 1) {
                    val fieldSchema: AryanIsoField = AryanAdviseReversePackSchema.getIsoFieldInfo(key)
                    when (key) {
                        3, 4, 11, 12, 13, 24, 25 ->
                            tempByteList.addAll(IsoUtil.hexStringToByteArray(IsoUtil.padleft(value, fieldSchema.length, '0')).asList())
                        41, 49 ->
                            tempByteList.addAll(IsoUtil.padleft(value, fieldSchema.length, '0').toByteArray(Charsets.US_ASCII).asList())
                        42 ->
                            tempByteList.addAll(IsoUtil.padright(value, fieldSchema.length, ' ').toByteArray(Charsets.US_ASCII).asList())
                        48 -> {
                            val length_48: String = IsoUtil.padleft((value!!.length + 2).toString(), 4, '0')
                            tempByteList.addAll(IsoUtil.hexStringToByteArray(length_48).asList()) // size of field 48

                            val lengthOfTag = IsoUtil.padleft((value.length+1).toString(), 2, '0')
                            tempByteList.addAll(IsoUtil.hexStringToByteArray(lengthOfTag +"15").asList())
                            tempByteList.addAll(value.toByteArray(Charsets.US_ASCII).asList())
                        }
                    }
                }
            }

//            todo newland
//            val mac: ByteArray = DeviceSDKManager.getHSMInterface(null)?.calcMac(tempByteList.toByteArray().sliceArray(5 until tempByteList.size))!!
//TODO SMART

//            val mac = ServiceManager.getInstence().pinpad.calcMACByArea(40, 50, tempByteList.toByteArray().sliceArray(5 until tempByteList.size).toString(), BwPinpadSource.MAC_MOD919).toByteArray()
            //            TODO TIANYU

            val mac: ByteArray = DeviceSDKManager.getHSMTianYuInterface().calcMac(tempByteList.toByteArray().sliceArray(5 until tempByteList.size))
          /*  var smartPosApi: ITYSmartPosApi = TianYuSetup.smartPosApi

            val mac:ByteArray = smartPosApi.calculateMac(
                (tempByteList.toByteArray().sliceArray(5 until tempByteList.size)) ,
                PinPadConstant.MacAlgorithm.X919
            ).toString().toByteArray()*/

            tempByteList.addAll(IsoUtil.bytesToHex(mac.sliceArray(0..3)).toByteArray(Charsets.US_ASCII).asList())

            val result = mutableListOf<Byte>()
            result.addAll(IsoUtil.hexStringToByteArray(IsoUtil.padleft(Integer.toHexString(tempByteList.size).toString(), 4, '0')).asList())
            result.addAll(tempByteList)

            result.toByteArray()
        } catch (e: Exception) {
            null
        }
    }
}
