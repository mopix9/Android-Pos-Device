package com.fanap.sina.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanap.sina.R
import com.fanap.sina.databinding.FragmentSettingsBinding
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.utils.SinaTime

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })
        onTimerFinish.observe(viewLifecycleOwner,{ finish(this) })

        binding.back.setOnClickListener { finish(this) }
        onBackPressed.observe(viewLifecycleOwner,{ finish(this) })

        binding.configuration.setOnClickListener {
            navigate(this,R.id.action_settingsFragment_to_configurationFragment)
        }

        binding.seller.setOnClickListener {
            navigate(this,R.id.action_settingsFragment_to_sellerFragment)
        }

        SinaTime.getTime.observe(viewLifecycleOwner,{
            binding.txtDate.text = it
        })
    }
}