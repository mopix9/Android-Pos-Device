
package com.fanap.sina.main.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.tms.utils.UpdateChecker
import com.fanap.corepos.utils.NetworkChecker
import com.fanap.corepos.utils.Utils
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentSwipeBinding
import com.fanap.sina.settings.view.seller.UpdateFragment
import com.fanap.sina.utils.SinaTime
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SwipeFragment : BaseFragment<FragmentSwipeBinding>() {

    private val TAG = this::class.java.simpleName
    @Inject
    lateinit var appContext: Context
    private val settingsRepository : ISettingsRepository by lazy{ DependencyManager.provideSettingsRepository(appContext) }
    private val transactionRepository : ITransactionRepository by lazy{ DependencyManager.provideTransactionRepository(appContext) }
    @Inject
    lateinit var setupFragment: SetupFragment
    private val cardReader : MagCardInterface by lazy {
        DeviceSDKManager.getMagCardInterface(appContext)!!
    }
    private lateinit var merchant : String

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSwipeBinding {
        return FragmentSwipeBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menu.setOnClickListener {
            navigate(this,R.id.action_swipeFragment_to_settingsFragment)
        }

//        lifecycleScope.launch {
//            val d = transactionRepository.getAll()
//            if (d != null) {
//                Log.e(TAG,d.size.toString())
//            }
//        }


        SinaTime.getTime.observe(viewLifecycleOwner,{
            binding.txtDate.text = it
        })

        lifecycleScope.launch(Dispatchers.IO) {
            merchant = settingsRepository.getValue(SettingsNames.MerchantName.name)?.value ?: "خوش آمدید"
            withContext(Dispatchers.Main){
                binding.txtMerchant.text = merchant
            }
        }

        UpdateChecker.onUpdateReceived.observe(viewLifecycleOwner,{
            if (it.isNotBlank()) {
                val bundle = Bundle()
                bundle.putString("URL", it)
                bundle.putBoolean("FORCE", true)
                val update = UpdateFragment()
                if (!update.isAdded) {
                    update.arguments = bundle
                    update.show(childFragmentManager, "")
                }
            }
        })

    }

    private fun startCardReader() {
        lifecycleScope.launch(Dispatchers.IO) {
            cardReader.read()
        }
        cardReader.cardTrack2LiveData.observe(viewLifecycleOwner,{
            if(merchant != "خوش آمدید") {
                if (!it.equals("") && it.contains("=")) {
                    Log.d("CARD", it)
                    val beepInterface: BeepInterface? = DeviceSDKManager.getBeepInterface(context)
                    beepInterface?.beep(BeepType.BEEP_TYPE_PROMPT)

                    navigate(
                        this,
                        R.id.action_swipeFragment_to_buyOptionsFragment,
                        bundleOf("Track2" to it)
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
    }
    override fun onPause() {
        super.onPause()
        cardReader.closeCardReader()
    }
}