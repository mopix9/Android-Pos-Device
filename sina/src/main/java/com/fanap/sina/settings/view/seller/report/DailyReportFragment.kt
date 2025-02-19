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
import com.fanap.corepos.receipt.sina.SinaDailyReceipt
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentDailyReportBinding
import com.fanap.sina.main.fragments.LoadingFragment
import com.fanap.sina.settings.view.seller.report.adapter.TransactionAdapter
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.base.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.collections.set

@AndroidEntryPoint
class DailyReportFragment : BaseFragment<FragmentDailyReportBinding>() {

    @Inject
    lateinit var loading: LoadingFragment

    @Inject
    lateinit var appContext: Context
    private val transactionRepository: ITransactionRepository by lazy {
        DependencyManager.provideTransactionRepository(
            appContext
        )
    }
    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(appContext)
    }
    private var counter = 0L
    private var printCounter = 0
    private lateinit var adapter: TransactionAdapter
    private var isLoading = false
    private lateinit var transactionList: MutableList<Transaction>
    private lateinit var printList: MutableList<Transaction>
    private lateinit var lists: List<List<Transaction>>
    private var printer: PrinterInterface? = null
    private val headerData = HashMap<IsoFields, String>()
    private val viewModel : BaseViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDailyReportBinding = FragmentDailyReportBinding.inflate(inflater, container, false)

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

        onTimerFinish.observe(viewLifecycleOwner, { binding.back.callOnClick() })
        onBackPressed.observe(viewLifecycleOwner, { binding.back.callOnClick() })
        binding.back.setOnClickListener { finish(this) }

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

        loading.show(childFragmentManager, "")
        lifecycleScope.launch {
            val transactions = transactionRepository.getTransactionsByDateLazy(
                SinaUtils.getDate() + "000000",
                SinaUtils.getDate() + SinaUtils.getTime(),
                counter
            )
            loading.dismiss()
            if (transactions?.isNotEmpty() == true) {
                transactionList.addAll(transactions)
                binding.txtEmpty.visibility = View.GONE
                adapter.notifyDataSetChanged()
                printList.addAll(
                    transactionRepository.getTransactionsByDate(
                        SinaUtils.getDate() + "000000",
                        SinaUtils.getDate() + SinaUtils.getTime()
                    )!!
                )
            } else binding.txtEmpty.visibility = View.VISIBLE

        }

        binding.print.setOnClickListener {
            if (printList.size > 0) {
                lists = Utils.listOfLists(printList, 4)!!
                print()
            } else Utils.makeSnack(
                binding.back,
                "تراکنشی برای پرینت موجود نیست!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
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
                                 bitmap = SinaDailyReceipt().generateReceipt(requireContext(), list, PrintPart.All, headerData)
                            }
                            bitmap?.let {
                                result = printer?.printBitmap(it) ?: false
                            }
                        }
                        index == 0 -> {
                            withContext(Dispatchers.Main) {
                                bitmap = SinaDailyReceipt().generateReceipt(requireContext(), list, PrintPart.Header, headerData)
                            }
                            bitmap?.let {
                                result = printer?.printBitmap(it) ?: false
                            }
                        }
                        index == lists.size - 1 -> {
                            withContext(Dispatchers.Main) {
                                bitmap = SinaDailyReceipt().generateReceipt(requireContext(), list, PrintPart.Footer, headerData)
                            }
                            bitmap?.let {
                                result = printer?.printBitmap(it) ?: false
                            }
                        }
                        else -> {
                            withContext(Dispatchers.Main) {
                                 bitmap = SinaDailyReceipt().generateReceipt(requireContext(), list, PrintPart.Body,headerData)
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
            finish(this@DailyReportFragment)
        else
            Utils.makeSnack(binding.root, "عدم چاپ رسید!", Snackbar.LENGTH_SHORT).show()
    }


    private fun getMoreItems(offset: Long) = lifecycleScope.launch {
        isLoading = true
        val transactions = transactionRepository.getTransactionsByDateLazy(
            SinaUtils.getDate() + "000000",
            SinaUtils.getDate() + SinaUtils.getTime(),
            offset * 10
        )
        if (transactions?.isNotEmpty() == true) {
            transactionList.addAll(transactions)
            adapter.notifyDataSetChanged()
            isLoading = false
        }
    }
}