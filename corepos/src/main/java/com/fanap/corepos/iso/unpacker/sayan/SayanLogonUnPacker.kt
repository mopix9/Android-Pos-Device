package com.fanap.corepos.iso.unpacker.sayan

import com.fanap.corepos.iso.model.*
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.unpacker.sayan.schema.SayanLogonUnPackSchema
import com.fanap.corepos.iso.utils.IranSystemUtil
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import java.util.*

class SayanLogonUnPacker : UnPacker() {

    override fun unpack(): HashMap<IsoFields, String> {

        val message: CharArray = IsoUtil.bytesToHex(message).toCharArray()
        val msg = HashMap<IsoFields, String>()
        var pointer = 34 // current location in message after adding mti and bitmap in msg

        msg[IsoFields.Mti] = message.sliceArray(14..17).concatToString()
        msg[IsoFields.Bitmap] = message.sliceArray(18..33).concatToString()
        val bitmap: List<Int> = IsoUtil.unpackBitmap(msg[IsoFields.Bitmap])

        for (i in bitmap.indices)
            if (bitmap[i] == 1) {
                val schema: SayanIsoField = SayanLogonUnPackSchema.getIsoFieldInfo(i + 1)
            var fieldLength: Int
            when (schema.type) {
                SayanFieldTypes.N, SayanFieldTypes.AN,SayanFieldTypes.ANS ->{
                    when(schema.lengthType){
                        IsoFieldLengthType.LLL ->{
                            fieldLength = message.sliceArray(pointer until pointer+4).concatToString().toInt() * 2
                            pointer += 4
                            msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+fieldLength).concatToString()
                            pointer += fieldLength
                        }
                        IsoFieldLengthType.CONST ->{
                            if (schema.dataType == SayanDataTypes.ASCII){
                                fieldLength = schema.length * 2
                                msg[getFieldName(i+1)] = IsoUtil.hexToAscii(message.sliceArray(pointer until pointer+fieldLength).concatToString())
                                pointer += fieldLength
                            }else {
                                msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+schema.length).concatToString()
                                pointer += schema.length
                            }
                        }

                        else -> {}
                    }
                }

                else -> {}
            }
        }


        msg[IsoFields.PinKey] = msg[IsoFields.Buffer2]?.toCharArray()?.sliceArray(0 until 32)?.concatToString() ?: ""
        msg[IsoFields.MacKey] = msg[IsoFields.Buffer2]?.toCharArray()?.sliceArray(32 until 64)?.concatToString() ?: ""
        msg[IsoFields.DataKey] = msg[IsoFields.Buffer2]?.toCharArray()?.sliceArray(64 until 96)?.concatToString() ?: ""
        msg[IsoFields.TransactionDateTime] = IsoUtil.hexToAscii(getFieldWithTagName(msg[IsoFields.Buffer1],"50"))

        return msg
        //006B6000000009081020380000028100049200000000011739281214303030303031393137370019155032303231313231353135303831390257310048FB3D8859611849C3920F7CB55D5DBB8209C640C6D3C42582127EF2E96BF1C0CDCE7C1380AC286E579435E12F9F138722
    }

    private fun getFieldName(field : Int) : IsoFields{
        return when(field){
            3 -> IsoFields.ProcessCode
            11 -> IsoFields.Stan
            12 -> IsoFields.TransactionTime
            13 -> IsoFields.TransactionDate
            39 -> IsoFields.Response
            41 -> IsoFields.Terminal
            48 -> IsoFields.Buffer1
            62 -> IsoFields.Buffer2
            else -> throw IllegalArgumentException()
        }
    }
}