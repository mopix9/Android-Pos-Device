package com.masa.aryan.buy

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.sina.SinaUtils
import com.google.android.material.snackbar.Snackbar
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.buy.viewmodel.BuySuccessViewModel
import com.masa.aryan.buy.viewmodel.BuyViewModel
import com.masa.aryan.databinding.FragmentBuyBinding
import com.masa.aryan.main.view.LoadingFragment
import com.masa.aryan.settings.management.AMOUNT_PREF_KEY
import com.masa.aryan.settings.management.RECIPT_PREF_KEY1
import com.masa.aryan.settings.management.RECIPT_PREF_KEY2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class BuyFragment : BaseFragment<FragmentBuyBinding>() {

 @Inject
 lateinit var appContext: Context
 private val viewModell: BuyViewModel by viewModels()
// private lateinit var successViewModel : BuySuccessViewModel

 @Inject
 lateinit var appInfoAmount: SharedPreferences

 @Inject
 lateinit var loading: LoadingFragment
 private val transactionManager: IIso by lazy { DependencyManager.provideIsoTransaction() }


 private val hsm: HSMInterface by lazy { DeviceSDKManager.getHSMInterface(appContext)!! }
 private lateinit var track2: String
 private lateinit var adviceTransaction: Transaction
 private lateinit var reverseTransaction: Transaction
 private lateinit var transaction: Transaction

 override fun getViewBinding(
  inflater: LayoutInflater,
  container: ViewGroup?
 ): FragmentBuyBinding = FragmentBuyBinding.inflate(inflater, container, false)

 @SuppressLint("ClickableViewAccessibility")
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  binding.viewModel = viewModell




  track2 = arguments?.getString("Track2", "") ?: ""
  startTimer()

  binding.edtAmount.addTextChangedListener(RialFormatter(binding.edtAmount))

  onTimerFinish.observe(viewLifecycleOwner) {
   navigate(this, R.id.action_buyFragment_to_swipeFragment)
  }
  onBackPressed.observe(viewLifecycleOwner) {
   navigate(this, R.id.action_buyFragment_to_swipeFragment)
  }
  onTouchListener.observe(viewLifecycleOwner, { startTimer() })

  binding.back.setOnClickListener { navigate(this, R.id.action_buyFragment_to_swipeFragment) }
  binding.cancel.setOnClickListener { navigate(this, R.id.action_buyFragment_to_swipeFragment) }

  viewModell.onConfirmClicked.observe(viewLifecycleOwner, {
   showPinPad()
  })

  viewModell.onError.observe(viewLifecycleOwner, {
   Utils.makeSnack(binding.root, it, Snackbar.LENGTH_LONG).show()
  })
 }

 private fun showPinPad() {

  hsm.inputPin(track2, appContext)
  hsm.mutablePassword.observe(viewLifecycleOwner, {
   if (!it.equals("")) {
    doTransaction(it)
   } else {
    navigate(this, R.id.action_buyFragment_to_swipeFragment)
   }
  })
 }

 private fun doTransaction(pinBlock: String) {
  lifecycleScope.launch(Dispatchers.IO) {
   withContext(Dispatchers.Main) {
    stopTimer()
    loading.show(childFragmentManager, "")
    loading.isCancelable = false
   }

   val map = viewModell.makeTransaction(track2, pinBlock)
   transaction = viewModell.insertTransaction(map)
   val result = transactionManager.doTransaction(map)

   withContext(Dispatchers.Main) {
    if (result != null) {

     val responseCode = result[IsoFields.Response] ?: ""
     transaction.response = responseCode
     transaction.rrn = result[IsoFields.Rrn] ?: ""

//   get amount from db
     val finalAmount = transaction.amount!!.toInt()
//     get maxAmount from Shared in ReciptPrintSettingFragment
     val managerAmount = appInfoAmount.getString(AMOUNT_PREF_KEY, "")!!.replace(",", "")

//     get switches state

     val state1 = appInfoAmount.getBoolean(RECIPT_PREF_KEY1, true)
     val state2 = appInfoAmount.getBoolean(RECIPT_PREF_KEY2,true)


//     compare to print Recipt or no
     if ((responseCode == "00") && (managerAmount.toInt() < finalAmount) && state1) {
      transaction.status = TransactionStatus.StartSuccessPrint.name
      viewModell.updateTransaction(transaction)
      val receipt = ReceiptFactory.getReceiptBitmap(
       requireContext(),
       viewModell.makeReceipt(track2, transaction)
      )
      val printResult = DeviceSDKManager.getPrinterInterface(requireContext())
       ?.printBitmap(receipt)
      if (printResult == true) {
       transaction.status = TransactionStatus.ReceiptPrinted.name
       viewModell.updateTransaction(transaction)
      }

      val adviceMap = viewModell.makeAdvice(transaction, track2)
      adviceTransaction = viewModell.insertTransaction(adviceMap)
      advice(adviceMap, result)




     } else if
         ((responseCode == "00") && (managerAmount.toInt() < finalAmount) && !state1) {
      transaction.status = TransactionStatus.ReceiptPrinted.name
      viewModell.updateTransaction(transaction)
      val adviceMap = viewModell.makeAdvice(transaction, track2)
      adviceTransaction = viewModell.insertTransaction(adviceMap)
      advice(adviceMap, result)

     } else if

                    ((responseCode == "00") && (managerAmount.toInt() >= finalAmount)) {

      transaction.status = TransactionStatus.ReceiptPrinted.name
      viewModell.updateTransaction(transaction)

      val adviceMap = viewModell.makeAdvice(transaction, track2)
      adviceTransaction = viewModell.insertTransaction(adviceMap)
      advice(adviceMap, result)
     } else {
      transaction.status = TransactionStatus.TransactionResUnpackedResponseError.name
      viewModell.updateTransaction(transaction)
      val receipt = ReceiptFactory.getReceiptBitmap(
       requireContext(),
       viewModell.makeFailReceipt(track2, transaction)
      )
      DeviceSDKManager.getPrinterInterface(requireContext())?.printBitmap(receipt)
      navigate(
       this@BuyFragment, R.id.action_buyFragment_to_failFragment,
       bundleOf("Response" to responseCode)
      )
     }

    } else {
     transaction.response = "-1"
     transaction.status = TransactionStatus.TransactionSentTimeOut.name
     viewModell.updateTransaction(transaction)
     val receipt = ReceiptFactory.getReceiptBitmap(
      requireContext(),
      viewModell.makeNullResponseReceipt(track2, transaction)
     )
     val printResult = DeviceSDKManager.getPrinterInterface(requireContext())?.printBitmap(receipt)
     if (printResult == true) {
      transaction.status = TransactionStatus.ReceiptPrinted.name
      viewModell.updateTransaction(transaction)
     }

     val reverseMap = viewModell.makeReverse(transaction)
     reverseTransaction = viewModell.insertTransaction(reverseMap)
     reverse(reverseMap)
    }
   }
  }
 }

 private var adviceRepeatCount = 0
 private fun advice(adviceMap: HashMap<IsoFields, String>, response: HashMap<IsoFields, String>) {
  adviceRepeatCount++
  lifecycleScope.launch(Dispatchers.IO) {
   val result = transactionManager.doTransaction(adviceMap)
   withContext(Dispatchers.Main) {
    if (result != null) {
     val responseCode = result[IsoFields.Response] ?: ""
     if (SinaUtils.isSuccessfulResponseForConfirmAndReverse(responseCode)) {
      adviceTransaction.response = responseCode
      adviceTransaction.status = TransactionStatus.AdviceResUnpacked.name
      transaction.status = TransactionStatus.AdviceResUnpacked.name
      viewModell.updateTransaction(adviceTransaction)
      viewModell.updateTransaction(transaction)
      navigate(
       this@BuyFragment, R.id.action_buyFragment_to_buySuccessFragment,
       bundleOf("Result" to response, "Track2" to track2, "Amount" to transaction.amount)
      )
     } else {
      if (adviceRepeatCount < 5)
       advice(adviceMap, response)
      else {
       adviceTransaction.response = responseCode
       adviceTransaction.status = TransactionStatus.AdviceResUnpackedResponseError.name
       viewModell.updateTransaction(adviceTransaction)
       navigate(
        this@BuyFragment, R.id.action_buyFragment_to_buySuccessFragment,
        bundleOf("Result" to response, "Track2" to track2, "Amount" to transaction.amount)
       )
      }
     }
    } else {
     if (adviceRepeatCount < 5)
      advice(adviceMap, response)
     else {
      adviceTransaction.status = TransactionStatus.AdviceSentTimeOut.name
      viewModell.updateTransaction(adviceTransaction)
      navigate(
       this@BuyFragment, R.id.action_buyFragment_to_buySuccessFragment,
       bundleOf("Result" to response, "Track2" to track2, "Amount" to transaction.amount)
      )
     }
    }
   }
  }
 }

 private var reverseCount = 0
 private fun reverse(reverseMap: HashMap<IsoFields, String>) {
  reverseCount++
  lifecycleScope.launch(Dispatchers.IO) {
   val result = transactionManager.doTransaction(reverseMap)
   withContext(Dispatchers.Main) {
    if (result != null) {
     val responseCode = result[IsoFields.Response] ?: ""
     if (SinaUtils.isSuccessfulResponseForConfirmAndReverse(responseCode)) {
      reverseTransaction.response = responseCode
      reverseTransaction.status = TransactionStatus.ReverseResUnpacked.name
      transaction.status = TransactionStatus.ReverseResUnpacked.name
      viewModell.updateTransaction(reverseTransaction)
      viewModell.updateTransaction(transaction)
      navigate(
       this@BuyFragment, R.id.action_buyFragment_to_failFragment,
       bundleOf("Result" to "عدم دریافت پاسخ تراکنش")
      )
     } else {
      if (reverseCount < 5)
       reverse(reverseMap)
      else {
       reverseTransaction.response = responseCode
       reverseTransaction.status =
        TransactionStatus.ReverseResUnpackedResponseError.name
       viewModell.updateTransaction(reverseTransaction)
       navigate(
        this@BuyFragment, R.id.action_buyFragment_to_failFragment,
        bundleOf("Result" to "عدم دریافت پاسخ تراکنش")
       )
      }
     }
    } else {
     if (reverseCount < 5)
      reverse(reverseMap)
     else {
      reverseTransaction.status = TransactionStatus.ReverseSentTimeOut.name
      viewModell.updateTransaction(reverseTransaction)
      navigate(
       this@BuyFragment, R.id.action_buyFragment_to_failFragment,
       bundleOf("Result" to "عدم دریافت پاسخ تراکنش")
      )
     }
    }
   }
  }
 }

}