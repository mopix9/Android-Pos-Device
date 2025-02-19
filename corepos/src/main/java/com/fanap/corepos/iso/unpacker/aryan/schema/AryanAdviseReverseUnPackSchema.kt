package com.fanap.corepos.iso.unpacker.aryan.schema

import com.fanap.corepos.iso.model.*
import java.lang.IllegalArgumentException

object AryanAdviseReverseUnPackSchema {

    /**
     * @param fieldNumber bit number of iso field in iso message
     * @return iso field specification like type,length and ...
     */
    fun getIsoFieldInfo(fieldNumber: Int): AryanIsoField {
        return when (fieldNumber) {
            3, 11, 12 -> AryanIsoField(6, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            4 -> AryanIsoField(12, AryanFieldTypes.N, IsoFieldApplication.OPTIONAL,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            13 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            15,24 -> AryanIsoField(4, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            25 -> AryanIsoField(2, AryanFieldTypes.N, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            37 -> AryanIsoField(12, AryanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            38 -> AryanIsoField(6, AryanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            39 -> AryanIsoField(2, AryanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            41 -> AryanIsoField(8, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            42 -> AryanIsoField(15, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            48 -> AryanIsoField(999, AryanFieldTypes.ANS, IsoFieldApplication.MANDATORY,IsoFieldLengthType.LLL, AryanDataTypes.ASCII)
            49 -> AryanIsoField(3, AryanFieldTypes.AN, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.ASCII)
            64 -> AryanIsoField(8, AryanFieldTypes.B, IsoFieldApplication.MANDATORY,IsoFieldLengthType.CONST, AryanDataTypes.HEX)
            else -> throw IllegalArgumentException("Invalid iso field! $fieldNumber")
        }

    }

}