package com.masa.aryan.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingsBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.management.setOnClickListener {
            navigate(this,R.id.action_settingsFragment_to_passwordFragment
                , bundleOf("IsTerminalPass" to false
                    , "Destination" to R.id.action_passwordFragment_to_managementFragment)
            )
        }

        binding.shop.setOnClickListener {
            navigate(this,R.id.action_settingsFragment_to_passwordFragment
                , bundleOf("IsTerminalPass" to true
                    , "Destination" to R.id.action_passwordFragment_to_shopFragment)
            )
        }

        binding.buyer.setOnClickListener {
            navigate(this,R.id.action_settingsFragment_to_buyerFragment)
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
    }


}