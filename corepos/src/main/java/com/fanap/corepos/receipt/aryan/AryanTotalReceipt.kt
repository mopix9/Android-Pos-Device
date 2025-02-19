package com.fanap.corepos.receipt.aryan

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.fanap.corepos.R
import com.fanap.corepos.databinding.AryanSettingsReceiptBinding
import com.fanap.corepos.databinding.AryanTotalReceiptBinding
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.Receipt
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.aryan.AryanUtils

class AryanTotalReceipt(val context: Context, val data: HashMap<IsoFields, String>) : Receipt() {

    private lateinit var binding: AryanTotalReceiptBinding

    override fun generate(): Bitmap {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aryan_total_receipt, null, false)
        val view = binding.getRoot()


        binding.txtTime.text = AryanUtils.getReceiptTime()
        binding.txtDate.text = AryanUtils.getPersianDate()
        binding.txtTerminal.text = data[IsoFields.Terminal]
        binding.txtTitle.text = data[IsoFields.TypeName]
        binding.txtStartDate.text = data[IsoFields.StartDate]
        binding.txtEndDate.text = data[IsoFields.EndDate]

        binding.txtBuyCount.text = data[IsoFields.Buffer1]
        binding.txtBuyAmount.text = data[IsoFields.Buffer2]?:""

        binding.txtBillCount.text = data[IsoFields.Buffer3]
        binding.txtBillAmount.text = data[IsoFields.Buffer4]?:""

        binding.txtTopupCount.text = data[IsoFields.Buffer5]
        binding.txtTopupAmount.text = data[IsoFields.Buffer6]?:""

        binding.txtChargeCount.text = data[IsoFields.Buffer7]
        binding.txtChargeAmount.text =data[IsoFields.Buffer8]?:""

        val sum = (data[IsoFields.Buffer2]?.replace(",","")?:"0").toLong() + (data[IsoFields.Buffer4]?.replace(",","")?:"0").toLong()+ (data[IsoFields.Buffer6]?.replace(",","")?:"0").toLong()+ (data[IsoFields.Buffer8]?.replace(",","")?:"0").toLong()
        binding.txtSum.text = "مبلغ به ریال "+RialFormatter.format(sum.toString())

        return view.convertViewToBitmap()
    }
}