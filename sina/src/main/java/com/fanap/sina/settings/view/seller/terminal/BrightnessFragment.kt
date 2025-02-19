package com.fanap.sina.settings.view.seller.terminal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.Settings
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.di.DependencyManager
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentBrightnessBinding
import com.fanap.sina.databinding.FragmentBrightnessBindingImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class BrightnessFragment : BaseFragment<FragmentBrightnessBinding>() {

    @Inject
    lateinit var appContext : Context
    private val settingsRepository: ISettingsRepository by lazy { DependencyManager.provideSettingsRepository(appContext) }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBrightnessBinding.inflate(inflater,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()

        setupViews()

        binding.seek.max = 10
        onTimerFinish.observe(viewLifecycleOwner,{
            finish(this)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })

        onTouchListener.observe(viewLifecycleOwner,{
            startTimer()
        })

        binding.back.setOnClickListener { finish(this) }

        binding.seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                setBrightness(progress.toFloat() / 10)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.update.setOnClickListener {
            update()
        }
    }

    private fun setBrightness(input: Float) {
        val layout = requireActivity().window.attributes
        layout.screenBrightness = input
        requireActivity().window.attributes = layout
    }

    private fun setupViews() = lifecycleScope.launch (Dispatchers.IO){
        val bright = settingsRepository.getValue(SettingsNames.BrightLevel.name)?.value?.toFloat() ?: 1
        withContext(Dispatchers.Main){
            binding.seek.progress = (bright.toFloat() *10).toInt()
        }
    }

    private fun update() = lifecycleScope.launch (Dispatchers.IO){
        settingsRepository.insert(Settings(SettingsNames.BrightLevel.name, (binding.seek.progress.toFloat() / 10).toString()))
        withContext(Dispatchers.Main){
            finish(this@BrightnessFragment)
        }
    }


}