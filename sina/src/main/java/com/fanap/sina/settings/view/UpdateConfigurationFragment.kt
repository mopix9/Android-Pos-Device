package com.fanap.sina.settings.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.Settings
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.utils.Utils
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentUpdateConfigurationBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class UpdateConfigurationFragment :BaseFragment<FragmentUpdateConfigurationBinding>() {

    @Inject
    lateinit var appContext: Context

    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(
            appContext
        )
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUpdateConfigurationBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer(20_000)
        onTimerFinish.observe(viewLifecycleOwner, {
            binding.cancel.callOnClick()
        })

        onBackPressed.observe(viewLifecycleOwner,{ finish(this) })
        lifecycleScope.launch(Dispatchers.IO) {
            val value = settingsRepository.getValue(SettingsNames.UpdateTimer.name)?.value ?: "60"
            withContext(Dispatchers.Main){
                binding.edtMinutes.setText(value)
            }
        }

        binding.confirm.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main){
                if (binding.edtMinutes.text.toString() != "") {
                    withContext(Dispatchers.IO){
                        settingsRepository.insert(Settings(SettingsNames.UpdateTimer.name, binding.edtMinutes.text.toString()))
                    }
                    Utils.makeSnack(binding.root, "به روز رسانی انجام شد.", Snackbar.LENGTH_SHORT).show()
                    binding.cancel.callOnClick()
                } else Utils.makeSnack(binding.root, "لطفا یک مقدار معتبر وارد کنید!", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.cancel.setOnClickListener {
            navigate(this,R.id.action_updateConfigurationFragment_to_configurationFragment)
        }

    }
}