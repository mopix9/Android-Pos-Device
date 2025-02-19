package com.fanap.corepos.receipt.sina

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanap.corepos.R
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.databinding.ReceiptListBinding
import com.fanap.corepos.device.print.utils.PrintPart
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.sina.adapters.TransactionPrintAdapter
import com.fanap.corepos.receipt.util.ReceiptUtils
import com.fanap.corepos.utils.Utils

class SinaDailyReceipt {

    lateinit var binding : ReceiptListBinding

    fun generateReceipt(context: Context, data : List<Transaction>, part: PrintPart, headerData : HashMap<IsoFields, String>) : Bitmap?{

        if (data.isEmpty()) return null

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.receipt_list, null, false)
        val view = binding.root

        binding.txtDate.text = Utils.getPersianDate()
        binding.txtTime.text = Utils.getTime()
        binding.txtMerchantName.text = "${headerData[IsoFields.MerchantName]}-${headerData[IsoFields.MerchantPhone]}"

        when (part) {
            PrintPart.All -> {
                binding.dailyHeader.root.visibility = View.VISIBLE
                binding.dailyHeader.txtTerminal.text = headerData[IsoFields.Terminal] ?: ""
                binding.dailyHeader.txtMerchantValue.text = headerData[IsoFields.Merchant] ?: ""
            }
            PrintPart.Header -> {
                binding.dailyHeader.root.visibility = View.VISIBLE
                binding.dailyHeader.txtTerminal.text = headerData[IsoFields.Terminal] ?: ""
                binding.dailyHeader.txtMerchantValue.text = headerData[IsoFields.Merchant] ?: ""
                binding.footer.visibility = View.GONE
            }
            PrintPart.Body -> {
                binding.footer.visibility = View.GONE
                binding.dailyHeader.root.visibility = View.GONE
            }
            PrintPart.Footer -> {
                binding.dailyHeader.root.visibility = View.GONE
            }
        }


        binding.recyclerDetails.layoutManager = LinearLayoutManager(context)
        binding.recyclerDetails.adapter = TransactionPrintAdapter(data)

        return view.convertViewToBitmap()
    }

    private fun View.convertViewToBitmap() : Bitmap {
        return ReceiptUtils.getBitmapFromView(this)
    }

}