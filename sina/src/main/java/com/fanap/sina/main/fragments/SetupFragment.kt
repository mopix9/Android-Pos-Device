package com.fanap.sina.main.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.tms.model.ConfigList
import com.fanap.corepos.tms.model.ConfigNames
import com.fanap.corepos.tms.model.Config_Arg
import com.fanap.corepos.tms.model.UpdateVersion_Arg
import com.fanap.corepos.tms.utils.TmsConstants
import com.fanap.corepos.tms.viewmodel.TmsViewModel
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.Utils
import com.fanap.sina.R
import com.fanap.sina.databinding.FragmentSetupBinding
import com.google.gson.Gson
import com.wang.avi.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : DialogFragment() {

    @Inject
    lateinit var shared: SharedPreferences
    @Inject
    lateinit var appContext : Context
    private val transactionRepository: ITransactionRepository by lazy {
        DependencyManager.provideTransactionRepository(appContext)
    }
    private val viewModel : TmsViewModel by viewModels()
    private lateinit var binding: FragmentSetupBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSetupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(R.drawable.white_background)


        lifecycleScope.launch {
            if (shared.getBoolean(BuildConfig.VERSION_CODE.toString(), true)) {
                val result = viewModel.updateVersionNo(UpdateVersion_Arg(
                    BuildConfig.VERSION_CODE.toString(), DeviceSDKManager.getSerialInterface(

                )?.serial?:"-", TmsConstants.TERMINAL_TYPE, TmsConstants.UPDATE_TYPE))
                if (result != null){
                    val editor = shared.edit()
                    editor.putBoolean(BuildConfig.VERSION_CODE.toString(), false)
                    editor.apply()
                }
            }
        }

        binding.txtWifi.setOnClickListener { startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) }
        binding.txtCellular.setOnClickListener {
            val intent = Intent()
            intent.component = ComponentName("com.android.settings", "com.android.settings.Settings\$DataUsageSummaryActivity")
            startActivity(intent)
        }

        binding.txtMenu.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_swipeFragment_to_settingsFragment)
        }

        startTimer()
    }

    private fun check() {
        binding.avi.visibility = View.VISIBLE
        binding.lnrNetwork.visibility = View.GONE
        binding.txtMessage.text = "راه اندازی اولیه ..."
        try {
            Handler().postDelayed({
                if (Utils.isCellularDataEnabled(appContext) || Utils.isNetworkConnected(appContext)) {
                    binding.txtMessage.text = "در حال بررسی شبکه..."
                    Handler().postDelayed({
                        if (Utils.getBatteryLevel(requireContext()) >= 10 || Utils.isCharging(requireActivity().applicationContext)) {
                            binding.txtMessage.text = "در حال بررسی باتری..."
                            timer?.cancel()
                            setTime()
                        } else {
                            binding.avi.visibility = View.GONE
                            binding.txtMessage.text = "باتری ضعیف است. لطفا دستگاه را به شارژر وصل کنید."
                        }
                    }, 1000)
                } else {
                    binding.avi.visibility = View.GONE
                    binding.lnrNetwork.visibility = View.VISIBLE
                    binding.txtMessage.text = "عدم دسترسی به اینترنت. لطفا اینترنت دستگاه را روشن کنید."
                }
            }, 1000)
        } catch (e: Exception) {
            Log.e("SetupFragment", e.message.toString())
        }
    }

    private fun setTime() {
            binding.txtMessage.text = "در حال بررسی ساعت دستگاه..."
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val result = viewModel.getConfig(Config_Arg(TmsConstants.ENCRYPTED_USERNAME, TmsConstants.TERMINAL_TYPE, DeviceSDKManager.getSerialInterface(

                    )?.serial ?: ""))
                    if (result != null && result.responseCode == 0 && result.configLst.isNotEmpty()) {
                        withContext(Dispatchers.Main){
                            setConfig(result.configLst)
                        }
                    }
                }catch (e: Exception) {
                    e.printStackTrace()
                }finally {
                    checkLastTransaction()
                }
            }
    }

    private suspend fun checkLastTransaction() {
        try {
                withContext(Dispatchers.Main){
                    binding.txtMessage.text = "در حال بررسی آخرین تراکنش..."
                }
                val transaction = transactionRepository.getLastBuyTransaction()

                transaction?.let {

                    val dialog = TransactionExceptionFragment()
                    dialog.isCancelable = false
                    val bundle = Bundle()
                    bundle.putString("TRANSACTION", Gson().toJson(transaction))

                    if (Utils.isAdviceAbleTransaction(transaction)) {
                        bundle.putBoolean("IsAdvice", true)
                        dialog.arguments = bundle

                        if (!dialog.isAdded)
                            dialog.show(parentFragmentManager, "")
                    } else if (Utils.isReverseAbleTransaction(transaction)) {
                        bundle.putBoolean("IsAdvice", false)
                        dialog.arguments = bundle

                        if (!dialog.isAdded)
                            dialog.show(parentFragmentManager, "")
                    }else
                        dismiss()
                }
                dismiss()
        }catch (e:Exception){
            dismiss()
        }
    }

    private fun setConfig(configLst: List<ConfigList>) {
        try {
            for (c in configLst) {
                if (c.name.equals(ConfigNames.CurrentTimestamp.name)) {
                    val x = Integer.toHexString(c.value!!.toInt())
                    DeviceSDKManager.getDateTimeInterface(context)?.setDateTime(IsoUtil.hexToDecimal(x))
                }
            }
        }catch (e:Exception){
            dismiss()
        }

    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(10000 * 60000, 5000) {
            override fun onTick(millisUntilFinished: Long) {
                check()
            }

            override fun onFinish() {
                startTimer()
            }
        }
        timer?.start()
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

}