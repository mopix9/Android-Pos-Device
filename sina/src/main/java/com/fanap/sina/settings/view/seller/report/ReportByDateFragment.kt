package com.fanap.sina.settings.view.seller.report

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.print.PrinterInterface
import com.fanap.corepos.device.print.utils.PrintPart
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.sina.SinaDateTimeReceipt
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.base.BaseViewModel
import com.fanap.sina.databinding.FragmentReportByDateBinding
import com.fanap.sina.main.fragments.LoadingFragment
import com.fanap.sina.settings.view.seller.report.adapter.TransactionAdapter
import com.fanap.sina.settings.view.seller.report.viewmodel.SuccessfulTransactionsViewModel
import com.google.android.material.snackbar.Snackbar
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class ReportByDateFragment : BaseFragment<FragmentReportByDateBinding>() , TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    @Inject
    lateinit var loading: LoadingFragment
    @Inject
    lateinit var appContext : Context
    private val transactionRepository: ITransactionRepository by lazy {
        DependencyManager.provideTransactionRepository(appContext)
    }
    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(appContext)
    }
    private var counter = 0L
    private val printCounter = 0
    private lateinit var adapter: TransactionAdapter
    private var isLoading = false
    private lateinit var transactionList: MutableList<Transaction>
    private lateinit var printList:MutableList<Transaction>
    private lateinit var lists: List<List<Transaction>>

    private var selectedDate = ""
    private var isStartClicked = false
    private var startDate = ""
    private  var endDate = ""

    private var printer: PrinterInterface? = null
    private val headerData = HashMap<IsoFields, String>()
    private val viewModel : BaseViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportByDateBinding = FragmentReportByDateBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        startTimer()
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })
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

        printer = DeviceSDKManager.getPrinterInterface(appContext)

        onTimerFinish.observe(viewLifecycleOwner,{ binding.back.callOnClick() })
        onBackPressed.observe(viewLifecycleOwner,{ binding.back.callOnClick() })
        binding.back.setOnClickListener { finish(this) }

        binding.txtEmpty.text = "تاریخ شروع و پایان را انتخاب کنید."
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
                if (printList.size > 0) {
                    lists = Utils.listOfLists(printList, 4)!!
                    headerData[IsoFields.StartDate] = binding.txtStartDate.text.toString()
                    headerData[IsoFields.EndDate] = binding.txtEndDate.text.toString()
                    print()
                } else Utils.makeSnack(binding.imgPrint, "تراکنشی موجود نیست!", Snackbar.LENGTH_SHORT).show()
            } else Utils.makeSnack(binding.imgPrint, "لطفا تاریخ شروع و پایان را انتخاب کنید!", Snackbar.LENGTH_SHORT).show()
        }

        val linearLayoutManager = LinearLayoutManager(context)
        binding.recDaily.layoutManager = linearLayoutManager
        transactionList = ArrayList()
        printList = ArrayList()
        adapter = TransactionAdapter(transactionList)
        binding.recDaily.adapter = adapter
        binding.recDaily.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                if (!isLoading) {
                    getMoreItems(++counter)
                }
            }
        })
    }

    private fun showDatePicker() {
        val persianCalendar = PersianCalendar()
        val datePickerDialog = DatePickerDialog.newInstance(
            this,
            persianCalendar.persianYear,
            persianCalendar.persianMonth,
            persianCalendar.persianDay
        )
        datePickerDialog.show(childFragmentManager,"")
    }

    private fun showTimePicker() {
        val now = PersianCalendar()
        val tpd = TimePickerDialog.newInstance(
            this,
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
                getTransactions()
            } else {
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

    private fun getMoreItems(offset: Long) = lifecycleScope.launch {
        isLoading = true

        val sDate = startDate.dropLast(4)
        val eDate = endDate.dropLast(4)

        val end = Utils.shamsiToMiladi(eDate.split('/')[0].toInt(), eDate.split('/')[1].toInt(), eDate.split('/')[2].toInt())+endDate.takeLast(4)
        val start = Utils.shamsiToMiladi(sDate.split('/')[0].toInt(), sDate.split('/')[1].toInt(), sDate.split('/')[2].toInt()) + startDate.takeLast(4)

        val transactions = transactionRepository.getTransactionsByDateLazy(start, end, offset * 10)
        if (transactions?.isNotEmpty() == true) {
            transactionList.addAll(transactions)
            adapter.notifyDataSetChanged()
            isLoading = false
        }
    }

    private fun getTransactions() = lifecycleScope.launch {

        val sDate = startDate.dropLast(4)
        val eDate = endDate.dropLast(4)

        val end = Utils.shamsiToMiladi(eDate.split('/')[0].toInt(), eDate.split('/')[1].toInt(), eDate.split('/')[2].toInt())+endDate.takeLast(4)
        val start = Utils.shamsiToMiladi(sDate.split('/')[0].toInt(), sDate.split('/')[1].toInt(), sDate.split('/')[2].toInt()) + startDate.takeLast(4)

        isLoading = true
        counter = 0L
        transactionList.clear()
        adapter.notifyDataSetChanged()
        loading.show(childFragmentManager, "")
        val transactions = transactionRepository.getTransactionsByDateLazy(start, end, counter)
                loading.dismiss()
                if (!transactions.isNullOrEmpty()) {
                    transactionList.addAll(transactions)
                    binding.txtEmpty.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                    binding.recDaily.visibility = View.VISIBLE
                    binding.recDaily.smoothScrollToPosition(0)
                } else {
                    binding.txtEmpty.visibility = View.VISIBLE
                    binding.recDaily.visibility = View.GONE
                    binding.txtEmpty.text = "تراکنشی موجود نیست!"
                }
                isLoading = false
                val print = transactionRepository.getTransactionsByDate(start, end)
                    printList.clear()
                    if (!print.isNullOrEmpty())
                        printList.addAll(print)

    }

    private fun print() = lifecycleScope.launch(Dispatchers.Main) {
        stopTimer()
        loading.show(childFragmentManager, "")
        loading.isCancelable = false
        var result = false
        withContext(Dispatchers.IO) {
            lists.forEachIndexed { index, list ->
                val bitmap : Bitmap?
                when {
                    lists.size == 1 -> {
                        withContext(Dispatchers.Main) {
                            bitmap = SinaDateTimeReceipt().generateReceipt(requireContext(), list, PrintPart.All, headerData)
                        }
                        bitmap?.let {
                            result = printer?.printBitmap(it) ?: false
                        }
                    }
                    index == 0 -> {
                        withContext(Dispatchers.Main) {
                            bitmap = SinaDateTimeReceipt().generateReceipt(requireContext(), list, PrintPart.Header, headerData)
                        }
                        bitmap?.let {
                            result = printer?.printBitmap(it) ?: false
                        }
                    }
                    index == lists.size - 1 -> {
                        withContext(Dispatchers.Main) {
                            bitmap = SinaDateTimeReceipt().generateReceipt(requireContext(), list, PrintPart.Footer, headerData)
                        }
                        bitmap?.let {
                            result = printer?.printBitmap(it) ?: false
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            bitmap = SinaDateTimeReceipt().generateReceipt(requireContext(), list, PrintPart.Body,headerData)
                        }
                        bitmap?.let {
                            result = printer?.printBitmap(it) ?: false
                        }
                    }
                }
            }
        }
        startTimer()
        loading.dismiss()
        if (result)
            finish(this@ReportByDateFragment)
        else
            Utils.makeSnack(binding.imgPrint, "عدم چاپ رسید!", Snackbar.LENGTH_SHORT).show()

    }
}