package com.fanap.corepos.iso.unpacker.sayan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException

object SayanLogonUnPackSchema {

    fun getIsoFieldInfo(fieldNumber: Int): SayanIsoField {
        return when (fieldNumber) {
            3, 11, 12 -> SayanIsoField(6, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            13 -> SayanIsoField(4, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            39 -> SayanIsoField(2, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.ASCII)
            41 -> SayanIsoField(8, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.ASCII)
            48, 62 -> SayanIsoField(999, SayanFieldTypes.ANS, IsoFieldApplication.OPTIONAL,IsoFieldLengthType.LLL, SayanDataTypes.HEX)
            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}