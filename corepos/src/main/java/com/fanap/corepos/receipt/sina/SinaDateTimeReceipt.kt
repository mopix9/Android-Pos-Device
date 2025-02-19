package com.fanap.corepos.receipt.sina

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
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

class SinaDateTimeReceipt {

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
                binding.dateTimeHeader.root.visibility = View.VISIBLE
                setHeader(headerData)
            }
            PrintPart.Header -> {
                binding.dateTimeHeader.root.visibility = View.VISIBLE
                setHeader(headerData)
                binding.footer.visibility = View.GONE
            }
            PrintPart.Body -> {
                binding.footer.visibility = View.GONE
                binding.dateTimeHeader.root.visibility = View.GONE
            }
            PrintPart.Footer -> {
                binding.dateTimeHeader.root.visibility = View.GONE
            }
        }


        binding.recyclerDetails.layoutManager = LinearLayoutManager(context)
        binding.recyclerDetails.adapter = TransactionPrintAdapter(data)

        return view.convertViewToBitmap()
    }

    private fun setHeader(headerData: HashMap<IsoFields, String>) {
        binding.dateTimeHeader.txtTerminalNumber.text = headerData[IsoFields.Terminal] ?: ""
        binding.dateTimeHeader.txtShopId.text = headerData[IsoFields.Merchant] ?: ""
        binding.dateTimeHeader.txtStartDate.text = headerData[IsoFields.StartDate] ?: ""
        binding.dateTimeHeader.txtEndDate.text = headerData[IsoFields.EndDate] ?: ""
    }

    private fun View.convertViewToBitmap() : Bitmap {
        return ReceiptUtils.getBitmapFromView(this)
    }


}