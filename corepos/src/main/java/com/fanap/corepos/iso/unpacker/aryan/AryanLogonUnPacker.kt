package com.fanap.corepos.iso.unpacker.aryan

import com.fanap.corepos.iso.model.*
import com.fanap.corepos.iso.unpacker.aryan.schema.AryanLogonUnPackSchema
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.unpacker.sayan.schema.SayanLogonUnPackSchema
import com.fanap.corepos.iso.utils.IranSystemUtil
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import java.util.*

class AryanLogonUnPacker : UnPacker() {

    override fun unpack(): HashMap<IsoFields, String> {

        val message: CharArray = IsoUtil.bytesToHex(message).toCharArray()
        val msg = HashMap<IsoFields, String>()
        var pointer = 34 // current location in message after adding mti and bitmap in msg

        msg[IsoFields.Mti] = message.sliceArray(14..17).concatToString()
        msg[IsoFields.Bitmap] = message.sliceArray(18..33).concatToString()
        val bitmap: List<Int> = IsoUtil.unpackBitmap(msg[IsoFields.Bitmap])

        for (i in bitmap.indices)
            if (bitmap[i] == 1) {
                val schema: AryanIsoField = AryanLogonUnPackSchema.getIsoFieldInfo(i + 1)
                var fieldLength: Int
                when (schema.type) {
                    AryanFieldTypes.N, AryanFieldTypes.AN,AryanFieldTypes.ANS ->{
                        when(schema.lengthType){
                            IsoFieldLengthType.LLL ->{
                                fieldLength = message.sliceArray(pointer until pointer+4).concatToString().toInt() * 2
                                pointer += 4
                                msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+fieldLength).concatToString()
                                pointer += fieldLength
                            }
                            IsoFieldLengthType.CONST ->{
                                if (schema.dataType == AryanDataTypes.ASCII){
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



        if(msg[IsoFields.Response] == "00") {
            msg[IsoFields.PinKey] =
                msg[IsoFields.Buffer2]?.toCharArray()?.sliceArray(0 until 32)?.concatToString()
                    ?: ""
            msg[IsoFields.MacKey] =
                msg[IsoFields.Buffer2]?.toCharArray()?.sliceArray(32 until 64)?.concatToString()
                    ?: ""
            msg[IsoFields.DataKey] =
                msg[IsoFields.Buffer2]?.toCharArray()?.sliceArray(64 until 96)?.concatToString()
                    ?: ""
        }
        msg[IsoFields.TransactionDateTime] = IsoUtil.hexToAscii(getFieldWithTagName(msg[IsoFields.Buffer1],"50"))

        return msg
    }

    private fun getFieldName(field : Int) : IsoFields{
        return when(field){
            3 -> IsoFields.ProcessCode
            11 -> IsoFields.Stan
            12 -> IsoFields.TransactionTime
            13 -> IsoFields.TransactionDate
            37 -> IsoFields.Rrn
            39 -> IsoFields.Response
            41 -> IsoFields.Terminal
            48 -> IsoFields.Buffer1
            62 -> IsoFields.Buffer2
            else -> throw IllegalArgumentException()
        }
    }
}