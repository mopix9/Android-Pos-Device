package com.fanap.corepos.iso.packer.aryan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException


object AryanPurchasePackSchema {
    /**
     * @param fieldNumber bit number of iso field in iso message
     * @return iso field specification like type,length and ...
     */
    fun getIsoFieldInfo(fieldNumber: Int): AryanIsoField {
        return when (fieldNumber) {
            3, 11, 12 -> AryanIsoField(6, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            4 -> AryanIsoField(12, AryanFieldTypes.N, IsoFieldApplication.OPTIONAL,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            13 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            22, 24 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            25 -> AryanIsoField(2, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            35 -> AryanIsoField(38, AryanFieldTypes.Z, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LL, AryanDataTypes.HEX)
            41 -> AryanIsoField(8, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            42 -> AryanIsoField(15, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            48 -> AryanIsoField(999, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LLL, AryanDataTypes.ASCII)
            49 -> AryanIsoField(3, AryanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            52, 64 -> AryanIsoField(8, AryanFieldTypes.B, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            59, 62 -> AryanIsoField(999, AryanFieldTypes.ANS, IsoFieldApplication.CONDITIONAL,IsoFieldLengthType.LLL, AryanDataTypes.ASCII)
            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}