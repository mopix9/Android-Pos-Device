package com.fanap.corepos.utils.sina

import android.os.Build
import com.fanap.corepos.utils.Utils
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.*


object SinaUtils {

    const val NII_LENGTH = 0
    const val BEEP_TYPE_SUCCESS = 0
    const val BEEP_TYPE_ERROR = 1
    const val BEEP_TYPE_FAULT = 2
    const val BEEP_TYPE_PROMPT = 3

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
        val sdf = SimpleDateFormat("yyyyMMdd", Locale.US)
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
            val output = SimpleDateFormat("HH:mm", Locale.US)
            output.format(inp.parse(input))
        }catch (e:Exception){
            ""
        }

    }

    fun getShamsiDateFromString(input : String) : String{
        return try {
            val inp = SimpleDateFormat("yyyyMMdd", Locale.US)
            val shamsi = Utils.miladiToShamsi(input.take(4).toInt(),input.substring(4,6).toInt(),input.takeLast(2).toInt())
            val output = SimpleDateFormat("yyyy/MM/dd", Locale.US)
            output.format(inp.parse(shamsi))
        }catch (e:Exception){
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