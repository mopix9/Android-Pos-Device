package com.masa.aryan.settings.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentSellerRollRequestBinding
import com.masa.aryan.databinding.FragmentShopBinding
import com.masa.aryan.settings.shop.viewmodel.RollViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SellerRollRequestFragment : BaseFragment<FragmentSellerRollRequestBinding>() {

    private val viewModel : RollViewModel by viewModels()
    private val transactionManager: IIso by lazy { DependencyManager.provideIsoTransaction() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSellerRollRequestBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })

        doTransaction()
    }

    private fun doTransaction() {
        lifecycleScope.launch (Dispatchers.IO) {
            delay(500)
            withContext(Dispatchers.Main) {
                stopTimer()
            }

            val map = viewModel.makeTransaction()
            val result = transactionManager.doTransaction(map)

            withContext(Dispatchers.Main) {
                binding.loading.visibility = View.GONE
                if (result != null) {
                    val responseCode = result[IsoFields.Response] ?: ""
                    if (responseCode == "00") {
                        binding.txtMessage.text = "عملیات با موفقیت انجام شد."
                        delay(1500)
                        finish(this@SellerRollRequestFragment)
                    }else{
                        binding.txtMessage.text = "خطا $responseCode"
                        delay(1500)
                        finish(this@SellerRollRequestFragment)
                    }
                } else {
                    binding.txtMessage.text = "عدم دریافت پاسخ"
                    delay(1500)
                    finish(this@SellerRollRequestFragment)
                }
            }
        }
    }

}