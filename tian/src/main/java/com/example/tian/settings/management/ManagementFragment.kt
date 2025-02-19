package com.example.tian.settings.management

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tian.base.BaseFragment
import com.example.tian.settings.management.viewmodel.ManagementViewModel
import com.exmple.tian.R
import com.exmple.tian.databinding.FragmentManagementBinding
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ManagementFragment : BaseFragment<FragmentManagementBinding>() {

    @Inject
    lateinit var appContext: Context
    private val viewModel : ManagementViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentManagementBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.connectionSettings.setOnClickListener{
            navigate(
             this,
             R.id.action_managementFragment_to_connectivityFragment
            )
        }

        binding.configuration.setOnClickListener {
            navigate(
             this,
             R.id.action_managementFragment_to_configurationFragment
            )
        }

        binding.terminalSettings.setOnClickListener {
            navigate(
             this,
             R.id.action_managementFragment_to_terminalFragment
            )
        }

        binding.resetPassword.setOnClickListener{
            navigate(
             this,
             R.id.action_managementFragment_to_merchantPasswordFragment
            )
        }
        binding.reciptPrintSettingFragment.setOnClickListener {
            navigate(
             this,
             R.id.action_managementFragment_to_selectReciptTypeToPrintFragment
            )
        }


        binding.printSettings.setOnClickListener {
            lifecycleScope.launch {
                val receipt = ReceiptFactory.getReceiptBitmap(requireContext(), viewModel.makeSettingsReceipt())
                val printResult = DeviceSDKManager.getPrinterInterface(appContext)?.printBitmap(receipt) ?: false
                if (!printResult)
                    Utils.makeSnack(binding.root,"پرینت ناموفق!",Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.back.setOnClickListener {
            navigate(
             this,
             R.id.action_managementFragment_to_settingsFragment
            )
        }

        onBackPressed.observe(viewLifecycleOwner,{
            navigate(
             this,
             R.id.action_managementFragment_to_settingsFragment
            )
        })
    }

}