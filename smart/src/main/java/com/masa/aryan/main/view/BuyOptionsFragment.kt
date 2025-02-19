package com.masa.aryan.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentBuyOptionsBinding


class BuyOptionsFragment : BaseFragment<FragmentBuyOptionsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBuyOptionsBinding {
        return FragmentBuyOptionsBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer(10000)
        onTimerFinish.observe(viewLifecycleOwner,{
            finish(this)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })

        binding.lnrBuy.setOnClickListener {
            navigate(this,R.id.action_buyOptionsFragment_to_buyFragment,arguments)
        }

        binding.lnrBalance.setOnClickListener {
            navigate(this,R.id.action_buyOptionsFragment_to_balanceFragment,arguments)
        }

        binding.lnrBill.setOnClickListener {
            navigate(this,R.id.action_buyOptionsFragment_to_billFragment,arguments)
        }

        binding.lnrCharge.setOnClickListener {
            navigate(this,R.id.action_buyOptionsFragment_to_chargeFragment,arguments)
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
    }

}