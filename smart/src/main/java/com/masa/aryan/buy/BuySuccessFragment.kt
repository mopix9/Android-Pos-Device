package com.masa.aryan.buy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.sina.SinaUtils
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.buy.viewmodel.BuySuccessViewModel
import com.masa.aryan.databinding.FragmentBuySuccessBinding
import com.masa.aryan.settings.management.AMOUNT_PREF_KEY
import com.masa.aryan.settings.management.RECIPT_PREF_KEY1
import com.masa.aryan.settings.management.RECIPT_PREF_KEY2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BuySuccessFragment : BaseFragment<FragmentBuySuccessBinding>() {


 @Inject
 lateinit var appContext: Context
 private val beep: BeepInterface? by lazy { DeviceSDKManager.getBeepInterface() }
 private val viewModel: BuySuccessViewModel by viewModels()

 @Inject
 lateinit var appInfoAmount: SharedPreferences


 override fun getViewBinding(
  inflater: LayoutInflater,
  container: ViewGroup?
 ): FragmentBuySuccessBinding {
  return FragmentBuySuccessBinding.inflate(inflater, container, false)
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  binding.viewModel = viewModel


  startTimer(10_000)



  onTimerFinish.observe(viewLifecycleOwner, { binding.back.callOnClick() })
  binding.cancel.setOnClickListener { binding.back.callOnClick() }
  binding.back.setOnClickListener {
   navigate(
    this,
    R.id.action_buySuccessFragment_to_swipeFragment
   )
  }

  val result: HashMap<IsoFields, String> = arguments?.get("Result") as HashMap<IsoFields, String>
  val track2: String = (arguments?.get("Track2") ?: "") as String
  val amount: String = (arguments?.get("Amount") ?: "") as String



  binding.txtAmount.text = amount
  binding.txtTrackingNumber.text = Utils.removeZeros(result[IsoFields.Stan] ?: "")
  binding.txtReferenceNumber.text = result[IsoFields.Rrn] ?: ""
  binding.txtDateTime.text =
   SinaUtils.getTimeFromString(result[IsoFields.TransactionTime] ?: "000000")
  binding.txtBankName.text = Utils.getBankName(track2)
  binding.txtCardNumber.text = Utils.cardMask(track2)

  val state2 = appInfoAmount.getBoolean(RECIPT_PREF_KEY2, true)
  val managerAmount = appInfoAmount.getString(AMOUNT_PREF_KEY, "")!!.replace(",", "")


  if (state2 && (managerAmount.toInt() < amount.toInt())) {

   binding.sellerReceipt.setOnClickListener {

    recipt()
    navigate(this@BuySuccessFragment, R.id.action_buySuccessFragment_to_swipeFragment)
   }

  } else if (!state2 || managerAmount.toInt() >= amount.toInt())
   binding.sellerReceipt.visibility = View.GONE

  beep?.beep(BeepType.BEEP_TYPE_SUCCESS)

 }

 private fun recipt() {
  val managerAmount = appInfoAmount.getString(AMOUNT_PREF_KEY, "")!!.replace(",", "")
  val amount: String = (arguments?.get("Amount") ?: "") as String
  val result: HashMap<IsoFields, String> = arguments?.get("Result") as HashMap<IsoFields, String>
  val track2: String = (arguments?.get("Track2") ?: "") as String
  val state2 = appInfoAmount.getBoolean(RECIPT_PREF_KEY2, true)
  if (managerAmount.toInt() < amount.toInt() && state2) {
   val receipt = ReceiptFactory.getReceiptBitmap(
    requireContext(),
    viewModel.makeReceipt(track2, result, amount)
   )
   lifecycleScope.launch(Dispatchers.IO) {
    DeviceSDKManager.getPrinterInterface(appContext)?.printBitmap(receipt)
   }
  } else
   if (managerAmount.toInt() >= amount.toInt())
    binding.sellerReceipt.visibility = View.GONE

 }


}