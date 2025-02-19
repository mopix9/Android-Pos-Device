package com.masa.aryan.bill

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentBillSuccessBinding
import com.masa.aryan.settings.shop.EDIT_PREF_TEXT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BillSuccessFragment : BaseFragment<FragmentBillSuccessBinding>() {

    @Inject
    lateinit var appContext : Context
    private val beep : BeepInterface? by lazy { DeviceSDKManager.getBeepInterface() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBillSuccessBinding {
        return FragmentBillSuccessBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer(10_000)

        onTimerFinish.observe(viewLifecycleOwner,{navigate(
         this,
         R.id.action_billSuccessFragment_to_swipeFragment
        ) })
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