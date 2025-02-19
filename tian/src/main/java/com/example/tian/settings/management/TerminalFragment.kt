package com.example.tian.settings.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tian.base.BaseFragment
import com.example.tian.settings.management.viewmodel.TerminalViewModel
import com.exmple.tian.databinding.FragmentTerminalBinding
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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