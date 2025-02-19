package com.fanap.sina.settings.view.seller

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentSellerBinding

class SellerFragment : BaseFragment<FragmentSellerBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSellerBinding = FragmentSellerBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()

        onTimerFinish.observe(viewLifecycleOwner,{
            finish(this)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })

        onTouchListener.observe(viewLifecycleOwner,{
            startTimer()
        })

        binding.back.setOnClickListener { finish(this)}

        binding.data.setOnClickListener {
            val intent = Intent()
            intent.component = ComponentName(
                "com.android.settings",
                "com.android.settings.Settings\$DataUsageSummaryActivity"
            )
            startActivity(intent)
        }
        binding.wifi.setOnClickListener { startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) }

        binding.soundSettings.setOnClickListener { startActivity(Intent(Settings.ACTION_SOUND_SETTINGS)) }

        binding.reports.setOnClickListener {
            navigate(this,R.id.action_sellerFragment_to_passwordFragment
                , bundleOf("IsTerminalPass" to true
                    , "Destination" to R.id.action_passwordFragment_to_reportFragment)
            )
        }

        binding.terminal.setOnClickListener {
            navigate(this, R.id.action_sellerFragment_to_terminalFragment)
        }

    }

}