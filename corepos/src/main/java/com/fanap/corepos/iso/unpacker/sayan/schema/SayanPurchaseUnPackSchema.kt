package com.fanap.corepos.iso.unpacker.sayan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException

object SayanPurchaseUnPackSchema {

    fun getIsoFieldInfo(fieldNumber: Int): SinaIsoField {
        return when (fieldNumber) {
            3, 11, 12 -> SinaIsoField(6, SinaFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST)
            13, 41 -> SinaIsoField(8, SinaFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST)
            4, 37 -> SinaIsoField(12, SinaFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST)
            54 -> SinaIsoField(120, SinaFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LLL)
            39 -> SinaIsoField(2, SinaFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST)
            42 -> SinaIsoField(15, SinaFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST)
            48, 63 -> SinaIsoField(999, SinaFieldTypes.ANS, IsoFieldApplication.OPTIONAL,IsoFieldLengthType.LLL)
            64 -> SinaIsoField(8, SinaFieldTypes.B, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST)
            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}