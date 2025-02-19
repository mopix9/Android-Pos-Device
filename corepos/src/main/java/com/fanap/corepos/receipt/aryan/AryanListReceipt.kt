package com.fanap.corepos.receipt.aryan

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanap.corepos.R
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.databinding.AryanListReceiptBinding
import com.fanap.corepos.databinding.ReceiptListBinding
import com.fanap.corepos.device.print.utils.PrintPart
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.Receipt
import com.fanap.corepos.receipt.aryan.adapter.AryanTransactionListPrintAdapter
import com.fanap.corepos.receipt.sina.adapters.TransactionPrintAdapter
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.aryan.AryanUtils

class AryanListReceipt(val context: Context,val data: HashMap<IsoFields, Any>) : Receipt(){

    lateinit var binding : AryanListReceiptBinding

    override fun generate(): Bitmap {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aryan_list_receipt, null, false)
        val view = binding.root

        when(data[IsoFields.Buffer1]){
            PrintPart.All.name ->{
                setHeaderValues()
                setFooterValues()
            }
            PrintPart.Header.name ->{
                setHeaderValues()
                binding.footer.visibility = View.GONE
            }
            PrintPart.Body.name ->{
                binding.header.visibility = View.GONE
                binding.footer.visibility = View.GONE
            }
            PrintPart.Footer.name ->{
                binding.header.visibility = View.GONE
                setFooterValues()
            }
        }

        binding.rec.layoutManager = LinearLayoutManager(context)
        binding.rec.adapter = AryanTransactionListPrintAdapter(data[IsoFields.Buffer2] as List<Transaction>)

        return view.convertViewToBitmap()
    }

    private fun setHeaderValues(){
        binding.txtTime.text = AryanUtils.getReceiptTime()
        binding.txtDate.text = AryanUtils.getPersianDate()
        binding.txtTerminal.text = data[IsoFields.Terminal].toString()
        binding.txtTitle.text = data[IsoFields.TypeName].toString()
        binding.txtStartDate.text = data[IsoFields.StartDate].toString()
        binding.txtEndDate.text = data[IsoFields.EndDate].toString()
    }

    private fun setFooterValues(){
        binding.txtSum.text = "مبلغ به ریال "+ RialFormatter.format(data[IsoFields.Amount].toString())
        binding.txtCount.text = "تعداد کل تراکنش ها "+ data[IsoFields.Buffer3]
    }

}