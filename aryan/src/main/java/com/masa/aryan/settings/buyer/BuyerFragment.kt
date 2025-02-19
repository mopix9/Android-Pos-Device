package com.masa.aryan.settings.buyer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentBuyerBinding
import com.masa.aryan.settings.shop.EDIT_PREF_TEXT


class BuyerFragment : BaseFragment<FragmentBuyerBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBuyerBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settlement.setOnClickListener {
            navigate(
             this,
             R.id.action_buyerFragment_to_settlementFragment
            )
        }

        binding.on.setOnClickListener {
            navigate(this,R.id.action_buyerFragment_to_logonFragment)

        }
        binding.off.setOnClickListener {
            navigate(this,R.id.action_buyerFragment_to_getInfoFragment)
        }

        binding.back.setOnClickListener {
            finish(this)
        }



        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })




    }




}