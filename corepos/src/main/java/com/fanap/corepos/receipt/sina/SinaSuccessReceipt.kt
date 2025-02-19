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
import com.fanap.corepos.receipt.sina.adapters.SuccessTransactionPrintAdapter
import com.fanap.corepos.receipt.util.ReceiptUtils
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils

class SinaSuccessReceipt {

    lateinit var binding : ReceiptListBinding

    fun generateReceipt(context: Context, data : List<Transaction>, allData : List<Transaction>, part: PrintPart, headerData : HashMap<IsoFields, String>) : Bitmap?{

        if (data.isEmpty()) return null

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.receipt_list, null, false)
        val view = binding.root

        binding.txtDate.text = Utils.getPersianDate()
        binding.txtTime.text = Utils.getTime()
        binding.txtMerchantName.text = "${headerData[IsoFields.MerchantName]}-${headerData[IsoFields.MerchantPhone]}"

        when (part) {
            PrintPart.All -> {
                binding.successHeader.root.visibility = View.VISIBLE
                setHeader(headerData,allData)
            }
            PrintPart.Header -> {
                binding.successHeader.root.visibility = View.VISIBLE
                setHeader(headerData,allData)
                binding.footer.visibility = View.GONE
            }
            PrintPart.Body -> {
                binding.footer.visibility = View.GONE
                binding.successHeader.root.visibility = View.GONE
            }
            PrintPart.Footer -> {
                binding.successHeader.root.visibility = View.GONE
            }
        }


        binding.recyclerDetails.layoutManager = LinearLayoutManager(context)
        binding.recyclerDetails.adapter = SuccessTransactionPrintAdapter(data,allData)

        return view.convertViewToBitmap()
    }

    private fun setHeader(headerData: HashMap<IsoFields, String>, allData: List<Transaction>) {
        binding.successHeader.txtTerminalNumber.text = headerData[IsoFields.Terminal] ?: ""
        binding.successHeader.txtShopId.text = headerData[IsoFields.Merchant] ?: ""
        binding.successHeader.txtStartDate.text = headerData[IsoFields.StartDate]?.split(" ")?.get(0) ?: ""
        binding.successHeader.txtEndDate.text = headerData[IsoFields.EndDate]?.split(" ")?.get(0) ?: ""

        var title = ""
        when(allData[0].processCode){
            "000000" -> title = "خرید"
            "170000" -> title = "قبض"
            "180000" -> title = "شارژ مستقیم"
            "190000" -> title = "شارژ"
        }

        binding.successHeader.txtReportType.text = title
        binding.successHeader.txtSum.text = "-/"+RialFormatter.format(allData.sumOf { it.amount?.toLong() ?: 0 }.toString()) + "ریال"
    }

    private fun View.convertViewToBitmap() : Bitmap {
        return ReceiptUtils.getBitmapFromView(this)
    }

}