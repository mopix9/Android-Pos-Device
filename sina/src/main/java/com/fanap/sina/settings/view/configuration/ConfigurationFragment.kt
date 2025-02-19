package com.fanap.sina.settings.view.configuration

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentConfigurationBinding


class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfigurationBinding {
        return FragmentConfigurationBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })
        onTimerFinish.observe(viewLifecycleOwner,{ finish(this) })

        binding.back.setOnClickListener { finish(this) }
        onBackPressed.observe(viewLifecycleOwner,{ finish(this) })

        binding.bankConfiguration.setOnClickListener {
            navigate(this,R.id.action_configurationFragment_to_passwordFragment
                , bundleOf("IsTerminalPass" to false
                    , "Destination" to R.id.action_passwordFragment_to_logonFragment))
        }

        binding.updateConfiguration.setOnClickListener {
            navigate(this,R.id.action_configurationFragment_to_passwordFragment
            , bundleOf("IsTerminalPass" to false
                    , "Destination" to R.id.action_passwordFragment_to_updateConfigurationFragment))
        }

        binding.fileManager.setOnClickListener {
            navigate(this,R.id.action_configurationFragment_to_passwordFragment
                , bundleOf("IsTerminalPass" to false))
        }

        setFragmentResultListener("IS_PASSWORD_CORRECT"){ _ , bundle ->
            if (bundle.getBoolean("IS_CORRECT",false)){
                val i = Intent(Intent.ACTION_MAIN)
                i.component = ComponentName("com.newpos.appmanager", "com.newpos.appmanager.Main")
                startActivity(i)
            }
        }
    }
}