package com.fanap.corepos.iso.maker.sina.base

import com.fanap.corepos.iso.maker.base.IMakerFactory
import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.maker.sina.*
import com.fanap.corepos.iso.maker.sina.SinaBalanceMaker
import com.fanap.corepos.iso.maker.sina.SinaBuyMaker
import com.fanap.corepos.iso.maker.sina.SinaLogonMaker
import com.fanap.corepos.iso.maker.sina.SinaVoucherMaker
import com.fanap.corepos.iso.utils.IsoFields

class SinaIsoMakerFactory : IMakerFactory {

    override fun getIsoMaker(msg: HashMap<IsoFields, String>): Maker {

        return when (msg[IsoFields.ProcessCode]!!) {
            "180000" -> SinaTopupMaker()
            "170000" -> SinaBillMaker()
            // "190000", "180000" -> ChargePacker()
            //"170000" -> BillPacker()
             "190000" -> SinaVoucherMaker()
            "310000" -> SinaBalanceMaker()
            "250000" -> SinaLogonMaker()
            "000000" -> {
                when (msg[IsoFields.Mti]!!){
                    "0200" -> SinaBuyMaker()
                    "0220" -> SinaAdviseMaker()
                    "0400" -> SinaReverseMaker()
                    else -> throw IllegalArgumentException()
                }

            }
            else -> throw IllegalArgumentException("Undefined MTI!")
        }
    }

}