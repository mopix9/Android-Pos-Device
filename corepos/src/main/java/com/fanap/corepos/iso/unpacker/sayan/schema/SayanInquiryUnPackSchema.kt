package com.fanap.corepos.iso.unpacker.sayan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException

object SayanInquiryUnPackSchema {

    fun getIsoFieldInfo(fieldNumber: Int): SayanIsoField {
        return when (fieldNumber) {
            2 -> SayanIsoField(16, SayanFieldTypes.N, IsoFieldApplication.CONDITIONAL,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            3, 11, 12 -> SayanIsoField(6, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            13 -> SayanIsoField(4, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            22, 24 -> SayanIsoField(4, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            25 -> SayanIsoField(2, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            37 -> SayanIsoField(12, SayanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            38 -> SayanIsoField(6, SayanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)
            39 -> SayanIsoField(2, SayanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.ASCII)
            41 -> SayanIsoField(8, SayanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.ASCII)
            42 -> SayanIsoField(15, SayanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.ASCII)
            48 -> SayanIsoField(999, SayanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LLL, SayanDataTypes.HEX)
            49 -> SayanIsoField(4, SayanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.ASCII)
            54 -> SayanIsoField(120, SayanFieldTypes.ANS, IsoFieldApplication.CONDITIONAL,IsoFieldLengthType.LLL, SayanDataTypes.HEX)
            58, 59 -> SayanIsoField(999, SayanFieldTypes.ANS, IsoFieldApplication.CONDITIONAL,IsoFieldLengthType.LLL, SayanDataTypes.HEX)
            64 -> SayanIsoField(8, SayanFieldTypes.B, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, SayanDataTypes.HEX)

            else -> throw IllegalArgumentException("Invalid iso field!")
        }
    }
}