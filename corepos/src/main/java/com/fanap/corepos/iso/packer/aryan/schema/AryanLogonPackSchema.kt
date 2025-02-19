package com.fanap.corepos.iso.packer.aryan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException


object AryanLogonPackSchema {
    /**
     * @param fieldNumber bit number of iso field in iso message
     * @return iso field specification like type,length and ...
     */
    fun getIsoFieldInfo(fieldNumber: Int): AryanIsoField {
        return when (fieldNumber) {
            3, 11, 12 -> AryanIsoField(6, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            13 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            24 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            48 -> AryanIsoField(999, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LLL, AryanDataTypes.ASCII)
            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}