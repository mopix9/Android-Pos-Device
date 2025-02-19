package com.fanap.sina.settings.view.configuration

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.Settings
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.Utils
import com.fanap.sina.databinding.FragmentLogonBinding
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.main.fragments.LoadingFragment
import com.fanap.sina.settings.view.adapter.ProtocolAdapter
import com.fanap.sina.settings.view.configuration.viewmodel.LogonViewModel
import com.fanap.corepos.utils.sina.SinaUtils
import com.fanap.sina.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class LogonFragment : BaseFragment<FragmentLogonBinding>() {

    @Inject
    lateinit var appContext: Context
    @Inject
    lateinit var loading: LoadingFragment
    private val transactionManager: IIso by lazy { DependencyManager.provideIsoTransaction() }
    private val transactionRepository: ITransactionRepository by lazy { DependencyManager.provideTransactionRepository(appContext) }
    private val settingsRepository: ISettingsRepository by lazy { DependencyManager.provideSettingsRepository(appContext) }
    private val hsm: HSMInterface by lazy { DeviceSDKManager.getHSMInterface(appContext)!! }
    private val viewModel: LogonViewModel by viewModels()
    private lateinit var adapter: ProtocolAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogonBinding {
        return FragmentLogonBinding.inflate(inflater, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.edtIp.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtIp.onCreateInputConnection(EditorInfo()))
            binding.edtIp.setSelection(binding.edtIp.length())
            false
        }

        binding.edtPort.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtPort.onCreateInputConnection(EditorInfo()))
            binding.edtPort.setSelection(binding.edtPort.length())
            false
        }

        binding.edtTmsPort.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtTmsPort.onCreateInputConnection(EditorInfo()))
            binding.edtTmsPort.setSelection(binding.edtTmsPort.length())
            false
        }

        binding.edtTms.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtTms.onCreateInputConnection(EditorInfo()))
            binding.edtTms.setSelection(binding.edtTms.length())
            false
        }

        binding.edtTerminalNo.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtTerminalNo.onCreateInputConnection(EditorInfo()))
            binding.edtTerminalNo.setSelection(binding.edtTerminalNo.length())
            false
        }

        binding.edtMerchantNo.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtMerchantNo.onCreateInputConnection(EditorInfo()))
            binding.edtMerchantNo.setSelection(binding.edtMerchantNo.length())
            false
        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.serialNo.set(settingsRepository.getValue(SettingsNames.TerminalSerial.name)?.value ?: "")
            viewModel.terminalNo.set(settingsRepository.getValue(SettingsNames.TerminalNo.name)?.value ?: "")
            viewModel.merchantNo.set(settingsRepository.getValue(SettingsNames.MerchantNo.name)?.value ?: "")
            viewModel.ip.set(settingsRepository.getValue(SettingsNames.Ip.name)?.value ?: "")
            viewModel.port.set(settingsRepository.getValue(SettingsNames.Port.name)?.value ?: "")
            viewModel.setTmsAddress((settingsRepository.getValue(SettingsNames.Tms.name)?.value ?: ""))
        }

        adapter = ProtocolAdapter(requireContext())
        binding.spnProtocol.adapter = adapter

        startTimer()
        onTouchListener.observe(viewLifecycleOwner, { startTimer() })
        binding.edtIp.addTextChangedListener { startTimer() }
        binding.edtMerchantNo.addTextChangedListener { startTimer() }
        binding.edtPort.addTextChangedListener { startTimer() }
        binding.edtSerialNo.addTextChangedListener { startTimer() }
        binding.edtTerminalNo.addTextChangedListener { startTimer() }
        binding.edtTms.addTextChangedListener { startTimer() }
        binding.edtTmsPort.addTextChangedListener { startTimer() }
        onTimerFinish.observe(viewLifecycleOwner, { binding.back.callOnClick() })
        onBackPressed.observe(viewLifecycleOwner, { binding.back.callOnClick() })
        binding.back.setOnClickListener { navigate(this,R.id.action_logonFragment_to_configurationFragment) }

        viewModel.onConfirmClicked.observe(viewLifecycleOwner,{
            logon()
        })

        viewModel.onError.observe(viewLifecycleOwner,{
            Utils.makeSnack(binding.root,it,Snackbar.LENGTH_LONG).show()
        })
    }

    private fun logon(){
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                stopTimer()
                loading.show(childFragmentManager, "")
            }
            val stanList = transactionRepository.getStanSet()
            val map = HashMap<IsoFields, String>()
            map.apply {
                put(IsoFields.Mti, "0800")
                put(IsoFields.ProcessCode, "250000")
                put(IsoFields.LastStan, stanList[0].toString())
                put(IsoFields.Stan, stanList[1].toString())
                put(IsoFields.TransactionTime, SinaUtils.getTime())
                put(IsoFields.TransactionDate, SinaUtils.getDate())
                put(IsoFields.Serial, viewModel.serialNo.get()!!)
                put(IsoFields.Terminal, viewModel.terminalNo.get()!!)
                put(IsoFields.Merchant, viewModel.merchantNo.get()!!)
                put(IsoFields.Status, TransactionStatus.LogonSent.name)
            }

            val transaction = transactionRepository.insert(map)

            val result = transactionManager.doTransaction(map)

            withContext(Dispatchers.Main) {
                startTimer()
                loading.dismiss()
                if (result != null) {
                    val responseCode = result[IsoFields.Response] ?: ""
                    transaction.response = responseCode
                    transaction.status = TransactionStatus.LogonResReceived.name
                    transactionRepository.updateTransaction(transaction)
                    if (responseCode == "00") {

                        settingsRepository.insert(Settings(SettingsNames.TerminalNo.name,viewModel.terminalNo.get()!!))
                        settingsRepository.insert(Settings(SettingsNames.MerchantNo.name,viewModel.merchantNo.get()!!))
                        settingsRepository.insert(Settings(SettingsNames.Tms.name, viewModel.makeTmsAddress()))

                        settingsRepository.insert(Settings(SettingsNames.MerchantName.name,result[IsoFields.MerchantName]?:""))
                        settingsRepository.insert(Settings(SettingsNames.Phone.name,result[IsoFields.MerchantPhone]?:""))
                        settingsRepository.insert(Settings(SettingsNames.Address.name,result[IsoFields.Address]?:""))

                        hsm.loadMasterKey(IsoUtil.bytesToHex(IsoUtil.getDefaultMacKey()!!))
                        hsm.loadMacKey(result[IsoFields.MacKey])
                        hsm.loadPinKey(result[IsoFields.PinKey])
                        hsm.loadDataKey(result[IsoFields.DataKey])

                        Utils.makeSnack(binding.root,"راه اندازی اولیه با موفقیت انجام شد.",Snackbar.LENGTH_LONG).show()
                        binding.back.callOnClick()
                    }else{
                        Utils.makeSnack(binding.root,"خطا: $responseCode", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Utils.makeSnack(binding.root, "عدم دریافت پاسخ", Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }
}