package com.example.tian.settings.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentConfigurationBinding

class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentConfigurationBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logon.setOnClickListener {
            navigate(
                this,
                R.id.action_configurationFragment_to_logonFragment
            )
        }

        binding.getInfo.setOnClickListener {
            navigate(
                this,
                R.id.action_configurationFragment_to_getInfoFragment
            )
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
    }

}