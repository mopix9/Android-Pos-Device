package com.fanap.corepos.iso.packer.aryan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException


object AryanInquiryPackSchema {
    /**
     * @param fieldNumber bit number of iso field in iso message
     * @return iso field specification like type,length and ...
     */
    fun getIsoFieldInfo(fieldNumber: Int): AryanIsoField {
        return when (fieldNumber) {
            2 -> AryanIsoField(19, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LL, AryanDataTypes.HEX)
            3, 11, 12 -> AryanIsoField(6, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            13 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            14 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.CONDITIONAL,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            22, 24 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            25 -> AryanIsoField(2, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            35 -> AryanIsoField(38, AryanFieldTypes.Z, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LL, AryanDataTypes.HEX)
            41 -> AryanIsoField(8, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            42 -> AryanIsoField(15, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            48 -> AryanIsoField(999, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LLL, AryanDataTypes.ASCII)
            52, 64 -> AryanIsoField(8, AryanFieldTypes.B, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)

            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}