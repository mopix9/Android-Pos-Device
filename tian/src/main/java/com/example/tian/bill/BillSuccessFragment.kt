package com.example.tian.bill

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tian.base.BaseFragment
import com.exmple.tian.R
import com.exmple.tian.databinding.FragmentBillSuccessBinding
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BillSuccessFragment : BaseFragment<FragmentBillSuccessBinding>() {

    @Inject
    lateinit var appContext : Context
    private val beep : BeepInterface? by lazy { DeviceSDKManager.getSmartPeakBeepInterface() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBillSuccessBinding {
        return FragmentBillSuccessBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer(10_000)

        onTimerFinish.observe(viewLifecycleOwner) {
         navigate(
          this,
          R.id.action_billSuccessFragment_to_swipeFragment
         )
        }
     binding.cancel.setOnClickListener { navigate(
         this,
         R.id.action_billSuccessFragment_to_swipeFragment
        ) }
        binding.back.setOnClickListener { navigate(
         this,
         R.id.action_billSuccessFragment_to_swipeFragment
        ) }

        beep?.beep(BeepType.BEEP_TYPE_SUCCESS)
    }

}