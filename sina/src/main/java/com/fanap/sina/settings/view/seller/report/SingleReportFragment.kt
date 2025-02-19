package com.fanap.sina.settings.view.seller.report

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.print.PrinterInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentSingleReportBinding
import com.fanap.sina.main.fragments.LoadingFragment
import com.fanap.sina.settings.view.seller.report.viewmodel.SingleReportViewModel
import com.fanap.sina.utils.SinaResponse
import com.fanap.corepos.utils.sina.SinaUtils
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SingleReportFragment : BaseFragment<FragmentSingleReportBinding>() {

    @Inject
    lateinit var loading : LoadingFragment
    @Inject
    lateinit var appContext: Context
    private val transactionRepository: ITransactionRepository by lazy {
        DependencyManager.provideTransactionRepository(
            appContext
        )
    }
    private var printer: PrinterInterface? = null
    private val viewModel: SingleReportViewModel by viewModels()
    private var transaction: Transaction? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSingleReportBinding.inflate(inflater, container, false)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        startTimer()
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })

        printer = DeviceSDKManager.getPrinterInterface(appContext)

        binding.edtSearch.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtSearch.onCreateInputConnection(EditorInfo()))
            false
        }

        onTimerFinish.observe(viewLifecycleOwner, { binding.back.callOnClick() })
        onBackPressed.observe(viewLifecycleOwner, { binding.back.callOnClick() })
        binding.back.setOnClickListener { finish(this) }

        binding.btnLastTransaction.setOnClickListener {
            lifecycleScope.launch {
                transaction = transactionRepository.getLastPrintableTransaction()
                if (transaction != null) {
                    showTransaction(transaction!!)
                    binding.card.visibility = View.VISIBLE
                    binding.txtEmpty.visibility = View.GONE
                } else {
                    binding.card.visibility = View.GONE
                    binding.txtEmpty.visibility = View.VISIBLE
                    binding.txtEmpty.text = "تراکنشی یافت نشد!"
                    transaction = null
                }
            }
        }

        binding.imgSearch.setOnClickListener {
            if (!TextUtils.isEmpty(binding.edtSearch.text.toString())) {
                binding.keyboard.slideDownAnimation()
                lifecycleScope.launch {
                    transaction = transactionRepository.getPrintableTransaction(binding.edtSearch.text.toString())
                    if (transaction != null) {
                        showTransaction(transaction!!)
                        binding.card.visibility = View.VISIBLE
                        binding.txtEmpty.visibility = View.GONE
                    } else {
                        binding.card.visibility = View.GONE
                        binding.txtEmpty.visibility = View.VISIBLE
                        binding.txtEmpty.text = "تراکنشی یافت نشد!"
                        Utils.makeSnack(binding.back, "تراکنشی یافت نشد!", Snackbar.LENGTH_SHORT)
                            .show()
                        transaction = null
                    }

                }
            } else Utils.makeSnack(
                binding.back,
                "لطفا شماره پیگیری تراکنش را وارد کنید!",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding.imgPrint.setOnClickListener {
            if (transaction != null) {
                loading.show(childFragmentManager, "")
                loading.isCancelable = false

                var receipt : Bitmap? = null
                viewModel.makeReceipt(transaction)?.let {
                    receipt = ReceiptFactory.getReceiptBitmap(requireContext(), it)
                }

                lifecycleScope.launch(Dispatchers.IO) {
                    receipt?.let {
                        val result = printer?.printBitmap(receipt!!) ?: false
                        withContext(Dispatchers.Main){
                            if (!result)
                                Utils.makeSnack(binding.root, "عدم چاپ رسید!", Snackbar.LENGTH_SHORT).show()
                            loading.dismiss()
                            finish(this@SingleReportFragment)
                        }
                    }
                }
            } else
                Utils.makeSnack(binding.imgPrint, "تراکنشی برای پرینت موجود نیست!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showTransaction(item: Transaction) {
        binding.txtBank.text = Utils.getBankName(item.card ?: "")
        binding.txtCard.text = item.card
        binding.txtDate.text = SinaUtils.getShamsiDateFromString(item.date?.take(6) ?: "")
        binding.txtTime.text = SinaUtils.getTimeFromString(item.date?.takeLast(6) ?: "")
        if (item.rrn.isNullOrEmpty())
            item.rrn = "-"
        binding.txtRrnValue.text = item.rrn.toString() + "/" + item.stan
        when (item.processCode) {
            "000000" -> drawBuy(item)
            "170000" -> drawBill(item)
            "190000" -> drawCharge(item)
            "180000" -> drawTopup(item)
        }
    }

    private fun drawBuy(item: Transaction) {
        binding.txtTitle.text = "خرید"
        binding.txtResponse.visibility = View.VISIBLE
        binding.txtResponse.text = SinaResponse.getResponse(item.response ?: "")
        binding.txtResponse.setTextColor(
            if (item.response.equals("00")
            ) Color.parseColor("#2E7D32") else Color.parseColor("#f20606")
        )
        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.row2.visibility = View.GONE
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE
            binding.txtRow1.text = "مبلغ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount ?: "") + "ریال"

        } else {
            binding.row1.visibility = View.GONE
            binding.row2.visibility = View.GONE
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE
        }
    }

    private fun drawTopup(item: Transaction) {
        binding.txtResponse.visibility = View.VISIBLE
        binding.txtTitle.text = "شارژ مستقیم"
        binding.txtResponse.text = SinaResponse.getResponse(item.response ?: "")
        binding.txtResponse.setTextColor(
            if (item.response.equals("00")
            ) Color.parseColor("#2E7D32") else Color.parseColor("#f20606")
        )
        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ شارژ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount ?: "") + "ریال"
            binding.row2.visibility = View.VISIBLE
            binding.txtRow2.text = item.description
            binding.txtRow2Value.text = item.description2
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE
        } else {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = item.description
            binding.txtRow1Value.text = item.description2
            binding.row2.visibility = View.GONE
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE
        }
    }

    private fun drawCharge(item: Transaction) {
        binding.txtResponse.visibility = View.VISIBLE

        binding.txtTitle.text = "خرید شارژ " + item.description
        binding.txtResponse.text = SinaResponse.getResponse(item.response ?: "")
        binding.txtResponse.setTextColor(
            if (item.response.equals("00"))
                Color.parseColor("#2E7D32")
            else Color.parseColor("#f20606")
        )
        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ شارژ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount ?: "") + "ریال"
            binding.row2.visibility = View.GONE
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE

        } else {
            binding.row1.visibility = View.GONE
            binding.row2.visibility = View.GONE
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE
        }
    }

    fun drawBill(item: Transaction) {
        binding.txtResponse.visibility = View.VISIBLE
        binding.txtTitle.text = "پرداخت قبض"
        binding.txtResponse.text = SinaResponse.getResponse(item.response ?: "")
        binding.row2.visibility = View.VISIBLE
        binding.txtRow2.text = "سازمان قبض"
        binding.txtRow2Value.text = item.description
        binding.row3.visibility = View.VISIBLE
        binding.txtRow3.text = "شناسه قبض"
        binding.txtRow3Value.text = item.description2
        binding.row4.visibility = View.VISIBLE
        binding.txtRow4.text = "شناسه پرداخت"
        binding.txtRow4Value.text = item.description3

        binding.txtResponse.setTextColor(
            if (item.response.equals("00"))
                Color.parseColor("#2E7D32")
            else Color.parseColor("#f20606")
        )
        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount ?: "") + "ریال"
        } else {
            binding.row1.visibility = View.GONE
        }
    }

}