package com.example.tian.settings.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tian.base.BaseFragment
import com.example.tian.settings.management.viewmodel.MerchantPasswordViewModel
import com.exmple.tian.databinding.FragmentMerchantPasswordBinding
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MerchantPasswordFragment  : BaseFragment<FragmentMerchantPasswordBinding>() {

    private val supervisorChangePassViewModel: MerchantPasswordViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMerchantPasswordBinding = FragmentMerchantPasswordBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = supervisorChangePassViewModel
        startTimer()

        onBackPressed.observe(viewLifecycleOwner) {
            finish(this)
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        binding.cancel.setOnClickListener {
            finish(this)
        }

        supervisorChangePassViewModel.onError.observe(viewLifecycleOwner) {
            Utils.makeSnack(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }

        supervisorChangePassViewModel.onSuccess.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                Utils.makeSnack(binding.root, "رمز با موفقیت تغییر یافت.", Snackbar.LENGTH_SHORT)
                    .show()
                finish(this@MerchantPasswordFragment)
            }
        }

    }

}