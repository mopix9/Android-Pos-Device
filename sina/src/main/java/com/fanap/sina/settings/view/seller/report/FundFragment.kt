package com.fanap.sina.settings.view.seller.report

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.print.PrinterInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.sina.SinaFundReceipt
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentFundBinding
import com.fanap.sina.main.fragments.LoadingFragment
import com.google.android.material.snackbar.Snackbar
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.HashMap
import javax.inject.Inject

@AndroidEntryPoint
class FundFragment : BaseFragment<FragmentFundBinding>() , TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var selectedDate = ""
    private var isStartClicked = false
    private var startDate = ""
    private  var endDate = ""

    @Inject
    lateinit var loading : LoadingFragment
    @Inject
    lateinit var appContext : Context
    private val transactionRepository: ITransactionRepository by lazy { DependencyManager.provideTransactionRepository(appContext) }
    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(appContext)
    }
    private var printer: PrinterInterface? = null
    private val headerData = HashMap<IsoFields, String>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFundBinding  = FragmentFundBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        printer = DeviceSDKManager.getPrinterInterface(appContext)

        lifecycleScope.launch {
            headerData[IsoFields.MerchantName] =
                settingsRepository.getValue(SettingsNames.MerchantName.name)?.value ?: ""
            headerData[IsoFields.MerchantPhone] =
                settingsRepository.getValue(SettingsNames.Phone.name)?.value ?: ""
            headerData[IsoFields.Terminal] =
                settingsRepository.getValue(SettingsNames.TerminalNo.name)?.value ?: ""
            headerData[IsoFields.Merchant] =
                settingsRepository.getValue(SettingsNames.MerchantNo.name)?.value ?: ""
        }


        startTimer()
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })
        onTimerFinish.observe(viewLifecycleOwner,{ finish(this) })

        binding.back.setOnClickListener { finish(this) }
        onBackPressed.observe(viewLifecycleOwner,{ finish(this) })

        binding.btnStartDate.setOnClickListener {
            isStartClicked = true
            showDatePicker()
        }

        binding.btnEndDate.setOnClickListener {
            isStartClicked = false
            showDatePicker()
        }

        binding.imgPrint.setOnClickListener {
            if (startDate != "" && endDate != "") {
                if (!isAllZero()) {
                    binding.imgPrint.isEnabled = false
                    loading.show(childFragmentManager, "")
                    loading.isCancelable = false
                    stopTimer()
                    val receipt = SinaFundReceipt().generateReceipt(requireContext(),initDataForPrinter(),headerData)
                    lifecycleScope.launch(Dispatchers.IO) {
                        val result = printer?.printBitmap(receipt!!) ?: false
                        withContext(Dispatchers.Main){
                            startTimer()
                            binding.imgPrint.isEnabled = true
                            loading.dismiss()
                            if (result)
                                finish(this@FundFragment)
                            else
                                Utils.makeSnack(binding.imgPrint, "عدم چاپ رسید!", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Utils.makeSnack(binding.imgPrint, "تراکنشی موجود نیست!", Snackbar.LENGTH_SHORT).show()
                }
            } else Utils.makeSnack(binding.imgPrint, "لطفا تاریخ شروع و پایان را انتخاب کنید!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initDataForPrinter(): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap["successBuy"] = binding.successBuy.text.toString()
        hashMap["successBill"] = binding.successBill.text.toString()
        hashMap["successCharge"] = binding.successCharge.text.toString()
        hashMap["successTopup"] = binding.successTopup.text.toString()
        hashMap["failBuy"] = binding.failBuy.text.toString()
        hashMap["failBill"] = binding.failBill.text.toString()
        hashMap["failCharge"] = binding.failCharge.text.toString()
        hashMap["failTopup"] = binding.failTopup.text.toString()
        hashMap["sumBuy"] = binding.sumBuy.text.toString()
        hashMap["sumBill"] = binding.sumBill.text.toString()
        hashMap["sumCharge"] = binding.sumCharge.text.toString()
        hashMap["sumTopup"] = binding.sumTopup.text.toString()
        hashMap["startDate"] = binding.txtStartDate.text.toString().split(" ")[0]//startDate.split("_".toRegex()).toTypedArray()[0]
        hashMap["endDate"] =  binding.txtEndDate.text.toString().split(" ")[0]//endDate.split("_".toRegex()).toTypedArray()[0]
        hashMap["startTime"] =  binding.txtStartDate.text.toString().split(" ")[1]//startDate.split("_".toRegex()).toTypedArray()[1]
        hashMap["endTime"] =  binding.txtEndDate.text.toString().split(" ")[1]//endDate.split("_".toRegex()).toTypedArray()[1]
        return hashMap
    }

    private fun showDatePicker() {
        val persianCalendar = PersianCalendar()
        val datePickerDialog = DatePickerDialog.newInstance(
            this@FundFragment,
            persianCalendar.persianYear,
            persianCalendar.persianMonth,
            persianCalendar.persianDay
        )
        datePickerDialog.show(childFragmentManager,"")
    }

    private fun showTimePicker() {
        val now = PersianCalendar()
        val tpd = TimePickerDialog.newInstance(
            this@FundFragment,
            now[PersianCalendar.HOUR_OF_DAY],
            now[PersianCalendar.MINUTE],
            true
        )
        tpd.show(childFragmentManager, "")
    }

    override fun onTimeSet(view: RadialPickerLayout?, hourOfDay: Int, minute: Int) {
        val hour = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay.toString()
        val min = if (minute < 10) "0$minute" else minute.toString()

        if (isStartClicked) {
            binding.txtStartDate.text = "$selectedDate $hour:$min"
            startDate = selectedDate + hour + min
        } else {
            binding.txtEndDate.text = "$selectedDate $hour:$min"
            endDate = selectedDate + hour + min
        }

        if (startDate != "" && endDate != "") {
            if (endDate >= startDate) {
                loading.show(childFragmentManager, "")
                getSuccessCount("000000")
            } else {
                binding.lnrData.visibility = View.GONE
                binding.txtEmpty.visibility = View.VISIBLE
                binding.imgPrint.isEnabled = false
                Utils.makeSnack(binding.btnStartDate, "تاریخ شروع باید قبل تر از تاریخ پایان باشد.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val newMonth = monthOfYear+1
        val month = if (newMonth < 10) "0$newMonth" else newMonth.toString()
        val day = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth.toString()

        selectedDate = "$year/$month/$day"
        showTimePicker()
    }

    private fun getSuccessCount(mti: String) {
        lifecycleScope.launch {

            val sDate = startDate.dropLast(4)
            val eDate = endDate.dropLast(4)

            val end = Utils.shamsiToMiladi(eDate.split('/')[0].toInt(), eDate.split('/')[1].toInt(), eDate.split('/')[2].toInt())+endDate.takeLast(4)
            val start = Utils.shamsiToMiladi(sDate.split('/')[0].toInt(), sDate.split('/')[1].toInt(), sDate.split('/')[2].toInt()) + startDate.takeLast(4)

            val result = transactionRepository.getSuccessCount(start, end, mti)
                when (mti) {
                    "000000" -> {
                        binding.successBuy.text = result.toString()
                        getSuccessCount("170000")
                    }
                    "170000" -> {
                        binding.successBill.text = result.toString()
                        getSuccessCount("180000")
                    }
                    "180000" -> {
                        binding.successTopup.text = result.toString()
                        getSuccessCount("190000")
                    }
                    "190000" -> {
                        binding.successCharge.text = result.toString()
                        getFailureCount("000000")
                    }
                }
            
        }
    }

    private fun getSum(mti: String)  {
        lifecycleScope.launch{

            val sDate = startDate.dropLast(4)
            val eDate = endDate.dropLast(4)

            val end = Utils.shamsiToMiladi(eDate.split('/')[0].toInt(), eDate.split('/')[1].toInt(), eDate.split('/')[2].toInt())+endDate.takeLast(4)
            val start = Utils.shamsiToMiladi(sDate.split('/')[0].toInt(), sDate.split('/')[1].toInt(), sDate.split('/')[2].toInt()) + startDate.takeLast(4)

            val result = transactionRepository.getAmount(start, end, mti)
            when (mti) {
                "000000" -> {
                    binding.sumBuy.text = RialFormatter.format(
                        result.toString()
                    )
                    getSum("170000")
                }
                "170000" -> {
                    binding.sumBill.text = RialFormatter.format(
                        result.toString()
                    )
                    getSum("180000")
                }
                "180000" -> {
                    binding.sumTopup.text = RialFormatter.format(
                        result.toString()
                    )
                    getSum("190000")
                }
                "190000" -> {
                    binding.sumCharge.text = RialFormatter.format(result.toString())

                    loading.dismiss()
                    if (isAllZero()) {
                        Utils.makeSnack(binding.imgPrint, "تراکنشی موجود نیست!", Snackbar.LENGTH_SHORT).show()
                        binding.lnrData.visibility = View.GONE
                        binding.txtEmpty.visibility = View.VISIBLE
                        binding.imgPrint.isEnabled = false
                    } else {
                        binding.lnrData.visibility = View.VISIBLE
                        binding.txtEmpty.visibility = View.GONE
                        binding.imgPrint.isEnabled = true
                    }
                }
            }
        }
    }

    private fun getFailureCount(mti: String) {
        lifecycleScope.launch {

            val sDate = startDate.dropLast(4)
            val eDate = endDate.dropLast(4)

            val end = Utils.shamsiToMiladi(eDate.split('/')[0].toInt(), eDate.split('/')[1].toInt(), eDate.split('/')[2].toInt())+endDate.takeLast(4)
            val start = Utils.shamsiToMiladi(sDate.split('/')[0].toInt(), sDate.split('/')[1].toInt(), sDate.split('/')[2].toInt()) + startDate.takeLast(4)

            val result = transactionRepository.getFailureCount(start, end, mti)
                when (mti) {
                    "000000" -> {
                        binding.failBuy.text = result.toString()
                        getFailureCount("170000")
                    }
                    "170000" -> {
                        binding.failBill.text = result.toString()
                        getFailureCount("180000")
                    }
                    "180000" -> {
                        binding.failTopup.text = result.toString()
                        getFailureCount("190000")
                    }
                    "190000" -> {
                        binding.failCharge.text = result.toString()
                        getSum("000000")
                    }
                }
            
        }
    }

    private fun isAllZero(): Boolean {
        return (binding.successBuy.text.toString() == "0"
                && binding.successBill.text.toString() == "0"
                && binding.successCharge.text.toString() == "0"
                && binding.successTopup.text.toString() == "0"
                && binding.failBuy.text.toString() == "0"
                && binding.failBill.text.toString() == "0"
                && binding.failCharge.text.toString() == "0"
                && binding.failTopup.text.toString() == "0")
    }
}