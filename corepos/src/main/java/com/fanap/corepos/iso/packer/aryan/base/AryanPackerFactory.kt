package com.fanap.corepos.iso.packer.aryan.base

import com.fanap.corepos.iso.packer.aryan.AryanAdvisePacker
import com.fanap.corepos.iso.packer.aryan.AryanBalancePacker
import com.fanap.corepos.iso.packer.aryan.AryanBillInquiryPacker
import com.fanap.corepos.iso.packer.aryan.AryanBillPacker
import com.fanap.corepos.iso.packer.aryan.AryanBuyPacker
import com.fanap.corepos.iso.packer.aryan.AryanGetTerminalPacker
import com.fanap.corepos.iso.packer.aryan.AryanLogonPacker
import com.fanap.corepos.iso.packer.aryan.AryanReversePacker
import com.fanap.corepos.iso.packer.aryan.AryanRollPacker
import com.fanap.corepos.iso.packer.aryan.AryanTopupPacker
import com.fanap.corepos.iso.packer.aryan.AryanVoucherPacker
import com.fanap.corepos.iso.packer.base.IPackerFactory
import com.fanap.corepos.iso.packer.base.Packer
import com.fanap.corepos.iso.utils.IsoFields

class AryanPackerFactory : IPackerFactory {

    override fun getPacker(msg: HashMap<IsoFields, String>): Packer {
        return when (msg[IsoFields.ProcessCode]) {
            "220000" -> AryanTopupPacker()
            "170000" -> AryanBillPacker()
            "180000" -> AryanVoucherPacker()
            "310000" -> AryanBalancePacker()
            "920000" -> AryanLogonPacker()
            "930000" -> AryanGetTerminalPacker()
            "190000" -> AryanBillInquiryPacker()
            "590000" -> AryanRollPacker()
            "000000" -> {
                when (msg[IsoFields.Mti]!!){
                    "0200" -> AryanBuyPacker()
                    "0220" -> AryanAdvisePacker()
                    "0420" -> AryanReversePacker()
                    else -> throw IllegalArgumentException()
                }

            }
            else -> throw IllegalArgumentException("Undefined MTI!")
        }
    }
}
