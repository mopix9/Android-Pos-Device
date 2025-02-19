package com.fanap.sina.settings.view.seller

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.utils.Utils
import com.fanap.sina.R
import com.fanap.sina.databinding.FragmentUpdateBinding
import com.fanap.sina.utils.DownloadService
import kotlinx.coroutines.launch
import java.io.File


class UpdateFragment : DialogFragment() {

    private val settingsRepository: ISettingsRepository by lazy { DependencyManager.provideSettingsRepository(requireContext()) }

    private lateinit var binding: FragmentUpdateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        isCancelable = false
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        val bundle = arguments
        lifecycleScope.launch {
            val tmsAddress = settingsRepository.getValue(SettingsNames.Tms.name)?.value ?: ""
            if (!Utils.isServiceRunning(requireContext(), "com.fanap.sina.utils.DownloadService")) {
                DownloadService.startDownload(
                    requireContext(),
                    tmsAddress + "api/" + bundle!!.getString("URL")
                )
            }
            DownloadService.onFinish?.observe(viewLifecycleOwner) { result ->
                if (result)
                    install()
                else {
                    dismiss()
                    Toast.makeText(requireContext(), "خطا در به روز رسانی", Toast.LENGTH_SHORT).show()
                }
            }
            DownloadService.onProgress?.observe(viewLifecycleOwner) { progress ->
                binding.progress.progress = progress
                binding.percent.text = "$progress %"
            }
        }

        return binding.root
    }

    private fun install() {
        val shared = requireContext().getSharedPreferences("DATA", Context.MODE_PRIVATE)
        /*if (appInstalled("com.android.packageinstaller")) {
            val shared = requireContext().getSharedPreferences("DATA", Context.MODE_PRIVATE)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.component = ComponentName(
                "com.android.packageinstaller",
                "com.android.packageinstaller.PackageInstallerActivity"
            )
            intent.setDataAndType(
                Uri.fromFile(File(shared.getString("Apk", ""))),
                "application/vnd.android.package-archive"
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setPackage("com.android.packageinstaller")
            startActivity(intent)
            dismiss()
        } else {
            val shared = requireContext().getSharedPreferences("DATA", Context.MODE_PRIVATE)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(
                Uri.fromFile(File(shared.getString("Apk", ""))),
                "application/vnd.android.package-archive"
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
            dismiss()
        }*/
        val result = DeviceSDKManager.getInstallInterface()?.installApk(shared.getString("Apk", "")) //requier address looklike /mnt/sdcard/terminal3.apk
        Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_LONG).show()
        dismiss()
    }


    private fun appInstalled(uri: String): Boolean {
        val pm = requireActivity().packageManager
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_TITLE,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth
        )
    }
}
