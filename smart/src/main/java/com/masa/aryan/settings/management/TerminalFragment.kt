package com.masa.aryan.settings.management

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentManagementBinding
import com.masa.aryan.databinding.FragmentTerminalBinding
import com.masa.aryan.settings.management.viewmodel.ManagementViewModel
import com.masa.aryan.settings.management.viewmodel.TerminalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TerminalFragment : BaseFragment<FragmentTerminalBinding>() {

    private val viewModel : TerminalViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTerminalBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        viewModel.onError.observe(viewLifecycleOwner,{
            Utils.makeSnack(binding.root,it,Snackbar.LENGTH_SHORT).show()
        })

        viewModel.onSuccess.observe(viewLifecycleOwner,{
            Utils.makeSnack(binding.root,"تنظیمات پایانه با موفقیت ذخیره شدند.",Snackbar.LENGTH_SHORT).show()
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