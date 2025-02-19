package com.fanap.sina.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.R
import com.fanap.sina.databinding.FragmentTransactionExceptionBinding
import com.fanap.sina.main.viewmodel.TransactionExceptionViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class TransactionExceptionFragment : DialogFragment() {

    @Inject
    lateinit var appContext : Context

    private val transactionRepository: ITransactionRepository by lazy { DependencyManager.provideTransactionRepository(appContext) }
    private val viewModel : TransactionExceptionViewModel by viewModels()
    var repeatCount = 5
    private val transactionManager: IIso by lazy { DependencyManager.provideIsoTransaction() }
    lateinit var binding: FragmentTransactionExceptionBinding
    var isAdvice = false
    private lateinit var transaction: Transaction
    private lateinit var adviceTransaction : Transaction
    private lateinit var reverseTransaction : Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTransactionExceptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.white_background)
        binding.viewModel = viewModel
        try {
            isAdvice = requireArguments().getBoolean("IsAdvice")
            transaction = Gson().fromJson(requireArguments().getString("TRANSACTION"), Transaction::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        lifecycleScope.launch {
            delay(1000)
            val response = viewModel.makeReceipt(transaction)!!
            val lastTransaction = transactionRepository.getLastTransaction()
            val lastStan = transactionRepository.getLastValidTransactionForSettlement(transaction.stan?:"")?.stan ?: "0"

            if (isAdvice) {
                val adviceMap : HashMap<IsoFields,String>
                if (lastTransaction?.mti == "0220"){
                    adviceMap = viewModel.makeAdvice(response, transaction.card ?: "", lastStan, lastTransaction.stan)
                    adviceTransaction = lastTransaction
                }else{
                    adviceMap = viewModel.makeAdvice(response, transaction.card ?: "", lastStan)
                    adviceTransaction = viewModel.insertTransaction(adviceMap)
                }

                advice(adviceMap, response)
            }
            else {

                val reverseMap : HashMap<IsoFields,String>
                if (lastTransaction?.mti == "0400"){
                    reverseMap = viewModel.makeReverse(transaction, lastStan , lastTransaction.stan)
                    reverseTransaction = lastTransaction
                }else{
                    reverseMap = viewModel.makeReverse(transaction, lastStan)
                    reverseTransaction = viewModel.insertTransaction(reverseMap)
                }
                reverse(reverseMap)
            }
        }

    }

    private var adviceRepeatCount = 0
    private fun advice(adviceMap: HashMap<IsoFields, String>, response : HashMap<IsoFields, String>) {
        adviceRepeatCount ++
        lifecycleScope.launch(Dispatchers.IO) {
            val result = transactionManager.doTransaction(adviceMap)
            withContext(Dispatchers.Main) {
                if (result != null) {
                    val responseCode = result[IsoFields.Response] ?: ""
                    if (SinaUtils.isSuccessfulResponseForConfirmAndReverse(responseCode)) {

                        if(transaction.status == TransactionStatus.StartSuccessPrint.name) {
                            val receipt = ReceiptFactory.getReceiptBitmap(requireContext(), viewModel.makeReceipt(transaction)!!)
                            withContext(Dispatchers.IO){
                                DeviceSDKManager.getPrinterInterface(requireContext())?.printBitmap(receipt)
                            }
                        }
                        adviceTransaction.response = responseCode
                        adviceTransaction.status = TransactionStatus.AdviceResUnpacked.name
                        transaction.status = TransactionStatus.AdviceResUnpacked.name
                        viewModel.updateTransaction(adviceTransaction)
                        viewModel.updateTransaction(transaction)
                        dismiss()
                    } else {
                        if (adviceRepeatCount < 5)
                            advice(adviceMap,response)
                        else {
                            adviceTransaction.response = responseCode
                            adviceTransaction.status = TransactionStatus.AdviceResUnpackedResponseError.name
                            viewModel.updateTransaction(adviceTransaction)
                            dismiss()
                        }
                    }
                } else {
                    if (adviceRepeatCount < 5)
                        advice(adviceMap,response)
                    else {
                        adviceTransaction.status = TransactionStatus.AdviceSentTimeOut.name
                        viewModel.updateTransaction(adviceTransaction)
                        dismiss()
                    }
                }
            }
        }
    }

    private var reverseCount = 0
    private fun reverse(reverseMap: HashMap<IsoFields, String>) {
        reverseCount ++
        lifecycleScope.launch(Dispatchers.IO) {
            val result = transactionManager.doTransaction(reverseMap)
            withContext(Dispatchers.Main) {
                if (result != null) {
                    val responseCode = result[IsoFields.Response] ?: ""
                    if (SinaUtils.isSuccessfulResponseForConfirmAndReverse(responseCode)) {

                        if(transaction.status == TransactionStatus.TransactionSentTimeOut.name
                            || transaction.status == TransactionStatus.TransactionSent.name) {
                            val data = viewModel.makeReceipt(transaction)
                            data?.set(IsoFields.Status, TransactionReceiptStatus.UnReceivedResponse.name)
                            data?.set(IsoFields.FailMessage, "خطا در انجام تراکنش")
                            val receipt = ReceiptFactory.getReceiptBitmap(requireContext(), data!!)
                            withContext(Dispatchers.IO){
                                DeviceSDKManager.getPrinterInterface(requireContext())?.printBitmap(receipt)
                            }
                        }

                        reverseTransaction.response = responseCode
                        reverseTransaction.status = TransactionStatus.ReverseResUnpacked.name
                        transaction.status = TransactionStatus.ReverseResUnpacked.name
                        viewModel.updateTransaction(reverseTransaction)
                        viewModel.updateTransaction(transaction)
                        dismiss()
                    }else {
                        if (reverseCount < 5)
                            reverse(reverseMap)
                        else {
                            reverseTransaction.response = responseCode
                            reverseTransaction.status = TransactionStatus.ReverseResUnpackedResponseError.name
                            viewModel.updateTransaction(reverseTransaction)
                            dismiss()
                        }
                    }
                } else {
                    if (reverseCount < 5)
                        reverse(reverseMap)
                    else {
                        reverseTransaction.status = TransactionStatus.ReverseSentTimeOut.name
                        viewModel.updateTransaction(reverseTransaction)
                        dismiss()
                    }
                }
            }
        }
    }
}