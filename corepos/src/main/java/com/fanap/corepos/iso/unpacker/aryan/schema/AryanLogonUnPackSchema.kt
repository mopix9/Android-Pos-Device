package com.fanap.corepos.iso.unpacker.aryan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException

object AryanLogonUnPackSchema {

    fun getIsoFieldInfo(fieldNumber: Int): AryanIsoField {
        return when (fieldNumber) {
            3, 11, 12 -> AryanIsoField(6, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            13 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            37 -> AryanIsoField(12, AryanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            39 -> AryanIsoField(2, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            41 -> AryanIsoField(8, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            48, 62 -> AryanIsoField(999, AryanFieldTypes.ANS, IsoFieldApplication.OPTIONAL,IsoFieldLengthType.LLL, AryanDataTypes.HEX)
            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}