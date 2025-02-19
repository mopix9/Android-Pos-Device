package com.fanap.sina.settings.view.seller.terminal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.tms.model.Update_Arg
import com.fanap.corepos.tms.utils.TmsConstants
import com.fanap.corepos.tms.viewmodel.TmsViewModel
import com.fanap.corepos.utils.Utils
import com.fanap.sina.BuildConfig
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentTerminalBinding
import com.fanap.sina.main.fragments.LoadingFragment
import com.fanap.sina.settings.view.seller.UpdateFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class TerminalFragment : BaseFragment<FragmentTerminalBinding>() {

    private val viewModel : TmsViewModel by viewModels()
    @Inject
    lateinit var loading : LoadingFragment

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTerminalBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        startTimer()

        onTimerFinish.observe(viewLifecycleOwner,{
            finish(this)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })

        binding.back.setOnClickListener { finish(this)}

        binding.changePassword.setOnClickListener { v ->
//            showPasswordBottomSheet(
//                true,
//                R.id.action_passwordFragment2_to_terminalPasswordFragment
//            )
        }

        binding.forgetPassword.setOnClickListener {
//            if (NavHostFragment.findNavController(this@TerminalFragment).currentDestination!!
//                    .id == R.id.terminalFragment
//            ) NavHostFragment.findNavController(this)
//                .navigate(R.id.action_terminalFragment_to_resetTerminalPasswordFragment)
        }
        binding.brightness.setOnClickListener {
            navigate(this, R.id.action_terminalFragment_to_brightnessFragment)
        }
        binding.update.setOnClickListener {
            loading.show(childFragmentManager,"")
            try {
                lifecycleScope.launch(Dispatchers.IO) {
                    val serial = DeviceSDKManager.getSerialInterface()?.serial ?: ""
                    val result = viewModel.getLastVersion(Update_Arg(BuildConfig.VERSION_CODE.toString(),serial, TmsConstants.ENCRYPTED_USERNAME,1,3))
                    withContext(Dispatchers.Main){
                        loading.dismiss()
                        if (result != null){
                            if (result.responseCode == 0) {
                                if (result.lastVersionNo ?: "" > BuildConfig.VERSION_CODE.toString()) {
                                    if (result.downloadUrl?.isNotBlank() == true) {
                                        val bundle = Bundle()
                                        bundle.putString("URL", result.downloadUrl)
                                        bundle.putBoolean("FORCE", true)
                                        val update = UpdateFragment()
                                        if (!update.isAdded) {
                                            update.arguments = bundle
                                            update.show(childFragmentManager, "")
                                        }
                                    }
                                } else Utils.makeSnack(binding.root, "نسخه شما به روز است", Snackbar.LENGTH_SHORT).show()
                            } else Utils.makeSnack(binding.back, result.responseDesc, Snackbar.LENGTH_SHORT).show()
                        } else Utils.makeSnack(binding.root, "عدم دسترسی به TMS", Snackbar.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}