package com.fanap.corepos.iso.unpacker.aryan

import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.iso.model.*
import com.fanap.corepos.iso.unpacker.aryan.schema.AryanPurchaseUnPackSchema
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.utils.Encoding
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil

class AryanVoucherUnPacker  : UnPacker() {

    override fun unpack(): java.util.HashMap<IsoFields, String> {

        val message: CharArray = IsoUtil.bytesToHex(message).toCharArray()
        val msg = java.util.HashMap<IsoFields, String>()
        var pointer = 34 // current location in message after adding mti and bitmap in msg

        msg[IsoFields.Mti] = message.sliceArray(14..17).concatToString()
        msg[IsoFields.Bitmap] = message.sliceArray(18..33).concatToString()
        val bitmap: List<Int> = IsoUtil.unpackBitmap(msg[IsoFields.Bitmap])

        for (i in bitmap.indices)
            if (bitmap[i] == 1) {
                val schema: AryanIsoField = AryanPurchaseUnPackSchema.getIsoFieldInfo(i + 1)
                var fieldLength: Int
                when (schema.type) {
                    AryanFieldTypes.N, AryanFieldTypes.AN, AryanFieldTypes.ANS ->{
                        when(schema.lengthType){

                            IsoFieldLengthType.LL ->{
                                fieldLength = message.sliceArray(pointer until pointer+2).concatToString().toInt()
                                pointer += 2
                                msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+fieldLength).concatToString()
                                pointer += fieldLength
                            }

                            IsoFieldLengthType.LLL ->{
                                fieldLength = message.sliceArray(pointer until pointer+4).concatToString().toInt() * 2
                                pointer += 4
                                msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+fieldLength).concatToString()
                                pointer += fieldLength
                            }
                            IsoFieldLengthType.CONST ->{
                                if (schema.dataType == AryanDataTypes.ASCII){
                                    fieldLength = schema.length * 2
                                    msg[getFieldName(i+1)] = IsoUtil.asciiToText(message.sliceArray(pointer until pointer+fieldLength).concatToString())
                                    pointer += fieldLength
                                }else {
                                    msg[getFieldName(i+1)] = message.sliceArray(pointer until pointer+schema.length).concatToString()
                                    pointer += schema.length
                                }
                            }
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


        val field_48 = extractFields(msg[IsoFields.Buffer1]?:"")

        msg[IsoFields.BankName] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(
            field_48?.get("38") ?:"")).split("-")[0]
        msg[IsoFields.ChargeSerial] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(field_48?.get("40") ?:""))
        val encryptedPin = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(field_48?.get("41") ?:""))
        val decryptedPin = DeviceSDKManager.getHSMInterface()?.decryptionData(encryptedPin)?:""
        msg[IsoFields.ChargePin] = IsoUtil.asciiToText(decryptedPin).replace("[^0-9]".toRegex(), "")
        //  msg[IsoFields.ChargeOrganization] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(getFieldWithTagName(msg[IsoFields.Buffer1],"42")))
        msg[IsoFields.ChargeOrganization] = Encoding.convertToWindows1256(IsoUtil.hexStringToByteArray(field_48?.get("51") ?:""))

        /*
        val plainChargePin = DeviceSDKManager.getHSMInterface(null)?.decryptionData(msg[IsoFields.ChargePin])
        msg[IsoFields.ChargePin] = plainChargePin.toString()
        * */

        return msg
    }

    private fun getFieldName(field : Int) : IsoFields{
        return when(field){
            2 -> IsoFields.Pan
            3 -> IsoFields.ProcessCode
            4 -> IsoFields.Amount
            11 -> IsoFields.Stan
            12 -> IsoFields.TransactionTime
            13 -> IsoFields.TransactionDate
            24 -> IsoFields.NiiCode
            37 -> IsoFields.Rrn
            38 -> IsoFields.IdentificationCode
            39 -> IsoFields.Response
            41 -> IsoFields.Terminal
            48 -> IsoFields.Buffer1
            49 -> IsoFields.CurrencyCode
            54 -> IsoFields.Buffer2
            64 -> IsoFields.Mac
            else -> throw IllegalArgumentException()
        }
    }
}