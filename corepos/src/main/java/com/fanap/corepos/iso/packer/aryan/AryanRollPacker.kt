package com.fanap.corepos.iso.packer.aryan

import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.iso.model.AryanIsoField
import com.fanap.corepos.iso.packer.base.Packer
import com.fanap.corepos.iso.unpacker.aryan.schema.AryanInquiryUnPackSchema
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.sayan.SayanUtils
import java.util.TreeMap

class AryanRollPacker : Packer() {

    override fun pack(): ByteArray? {
        return try {
            val fields: TreeMap<Int, String?> = message
            val tempByteList = mutableListOf<Byte>()

            tempByteList.addAll(IsoUtil.hexStringToByteArray(SayanUtils.TPDU).asList())
            tempByteList.addAll(IsoUtil.hexStringToByteArray(fields[0]).asList()) // Mti
            tempByteList.addAll(IsoUtil.hexStringToByteArray(IsoUtil.binaryToHex(IsoUtil.createBitmap(fields.keys))).asList()) // Bitmap

            for ((key, value) in fields) { // Loop and append fields
                if (key > 1) {
                    val fieldSchema: AryanIsoField = AryanInquiryUnPackSchema.getIsoFieldInfo(key)
                    when (key) {
                        3, 11, 12, 13, 24 ->
                            tempByteList.addAll(IsoUtil.hexStringToByteArray(IsoUtil.padleft(value, fieldSchema.length, '0')).asList())
                        41 ->
                            tempByteList.addAll(IsoUtil.padleft(value, fieldSchema.length, '0').toByteArray(Charsets.US_ASCII).asList())
                        42 ->
                            tempByteList.addAll(IsoUtil.padright(value, fieldSchema.length, ' ').toByteArray(Charsets.US_ASCII).asList())
                        48 -> {
                            val data  = value!!.split("~")
                            val length_48_temp = data[0].length + data[1].length + data[2].length + data[3].length + data[4].length + (data.size * 2)
                            val length_48: String = IsoUtil.padleft(length_48_temp.toString(), 4, '0')
                            tempByteList.addAll(IsoUtil.hexStringToByteArray(length_48).asList()) // size of field 48

                            data.forEachIndexed { index, it ->
                                val lengthOfTag = IsoUtil.padleft((it.length+1).toString(), 2, '0')
                                tempByteList.addAll(IsoUtil.hexStringToByteArray(lengthOfTag).asList())
                                tempByteList.addAll(IsoUtil.hexStringToByteArray(getTagOfData(index)).asList())
                                tempByteList.addAll(it.toByteArray(Charsets.US_ASCII).asList())
                            }
                        }
                    }
                }
            }

//            TODO NEWLAND
//            val mac: ByteArray = DeviceSDKManager.getHSMInterface(null)?.calcMac(tempByteList.toByteArray().sliceArray(5 until tempByteList.size))!!
//TODO SMARTPEAK

//            val mac = ServiceManager.getInstence().pinpad.calcMACByArea(40, 50, tempByteList.toByteArray().sliceArray(5 until tempByteList.size).toString(), BwPinpadSource.MAC_MOD919).toByteArray()
            //            TODO TIANYU

            val mac: ByteArray = DeviceSDKManager.getHSMInterface(null)?.calcMac(tempByteList.toByteArray().sliceArray(5 until tempByteList.size))!!

            tempByteList.addAll(IsoUtil.bytesToHex(mac.sliceArray(0..3)).toByteArray(Charsets.US_ASCII).asList())

            val result = mutableListOf<Byte>()
            result.addAll(IsoUtil.hexStringToByteArray(IsoUtil.padleft(Integer.toHexString(tempByteList.size).toString(), 4, '0')).asList())
            result.addAll(tempByteList)

            result.toByteArray()
        } catch (e: Exception) {
            null
        }
    }

    private fun getTagOfData(index: Int): String {
        return when(index){
            0 -> "01"
            1 -> "02"
            2 -> "03"
            3 -> "12"
            4 -> "15"
            else -> throw IllegalArgumentException("Undefined Tag!")
        }
    }
}