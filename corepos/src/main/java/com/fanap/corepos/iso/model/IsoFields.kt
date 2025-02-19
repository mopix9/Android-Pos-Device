package com.fanap.corepos.iso.model

class SinaIsoField(var length: Int, var type: SinaFieldTypes, var application: IsoFieldApplication, var lengthType: IsoFieldLengthType)
class DotinIsoField(var length: Int, var type: DotinFieldTypes, var application: IsoFieldApplication, var lengthType: IsoFieldLengthType)
class SayanIsoField(var length: Int, var type: SayanFieldTypes, var application: IsoFieldApplication, var lengthType: IsoFieldLengthType, var dataType: SayanDataTypes)
class AryanIsoField(var length: Int, var type: AryanFieldTypes, var application: IsoFieldApplication, var lengthType: IsoFieldLengthType, var dataType: AryanDataTypes)

