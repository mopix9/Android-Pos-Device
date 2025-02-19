package com.fanap.corepos.utils.aryan

import android.os.Build
import com.fanap.corepos.utils.Utils
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*


object AryanUtils {

    const val NII_LENGTH = 0
    const val TPDU_LENGTH = 5
    const val BEEP_TYPE_SUCCESS = 0
    const val BEEP_TYPE_ERROR = 1
    const val BEEP_TYPE_FAULT = 2
    const val BEEP_TYPE_PROMPT = 3
//    const val TPDU = "0000000565"
//    const val TPDU = "0000000768"
    const val TPDU = "0001050768"

    fun isSuccessfulResponseForConfirmAndReverse(responseCode : String) =
        arrayOf("00","02","25","34").contains(responseCode)

    fun isFailResponseForConfirmAndReverse(responseCode : String) =
        arrayOf("19","80","84","90","91","93","97","77").contains(responseCode)

    fun getTime(): String {
        val d = Date()
        val sdf = SimpleDateFormat("HHmmss", Locale.US)
        return sdf.format(d)
    }

    fun getReceiptTime(): String {
        val d = Date()
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        return sdf.format(d)
    }


    fun getDate(): String {
        val d = Date()
        val sdf = SimpleDateFormat("MMdd", Locale.US)
        return sdf.format(d)
    }

    fun getPersianDate(): String {
        val pdate = PersianDate()
        val pdformater1 = PersianDateFormat("Y/m/d")
        return pdformater1.format(pdate) //1396/05/20
    }

    fun getTimeFromString(input : String) : String{
        return try {
            val inp = SimpleDateFormat("HHmmss", Locale.US)
            val output = SimpleDateFormat("HH:mm:ss", Locale.US)
            output.format(inp.parse(input))
        }catch (e:Exception){
            ""
        }

    }

    fun getTimeForReceipt(input : String) : String{
        return try {
            val inp = SimpleDateFormat("HHmmss", Locale.US)
            val output = SimpleDateFormat("HH:mm:ss", Locale.US)
            output.format(inp.parse(input))
        }catch (e:Exception){
            ""
        }
    }


    fun shamsiToMiladi(year : Int, month : Int, day : Int) : String {
        val arr = PersianDate().toGregorian(year,month,day)
        var result = ""
        arr.drop(1).forEach {
            result += if (it<10)
                "0$it"
            else
                it.toString()
        }
        return result
    }

    fun getShamsiDateFromString(input : String) : String{
        return try {
            val inp = SimpleDateFormat("yyyyMMdd", Locale.US)
            val shamsi = Utils.miladiToShamsi(Calendar.getInstance().get(Calendar.YEAR),input.take(2).toInt(),input.takeLast(2).toInt())
            val output = SimpleDateFormat("yyyy/MM/dd", Locale.US)
            output.format(inp.parse(shamsi))
        }catch (e:Exception){
            ""
        }
    }
    fun maskCard(track2: String): String? {
        return try {
            val cardNumber = track2.split("=")[0]
            val builder = StringBuilder()
            builder.append(cardNumber.substring(0, 6))
            builder.append("**")
            builder.append(cardNumber.substring(12))
            builder.toString()
        }catch (e : Exception){
            ""
        }

    }
    fun miladiToShamsi(year : Int, month : Int, day : Int) : String {
        return try {
            val arr = PersianDate().toJalali(year,month,day)
            var result = ""
            arr.forEach {
                result += if (it<10)
                    "0$it"
                else
                    it.toString()
            }
            result
        }catch (e : Exception){
            ""
        }
    }

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer))
            model
         else
            "$manufacturer $model"

    }
}