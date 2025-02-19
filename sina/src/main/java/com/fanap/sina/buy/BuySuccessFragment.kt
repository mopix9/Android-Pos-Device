package com.fanap.sina.buy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentBuySuccessBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BuySuccessFragment : BaseFragment<FragmentBuySuccessBinding>() {

    @Inject
    lateinit var appContext : Context
    private val beep : BeepInterface? by lazy { DeviceSDKManager.getBeepInterface() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBuySuccessBinding {
        return FragmentBuySuccessBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer(10_000)

        onTimerFinish.observe(viewLifecycleOwner,{ binding.back.callOnClick() })
        binding.cancel.setOnClickListener { binding.back.callOnClick() }
        binding.back.setOnClickListener { navigate(this,R.id.action_buySuccessFragment_to_swipeFragment) }

        val result : HashMap<IsoFields, String> = arguments?.get("Result") as HashMap<IsoFields, String>
        val track2 : String = (arguments?.get("Track2") ?: "") as String

        binding.txtAmount.text = RialFormatter.format(Utils.removeZeros(result[IsoFields.Amount]?: ""))
        binding.txtTrackingNumber.text = Utils.removeZeros(result[IsoFields.Stan]?: "")
        binding.txtReferenceNumber.text = result[IsoFields.Rrn]?: ""
        binding.txtDateTime.text = SinaUtils.getTimeFromString(result[IsoFields.TransactionTime] ?: "000000")
        binding.txtBankName.text = Utils.getBankName(track2)
        binding.txtCardNumber.text = Utils.cardMask(track2)

        beep?.beep(BeepType.BEEP_TYPE_SUCCESS)

    }

}