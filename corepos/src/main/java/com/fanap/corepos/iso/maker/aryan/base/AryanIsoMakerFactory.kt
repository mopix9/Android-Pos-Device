package com.fanap.corepos.iso.maker.aryan.base

import com.fanap.corepos.iso.maker.aryan.*
import com.fanap.corepos.iso.maker.base.IMakerFactory
import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.utils.IsoFields

class AryanIsoMakerFactory : IMakerFactory {

    override fun getIsoMaker(msg: HashMap<IsoFields, String>): Maker {

        return when (msg[IsoFields.ProcessCode]!!) {
            "220000" -> AryanTopupMaker()
            "170000" -> AryanBillMaker()
            "180000" -> AryanVoucherMaker()
            "190000" -> AryanBillInquiryMaker()
            "310000" -> AryanBalanceMaker()
            "920000" -> AryanLogonMaker()
            "930000" -> AryanGetTerminalMaker()
            "590000" -> AryanRollMaker()
            "000000" -> {
                when (msg[IsoFields.Mti]!!){
                    "0200" -> AryanBuyMaker()
                    "0220" -> AryanAdviseMaker()
                    "0420" -> AryanReverseMaker()
                    else -> throw IllegalArgumentException()
                }

            }
            else -> throw IllegalArgumentException("Undefined MTI!")
        }
    }

}