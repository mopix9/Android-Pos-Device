package com.example.tian.settings.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tian.base.BaseFragment
import com.exmple.tian.R
import com.exmple.tian.databinding.FragmentShopBinding

class ShopFragment : BaseFragment<FragmentShopBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reports.setOnClickListener {
            navigate(
                this,
                R.id.action_shopFragment_to_reportsFragment
            )
        }

        binding.rollRequest.setOnClickListener {
            navigate(
                this,
                R.id.action_shopFragment_to_sellerRollRequestFragment
            )
        }

        binding.back.setOnClickListener {
            navigate(
                this,
                R.id.action_shopFragment_to_settingsFragment
            )
        }

        binding.createUser.setOnClickListener {
            navigate(
                this,
                R.id.action_shopFragment_to_createUserFragment
            )
        }

        onBackPressed.observe(viewLifecycleOwner,{
            navigate(
                this,
                R.id.action_shopFragment_to_settingsFragment
            )
        })
    }

}