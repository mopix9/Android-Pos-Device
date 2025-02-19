package com.masa.aryan.settings.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentConfigurationBinding
import com.masa.aryan.databinding.FragmentServerBinding
import com.masa.aryan.settings.management.viewmodel.ServerViewModel


class ServerFragment : BaseFragment<FragmentServerBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentServerBinding.inflate(inflater,container,false)

    private val viewModel: ServerViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.onError.observe(viewLifecycleOwner,{
            Utils.makeSnack(binding.root,it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.onSuccess.observe(viewLifecycleOwner,{
            Utils.makeSnack(binding.root,"تنظیمات با موفقیت ذخیره شدند.",Snackbar.LENGTH_SHORT)
            finish(this)
        })

        binding.back.setOnClickListener {
            finish(this)
        }

        binding.cancel.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
    }

}