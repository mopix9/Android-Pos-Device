package com.fanap.corepos.iso.unpacker.aryan

import com.fanap.corepos.iso.model.*
import com.fanap.corepos.iso.unpacker.aryan.schema.AryanInquiryUnPackSchema
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.unpacker.sayan.schema.SayanInquiryUnPackSchema
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.iso.utils.Encoding
import com.fanap.corepos.utils.IsoUtil
import java.util.*

class AryanGetTerminalUnPacker : UnPacker() {

    override fun unpack(): HashMap<IsoFields, String> {
        val message: CharArray = IsoUtil.bytesToHex(message).toCharArray()
        val msg = HashMap<IsoFields, String>()
        var pointer = 34 // current location in message after adding mti and bitmap in msg

        msg[IsoFields.Mti] = message.sliceArray(14..17).concatToString()
        msg[IsoFields.Bitmap] = message.sliceArray(18..33).concatToString()
        val bitmap: List<Int> = IsoUtil.unpackBitmap(msg[IsoFields.Bitmap])

        for (i in bitmap.indices)
            if (bitmap[i] == 1) {
                val schema: AryanIsoField = AryanInquiryUnPackSchema.getIsoFieldInfo(i + 1)
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

                AryanFieldTypes.B ->{
                    fieldLength = schema.length * 2
                    msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+fieldLength).concatToString()
                    pointer += fieldLength
                }
                else -> {}
            }
        }


        val ltvData = extractFields(msg[IsoFields.Buffer1]?:"")

        try {
            msg[IsoFields.Merchant] = msg[IsoFields.Merchant]?.trim() ?:""
            msg[IsoFields.MerchantName] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(
                ltvData?.get("31") ?:"")).split("\\")[0]
            msg[IsoFields.MerchantNameEnglish] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(
                ltvData?.get("31") ?:"")).split("\\")[1]
            msg[IsoFields.MerchantPhone] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(ltvData?.get("34") ?:""))
            msg[IsoFields.ShopPostCode] = IsoUtil.asciiToText(ltvData?.get("35") ?:"")
            msg[IsoFields.Address] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(ltvData?.get("32") ?:""))
            msg[IsoFields.Sheba] = IsoUtil.asciiToText(ltvData?.get("43") ?:"")
            msg[IsoFields.TransactionDateTime] = IsoUtil.asciiToText(ltvData?.get("50") ?:"")
        }catch (e : Exception){}

        try {
            msg[IsoFields.Buffer2] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(msg[IsoFields.Buffer2]))
        }catch (e:Exception){}

        return msg
    }

    private fun getFieldName(field : Int) : IsoFields{
        return when(field){
            3 -> IsoFields.ProcessCode
            7 -> IsoFields.TransmissionTime
            11 -> IsoFields.Stan
            12 -> IsoFields.TransactionTime
            13 -> IsoFields.TransactionDate
            24 -> IsoFields.NiiCode
            37 -> IsoFields.Rrn
            39 -> IsoFields.Response
            41 -> IsoFields.Terminal
            42 -> IsoFields.Merchant
            48 -> IsoFields.Buffer1
            59 -> IsoFields.Buffer2
            64 -> IsoFields.Mac
            else -> throw IllegalArgumentException()
        }
    }
}