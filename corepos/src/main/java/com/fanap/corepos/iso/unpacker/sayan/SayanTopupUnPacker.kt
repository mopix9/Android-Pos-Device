package com.fanap.corepos.iso.unpacker.sayan

import com.fanap.corepos.iso.model.IsoFieldLengthType
import com.fanap.corepos.iso.model.SinaFieldTypes
import com.fanap.corepos.iso.model.SinaIsoField
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.unpacker.sayan.schema.SayanPurchaseUnPackSchema
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import java.util.HashMap

class SayanTopupUnPacker  : UnPacker() {

    override fun unpack(): HashMap<IsoFields, String> {

        val message: CharArray = IsoUtil.bytesToHex(message).toCharArray()
        val msg = HashMap<IsoFields, String>()

        var pointer = 32 // current location in message after adding mti and bitmap in msg

        msg[IsoFields.Mti] = IsoUtil.hexToAscii(message.sliceArray(8..15).concatToString())
        msg[IsoFields.Bitmap] = message.sliceArray(16..31).concatToString()
        val bitmap: List<Int> = IsoUtil.unpackBitmap(msg[IsoFields.Bitmap])

        for (i in bitmap.indices)
            if (bitmap[i] == 1) {
                val schema: SinaIsoField = SayanPurchaseUnPackSchema.getIsoFieldInfo(i + 1)
                var fieldLength: Int
                when (schema.type) {
                    SinaFieldTypes.N, SinaFieldTypes.AN, SinaFieldTypes.ANS ->{

                        when(schema.lengthType){
                            IsoFieldLengthType.LL -> {
                                fieldLength = IsoUtil.hexToAscii(message.sliceArray(pointer until pointer+4).concatToString()).toInt() * 2
                                pointer += 4
                                msg[getFieldName(i+1)] = IsoUtil.hexToAscii(message.sliceArray(pointer until pointer+fieldLength).concatToString())
                                pointer += fieldLength

                            }
                            IsoFieldLengthType.LLL ->{
                                fieldLength = IsoUtil.hexToAscii(message.sliceArray(pointer until pointer+6).concatToString()).toInt() * 2
                                pointer += 6
                                msg[getFieldName(i+1)] = IsoUtil.hexToAscii(message.sliceArray(pointer until pointer+fieldLength).concatToString())
                                pointer += fieldLength
                            }
                            IsoFieldLengthType.CONST ->{
                                fieldLength = schema.length * 2
                                msg[getFieldName(i+1)] = IsoUtil.hexToAscii(message.sliceArray(pointer until pointer+fieldLength).concatToString())
                                pointer += fieldLength
                            }
                        }
                    }

                    SinaFieldTypes.B ->{
                        fieldLength = schema.length * 2
                        msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+fieldLength).concatToString()
                        pointer += fieldLength
                    }
                }
            }


        return msg
    }

    private fun getFieldName(field : Int) : IsoFields {
        return when(field){
            3 -> IsoFields.ProcessCode
            4 -> IsoFields.Amount
            11 -> IsoFields.Stan
            12 -> IsoFields.TransactionTime
            13 -> IsoFields.TransactionDate
            37 -> IsoFields.Rrn
            39 -> IsoFields.Response
            41 -> IsoFields.Terminal
            42 -> IsoFields.Merchant
            48 -> IsoFields.Buffer1
            64 -> IsoFields.Mac
            else -> throw IllegalArgumentException()
        }
    }
}