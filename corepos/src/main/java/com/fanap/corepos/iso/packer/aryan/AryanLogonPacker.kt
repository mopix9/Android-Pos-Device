package com.fanap.corepos.iso.packer.aryan

import com.fanap.corepos.iso.model.AryanIsoField
import com.fanap.corepos.iso.packer.aryan.schema.AryanLogonPackSchema
import com.fanap.corepos.iso.packer.base.Packer
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.aryan.AryanUtils
import java.util.*

class AryanLogonPacker : Packer() {

    override fun pack(): ByteArray? {
        return try {
             val fields: TreeMap<Int, String?> = message
            val tempByteList = mutableListOf<Byte>()

            tempByteList.addAll(IsoUtil.hexStringToByteArray(AryanUtils.TPDU).asList())
            tempByteList.addAll(IsoUtil.hexStringToByteArray(fields[0]).asList()) // Mti
            tempByteList.addAll(IsoUtil.hexStringToByteArray(IsoUtil.binaryToHex(IsoUtil.createBitmap(fields.keys).dropLast(1)+"0")).asList()) // Bitmap

            for ((key, value) in fields) { // Loop and append fields
                if (key > 1) {
                    val fieldSchema: AryanIsoField = AryanLogonPackSchema.getIsoFieldInfo(key)
                    when (key) {
                        3, 11, 12, 13, 24 ->
                            tempByteList.addAll(IsoUtil.hexStringToByteArray(IsoUtil.padleft(value, fieldSchema.length, '0')).asList())
                        48 -> {
                            val data  = value!!.split("~")
                            val length_48_temp = data[0].length + data[1].length + data[2].length + (data.size * 2)
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
// new Attached from SinaLogOn

        /*    val mac: ByteArray = IsoUtil.DoMac(
                tempByteList.toByteArray()
                , IsoUtil.getDefaultMacKey()
            )
            tempByteList.addAll(mac.asList())*/

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
            else -> throw IllegalArgumentException("Undefined Tag!")
        }
    }
}