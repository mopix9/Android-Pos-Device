package com.fanap.corepos.receipt.aryan

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.fanap.corepos.R
import com.fanap.corepos.databinding.AryanSettingsReceiptBinding
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.Receipt
import saman.zamani.persiandate.BuildConfig

class AryanSettingsReceipt(val context: Context,val data: HashMap<IsoFields, String>) : Receipt() {

    private lateinit var binding: AryanSettingsReceiptBinding

    override fun generate(): Bitmap {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aryan_settings_receipt, null, false)
        val view = binding.getRoot()

        binding.txtMerchantName.text = data[IsoFields.MerchantName]  ?: ""
        binding.txtMerchant.text = "${data[IsoFields.Merchant]?.trim()}"
        binding.txtMerchantTerminal.text = "${data[IsoFields.Terminal]}"

//        TODO NEWLAND

//        binding.model.text = DeviceName.NEWLAND.nameAndModel

//                TODO TIANYU
        binding.model.text = "TianYU P90"
//        binding.model.text = DeviceName.TIANYU.nameAndModel

//        TODO SMART PEAK
  /*      binding.model.text =when(Build.MODEL){
            "P600" -> "SmartPeak p600"
            "P1000" -> "SmartPeak p1000"
            else -> {"SmartPeak"}
        }*/
//        binding.model.text = DeviceName.SMARTPEAK.nameAndModel
        binding.version.text = BuildConfig.VERSION_NAME
//        binding.serialNumber.text = DeviceSDKManager.getSerialInterface()!!.serial
//        binding.serialNumber.text = DeviceSDKManager.getSerialSmartPeak()!!.serial
        binding.serialNumber.text ="00001504P6000003690"
        binding.txtIp.text = data[IsoFields.Buffer1]  ?: ""
        binding.txtPort.text = data[IsoFields.Buffer2]  ?: ""
        binding.txtNii.text = data[IsoFields.NiiCode]  ?: ""



        return view.convertViewToBitmap()
    }
}