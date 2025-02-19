package com.example.tian.settings.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tian.base.BaseFragment
import com.example.tian.settings.management.viewmodel.ServerViewModel
import com.exmple.tian.databinding.FragmentServerBinding
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar


class ServerFragment : BaseFragment<FragmentServerBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentServerBinding.inflate(inflater,container,false)

    private val viewModel: ServerViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.onError.observe(viewLifecycleOwner) {
            Utils.makeSnack(binding.root, it, Snackbar.LENGTH_LONG).show()
        }

        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Utils.makeSnack(binding.root, "تنظیمات با موفقیت ذخیره شدند.", Snackbar.LENGTH_SHORT)
            finish(this)
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        binding.cancel.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner) {
            finish(this)
        }
    }

}