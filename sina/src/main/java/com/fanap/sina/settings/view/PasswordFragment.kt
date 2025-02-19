package com.fanap.sina.settings.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentPasswordBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import saman.zamani.persiandate.PersianDate
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PasswordFragment : BaseFragment<FragmentPasswordBinding>() {


    @Inject
    lateinit var appContext: Context
    private var isTerminalPass: Boolean = true
    private lateinit var terminalPassword: String

    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(
            appContext
        )
    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPasswordBinding.inflate(inflater,container,false)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer(20_000)
        isTerminalPass = requireArguments().getBoolean("IsTerminalPass")

        binding.edtPassword.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtPassword.onCreateInputConnection(EditorInfo()))
            false
        }

        lifecycleScope.launch(Dispatchers.IO) {
            terminalPassword =
                settingsRepository.getValue(SettingsNames.Password.name)?.value ?: "1234"
            if (terminalPassword == "0000" && isTerminalPass)
                withContext(Dispatchers.Main) {
                    navigate(this@PasswordFragment, requireArguments().getInt("Destination"))
                }
        }

        onTimerFinish.observe(viewLifecycleOwner, {
            finish(this)
        })

        onTimerTick.observe(viewLifecycleOwner,{
            binding.timer.text = it
        })
        onBackPressed.observe(viewLifecycleOwner,{ finish(this) })

        if (!isTerminalPass) {
            binding.title.text = "لطفا رمز سرپرست را وارد کنید"
            binding.edtPassword.hint = "رمز سرپرست"
            val filterArray = arrayOfNulls<InputFilter>(1)
            filterArray[0] = LengthFilter(6)
            binding.edtPassword.filters = filterArray
        }
//        else {
//            repository.getValue(SettingsNames.BlockedCounter.name())
//                .observe(viewLifecycleOwner) { settings ->
//                    blockCounter = if (settings == null) 0 else settings.getValue().toInt()
//                }
//        }

        binding.confirm.setOnClickListener {
            val destination : Int  = requireArguments().getInt("Destination",0)
            if (TextUtils.isEmpty(binding.edtPassword.text.toString()))
                Utils.makeSnack(binding.root, "لطفا رمز عبور را وارد کنید.", Snackbar.LENGTH_SHORT).show()
            else {
                if (isTerminalPass) {
                    if (binding.edtPassword.text.toString() == terminalPassword) {
                        if (destination != 0) {
                            navigate(this, destination)
                        }else{
                            setFragmentResult("IS_PASSWORD_CORRECT", bundleOf("IS_CORRECT" to true))
                            finish(this)
                        }
                    } else {
                        Utils.makeSnack(binding.root, "رمز عبور اشتباه است.", Snackbar.LENGTH_SHORT).show()
                    }

                } else {
                    val rightNow = Calendar.getInstance()
                    val serial: String = DeviceSDKManager.getSerialInterface()?.serial ?: "-------"
                    val currentHourIn24Format = rightNow[Calendar.HOUR_OF_DAY]
                    val date = PersianDate()
                    Log.d("PASS", (date.shDay + currentHourIn24Format).toString())
                    if (binding.edtPassword.text.toString() == serial.substring(serial.length - 2) + date.shDay + currentHourIn24Format
                    ) {
                        if (destination != 0) {
                            navigate(this, destination)
                        }else{
                            setFragmentResult("IS_PASSWORD_CORRECT", bundleOf("IS_CORRECT" to true))
                            finish(this)
                        }
                    } else {
                        Utils.makeSnack(binding.root, "رمز عبور اشتباه است.", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

        }

        binding.cancel.setOnClickListener { finish(this) }
    }

}