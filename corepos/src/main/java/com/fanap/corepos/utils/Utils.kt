package com.fanap.corepos.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import com.fanap.corepos.R
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.database.service.model.TransactionStatus
import com.google.android.material.snackbar.Snackbar
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil

object Utils {

    fun isServiceRunning(context: Context, servicePackageName: String): Boolean {
        val manager = (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (servicePackageName == service.service.className) {
                return true
            }
        }
        return false
    }

    fun removeLeftZeros(input : String) : String {
        return try {
            val res = input.replaceFirst(Regex("^0*"), "")
            if (res=="") "0" else res
        }catch (e : Exception){
            ""
        }
    }


    fun getBasis11(input: String, controlCode : Int): Boolean{
        try {
            var sum = 0
            var counter = 2
            input.reversed().toCharArray().forEach {
                if (counter > 7)
                    counter = 2
                sum += Character.getNumericValue(it) * counter
                counter++
            }

            val mod = sum % 11
            val basis11 = if (mod == 0 || mod == 1) 0 else 11 - mod
            return basis11 == controlCode

        }catch (e : Exception){
            e.printStackTrace()
            return false
        }
    }

    fun <T> listOfLists(list: List<T>, length: Int): List<List<T>>? {
        try {
            val lists: MutableList<List<T>> = ArrayList()
            if (list.size <= length) lists.add(list) else {
                val size = ceil((list.size / length.toFloat()).toDouble())
                var i = 0
                while (i < size) {
                    val temp: MutableList<T> = ArrayList()
                    for (j in i * length until i * length + length) if (list.size > j) temp.add(list[j]) else break
                    lists.add(temp)
                    i++
                }
            }
            return lists
        }catch (e:Exception){
            return null
        }
    }


    fun getPersianDate(): String {
        val pdate = PersianDate()
        val pdformater1 = PersianDateFormat("Y/m/d")
        return pdformater1.format(pdate) //1396/05/20
    }

    fun getTime(): String {
        val d = Date()
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        return sdf.format(d)
    }

