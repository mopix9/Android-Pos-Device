/*
package com.masa.aryan.main.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import com.masa.aryan.BuildConfig
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentSwipeBinding
import com.masa.aryan.utils.AryanTime
import com.masa.aryan.utils.TransactionChecker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SwipeFragment : BaseFragment<FragmentSwipeBinding>() {

    @Inject
    lateinit var appContext: Context
    private val settingsRepository : ISettingsRepository by lazy{ DependencyManager.provideSettingsRepository(appContext) }
    private val cardReader : MagCardInterface by lazy {
        DeviceSDKManager.getMagCardInterface(appContext)!!
    }

    private lateinit var merchant : String

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSwipeBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        TransactionChecker.callBack.observe(viewLifecycleOwner,{
            when(it){
                TransactionChecker.TransactionCheckerStatus.ShowAdviceIcon ->{
                    cardReader.closeCardReader()
                    binding.settlement.visibility = View.VISIBLE
                }
                TransactionChecker.TransactionCheckerStatus.ShowReverseIcon ->{
                    cardReader.closeCardReader()
                    binding.settlement.visibility = View.VISIBLE
                }
                TransactionChecker.TransactionCheckerStatus.HideIcon ->{
                    binding.settlement.visibility = View.GONE
                }
                TransactionChecker.TransactionCheckerStatus.Finished ->{
                    binding.settlement.visibility = View.GONE
                    startCardReader()
                }
            }
        })

        lifecycleScope.launch(Dispatchers.IO) {
            merchant = settingsRepository.getValue(SettingsNames.MerchantName.name)?.value ?: "خوش آمدید"
            withContext(Dispatchers.Main){
                binding.txtMerchant.text = merchant
            }
        }

        AryanTime.getTime.observe(viewLifecycleOwner,{
            binding.txtDate.text = it
        })

        binding.menu.setOnClickListener {
            navigate(this,R.id.action_swipeFragment_to_settingsFragment)
        }

        binding.verApp.text = BuildConfig.VERSION_NAME

    }

    private fun startCardReader() {
        lifecycleScope.launch(Dispatchers.IO) {
            cardReader.read()
        }
        cardReader.cardTrack2LiveData.observe(viewLifecycleOwner,{
            if(merchant != "خوش آمدید") { //check has logon
                if (!it.equals("") && it.contains("=")) {
                    Log.d("CARD", it)
                    val beepInterface: BeepInterface? = DeviceSDKManager.getBeepInterface(context)
                    beepInterface?.beep(BeepType.BEEP_TYPE_PROMPT)

                    navigate(this, R.id.action_swipeFragment_to_buyOptionsFragment, bundleOf("Track2" to it)
                    )
                } else {
                    Utils.makeSnack(
                        binding.root,
                        getString(R.string.card_corrupted),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    startCardReader()
                }
            }else{
                Utils.makeSnack(binding.root, "راه اندازی اولیه انجام نشده است!", Snackbar.LENGTH_SHORT).show()
                startCardReader()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        startCardReader()
        TransactionChecker.check(appContext)
    }
    override fun onPause() {
        super.onPause()
        cardReader.closeCardReader()
    }

}*/
