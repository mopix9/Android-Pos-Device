package com.fanap.sina.main.fragments

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.BuildConfig
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentSplashBinding
import java.io.File

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.version.text = "Version: " + BuildConfig.VERSION_CODE.toString()
        startTimer(3000)

        onTimerFinish.observe(viewLifecycleOwner,{
            if (SinaUtils.getDeviceName().contains("amp",true))
                navigate(this,R.id.action_splashFragment_to_swipeFragment)
        })

        try {
            val directory = File(Environment.getExternalStorageDirectory(), "/apps")
            if (directory.isDirectory)
                directory.listFiles()?.forEach { child ->
                    child.delete()
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}