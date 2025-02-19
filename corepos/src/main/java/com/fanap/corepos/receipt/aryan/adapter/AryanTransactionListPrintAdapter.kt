package com.fanap.corepos.receipt.aryan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fanap.corepos.R
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.databinding.AryanTransactionListItemBinding
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.aryan.AryanUtils
import com.fanap.corepos.utils.sina.SinaUtils

class AryanTransactionListPrintAdapter(val data: List<Transaction>) :
    RecyclerView.Adapter<AryanTransactionListPrintAdapter.TransactionHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TransactionHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: AryanTransactionListItemBinding = AryanTransactionListItemBinding.bind(v)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.aryan_transaction_list_item, parent, false)
        return TransactionHolder(v)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val item: Transaction = data[position]
        holder.binding.txtStan.text = item.stan
        holder.binding.txtAmount.text = RialFormatter.format(item.amount?:"")
        holder.binding.txtDateTime.text = AryanUtils.getShamsiDateFromString(item.date?.take(4) ?: "").drop(5) +AryanUtils.getTimeForReceipt(item.date?.takeLast(6)?: "").take(5)


        holder.binding.txtTitle.text = when (item.processCode) {
            "000000" -> "خرید"
            "170000" -> "پرداخت قبض"
            "180000" -> "شارژ وچر"
            "220000" -> "شارژ مستقیم"
            else -> ""
        }
    }

}