    fun isCellularDataEnabled(context: Context?): Boolean {
        return try {
            if (context != null) {
                val conMan =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val mobile = conMan.getNetworkInfo(0)!!.state
                mobile.name == "CONNECTED"
            } else false
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun isNetworkConnected(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.activeNetworkInfo != null
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun isWifiConnected(context: Context): Boolean {
        val wifiMgr =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return if (wifiMgr.isWifiEnabled) { // Wi-Fi adapter is ON
            val wifiInfo = wifiMgr.connectionInfo
            wifiInfo.networkId != -1
            // Connected to an access point
        } else {
            false // Wi-Fi adapter is OFF
        }
    }

    fun isCharging(context: Context): Boolean {
        val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val plugged = intent!!.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS
    }

    fun isAdviceAbleTransaction(transaction: Transaction) =
        transaction.response != null
        && transaction.response.equals("00")
        && !transaction.status.equals(TransactionStatus.AdviceResUnpacked.name)


    fun isReverseAbleTransaction(transaction: Transaction) =
        (transaction.response != null
        && transaction.response.equals("-1")
        && !transaction.status.equals(TransactionStatus.ReverseResUnpacked.name))
                ||
                (transaction.response == null && transaction.status.equals(TransactionStatus.TransactionSent.name))

    fun getBatteryLevel(context: Context): Int {
        return try {
            val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            } else 11
        } catch (e: java.lang.Exception) {
            11
        }
    }

    fun shamsiToMiladi(year : Int, month : Int, day : Int) : String {
        val arr = PersianDate().toGregorian(year,month,day)
        var result = ""
        arr.forEach {
            result += if (it<10)
                "0$it"
            else
                it.toString()
        }
        return result
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


    fun getBillName(code: String?): String {
        return when (code) {
            "1" -> "آب"
            "2" -> "برق"
            "3" -> "گاز"
            "4" -> "تلفن ثابت"
            "5" -> "تلفن همراه"
            "6" -> "عوارض شهرداری"
            "7" -> "سازمان مالیات"
            "8" -> "جرایم رانندگی"
            else -> "نامشخص"
        }
    }

    fun makeSnack(parent: View, msg: String?, duration: Int): Snackbar {
        val snack = Snackbar.make(parent, msg!!, duration)
        val view = snack.view

        val params: FrameLayout.LayoutParams = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params

        val tv = view.findViewById<TextView>(R.id.snackbar_text)
        tv.textSize = 20f
        tv.typeface = Typeface.createFromAsset(parent.context.assets, "fonts/iransansbold.ttf")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
        else tv.gravity = Gravity.CENTER_HORIZONTAL
        return snack
    }

    fun openKeyboard(context: Context) {
        if (!isKeyboardOpen(context)) {
            val imgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imgr.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }

    fun hideKeyboard(context: Context) {
        if (isKeyboardOpen(context)) {
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    }

    private fun isKeyboardOpen(context: Context) : Boolean{
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isAcceptingText
    }

    fun removeZeros(input : String) : String {
        return try {
            input.replaceFirst(Regex("^0*"), "")
        }catch (e : Exception){
            ""
        }
    }

    fun cardMask(track2: String): String {
        return try {
            val cardNumber = track2.take(16)
            val builder = StringBuilder()
            builder.append(cardNumber.substring(0, 6))
            builder.append("**")
            builder.append(cardNumber.substring(12))
            builder.toString()
        }catch (e : Exception){
            ""
        }
    }

    fun getBankName(track2: String): String {
        try {
            return when (track2.substring(0, 6)) {
                "603799" -> "ملی"
                "589210", "604932" -> "سپه"
                "627648" -> "توسعه صادرات"
                "627961" -> "صنعت و معدن"
                "603770" -> "کشاورزی"
                "628023" -> "مسکن"
                "627760" -> "پست بانک"
                "502908" -> "توسعه تعاون"
                "627412" -> "اقتصاد نوین"
                "622106" -> "پارسیان"
                "502229" -> "پاسارگاد"
                "627488" -> "کارآفرین"
                "621986" -> "سامان"
                "639346" -> "سینا"
                "639607" -> "سرمایه"
                "636214" -> "آینده"
                "502806", "504706" -> "شهر"
                "502938" -> "دی"
                "603769" -> "صادرات"
                "610433" -> "ملت"
                "627353", "585983" -> "تجارت"
                "589463" -> "رفاه"
                "627381" -> "سپه(انصار سابق)"
                "639370" -> "مهر اقتصاد"
                "10060" -> "مرکزی"
                "504172" -> "رسالت"
                "505416" -> "گردشگری"
                "505785" -> "ایران زمین"
                "505801" -> "کوثر"
                "505809", "585947" -> "خاورمیانه"
                "507677" -> "نور"
                "581672" -> "شاپرک"
                "581874" -> "ایران ونزوئلا"
                "606256" -> "ملل"
                "606373" -> "مهر ایران"
                "628157" -> "توسعه"
                "636795" -> "مرکزی ج.ا.ا"
                "636949" -> "سپه(حکمت سابق)"
                "639599" -> "سپه(قوامین سابق)"
                else -> "نامشخص"
            }

        }catch (e : Exception){
            return ""
        }
    }

    val MTN_PREFIXES = arrayOf(
        "0930",
        "0933",
        "0935",
        "0936",
        "0937",
        "0938",
        "0939",
        "0941",
        "0901",
        "0902",
        "0903",
        "0904"
    )

    val MCI_PREFIXES = arrayOf(
        "0910",
        "0911",
        "0912",
        "0913",
        "0914",
        "0915",
        "0916",
        "0917",
        "0918",
        "0919"
    )

    val RIGHTEL_PREFIXES = arrayOf(
        "0921",
        "0922",
        "0990"
    )

}