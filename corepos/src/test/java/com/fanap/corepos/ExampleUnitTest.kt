package com.fanap.corepos

import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.di.IsoProtocol
import com.fanap.corepos.iso.manager.aryan.AryanTransaction
import com.fanap.corepos.iso.unpacker.sina.*
import com.fanap.corepos.iso.utils.IsoFields
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.HashMap

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun addition_isCorrect() {
        val payId = "2200784"
        val result = payId.substring(payId.length-1)
        println()
    }

    @Test
    fun slice_test() {
        val data = "30323932303831302038000002C10003"
        var mti = data.toCharArray().slice(8..15)
    }

    @Test
    fun sina_logon_unpacker() {
        val unpacker = SinaLogonUnPacker()
        val s = unpacker.unpack()
        assertEquals(s.size,12)
    }

    @Test
    fun sina_balance_unpacker() {
        val unpacker = SinaBalanceUnPacker()
        val s = unpacker.unpack()
        assertEquals(s.size,12)
    }

    @Test
    fun sina_buy_unpacker() {
        val unpacker = SinaBuyUnPacker()
        val s = unpacker.unpack()
        assertEquals(s.size,12)
    }

    @Test
    fun sina_advise_unpacker() {
        val unpacker = SinaAdviseUnPacker()
        val s = unpacker.unpack()
        assertEquals(s.size,12)
    }

    @Test
    fun sina_reverse_unpacker() {
        val unpacker = SinaReverseUnPacker()
        val s = unpacker.unpack()
        assertEquals(s.size,12)
    }

    @Test
    fun sina_bill_unpacker() {
        val unpacker = SinaBillUnPacker()
        val s = unpacker.unpack()
        assertEquals(s.size,12)
    }

    @Test
    fun sina_buy_packer() {

        DependencyManager.protocol = IsoProtocol.SINA

        val buyMap = HashMap<IsoFields, String>()
        buyMap.apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.CardNumber,"5047062026403687")
            put(IsoFields.ProcessCode,"000000")
            put(IsoFields.Amount,"20000")
            put(IsoFields.LastStan,"000032")
            put(IsoFields.Stan,"000033")
            put(IsoFields.TransactionTime,"211327")
            put(IsoFields.TransactionDate,"20210715")
            put(IsoFields.CardExpireDate,"2303")
            put(IsoFields.Serial,"P190000009")
            put(IsoFields.Track2,"5047062026403687=23031007351493204488")
            put(IsoFields.Terminal,"92001564")
            put(IsoFields.Merchant,"95030020")
            put(IsoFields.PinBlock,"6B3BA7D91B8D4F71")
        }
        val tr = AryanTransaction()
        runBlocking {
            tr.doTransaction(buyMap)
        }

    }

    @Test
    fun sina_bill_packer() {

        DependencyManager.protocol = IsoProtocol.SINA

        val buyMap = HashMap<IsoFields, String>()
        buyMap.apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.CardNumber,"5047062026403687")
            put(IsoFields.ProcessCode,"170000")
            put(IsoFields.Amount,"153000")
            put(IsoFields.LastStan,"000033")
            put(IsoFields.Stan,"000035")
            put(IsoFields.TransactionTime,"211555")
            put(IsoFields.TransactionDate,"20210715")
            put(IsoFields.CardExpireDate,"2303")
            put(IsoFields.Serial,"P190000009")
            put(IsoFields.Track2,"5047062026403687=23031007351493204488")
            put(IsoFields.Terminal,"92001564")
            put(IsoFields.Merchant,"95030020")
            put(IsoFields.PinBlock,"6B3BA7D91B8D4F71")
            put(IsoFields.BillId,"9611035800290")
            put(IsoFields.PayId,"15305004")
        }
        val tr = AryanTransaction()
        runBlocking {
            tr.doTransaction(buyMap)
        }

    }

    @Test
    fun sina_voucher_packer() {

        DependencyManager.protocol = IsoProtocol.SINA

        val chargeMap = HashMap<IsoFields, String>()
        chargeMap.apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.CardNumber,"5047062026403687")
            put(IsoFields.ProcessCode,"190000")
            put(IsoFields.Amount,"20000")
            put(IsoFields.LastStan,"000035")
            put(IsoFields.Stan,"000037")
            put(IsoFields.TransactionTime,"211701")
            put(IsoFields.TransactionDate,"20210715")
            put(IsoFields.CardExpireDate,"2303")
            put(IsoFields.Serial,"P190000009")
            put(IsoFields.Track2,"5047062026403687=23031007351493204488")
            put(IsoFields.Terminal,"92001564")
            put(IsoFields.Merchant,"95030020")
            put(IsoFields.ChargeOrganization,"9935")
            put(IsoFields.PinBlock,"6B3BA7D91B8D4F71")
        }
        val tr = AryanTransaction()
        runBlocking {
            tr.doTransaction(chargeMap)
        }

    }

    @Test
    fun sina_topup_packer() {

        DependencyManager.protocol = IsoProtocol.SINA

        val chargeMap = HashMap<IsoFields, String>()
        chargeMap.apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.CardNumber,"5047062026403687")

            put(IsoFields.ProcessCode,"180000")

            put(IsoFields.Amount,"20000")
            put(IsoFields.LastStan,"000037")
            put(IsoFields.Stan,"000039")
            put(IsoFields.TransactionTime,"211810")
            put(IsoFields.TransactionDate,"20210715")
            put(IsoFields.CardExpireDate,"2303")
            put(IsoFields.Serial,"P190000009")
            put(IsoFields.Track2,"5047062026403687=23031007351493204488")
            put(IsoFields.Terminal,"92001564")
            put(IsoFields.Merchant,"95030020")
            put(IsoFields.ChargeOrganization,"9914")

            put(IsoFields.PhoneNumber,"09190396878")

            put(IsoFields.PinBlock,"2CBAE5F5AB380FEC")
        }
        val tr = AryanTransaction()
        runBlocking {
            tr.doTransaction(chargeMap)
        }

    }

    @Test
    fun sina_advise_packer() {

        DependencyManager.protocol = IsoProtocol.SINA

        val map = HashMap<IsoFields, String>()
        map.apply {
            put(IsoFields.Mti,"0220")
            put(IsoFields.CardNumber,"5047062026403687")
            put(IsoFields.ProcessCode,"000000")
            put(IsoFields.Amount,"20000")
            put(IsoFields.LastStan,"000033")
            put(IsoFields.Stan,"000034")
            put(IsoFields.TransactionTime,"211327")
            put(IsoFields.TransactionDate,"20210715")
            put(IsoFields.Serial,"P190000009")
            put(IsoFields.Terminal,"92001564")
            put(IsoFields.Merchant,"95030020")
            put(IsoFields.TransactionDateTime,"20210715211327")
            put(IsoFields.TransactionStan,"000033")
            put(IsoFields.TransactionRrn,"141800127455")
        }
        val tr = AryanTransaction()
        runBlocking {
            tr.doTransaction(map)
        }

    }

    @Test
    fun sina_reverse_packer() {

        DependencyManager.protocol = IsoProtocol.SINA

        val map = HashMap<IsoFields, String>()
        map.apply {
            put(IsoFields.Mti,"0400")
            put(IsoFields.CardNumber,"5047062026403687")
            put(IsoFields.ProcessCode,"000000")
            put(IsoFields.Amount,"20000")
            put(IsoFields.LastStan,"000039")
            put(IsoFields.Stan,"000042")
            put(IsoFields.TransactionTime,"212055")
            put(IsoFields.TransactionDate,"20210715")
            put(IsoFields.Serial,"P190000009")
            put(IsoFields.Terminal,"92001564")
            put(IsoFields.Merchant,"95030020")
            put(IsoFields.TransactionDateTime,"20210715212013")
            put(IsoFields.TransactionStan,"41")
        }
        val tr = AryanTransaction()
        runBlocking {
            tr.doTransaction(map)
        }

    }

    @Test
    fun getKeys(){
        val keys = "32MCK5a66e28f84c1e82524faa189773e7adc32PNK7bb336b99850061df9d1df459c2cd2b432DTK11b0c1944b751efd2d989752be61c2b8"

        val mac = keys?.substringAfter("MCK")?.take(32)
        val pin = keys?.substringAfter("PNK")?.take(32)
        val data = keys?.substringAfter("DTK")?.take(32)

        assertEquals(mac,"5a66e28f84c1e82524faa189773e7adc")
    }
}