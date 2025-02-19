package com.fanap.corepos.receipt.sina.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fanap.corepos.R
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.databinding.SuccessTransactionItemBinding
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.sina.SinaUtils

class SuccessTransactionPrintAdapter(private val data: List<Transaction>, private val allData: List<Transaction>) :
    RecyclerView.Adapter<SuccessTransactionPrintAdapter.TransactionHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    inner class TransactionHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: SuccessTransactionItemBinding = SuccessTransactionItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.success_transaction_item, parent, false)
        return TransactionHolder(v)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val item: Transaction = data[position]
        val positionInAllData = allData.indexOfFirst { transaction -> transaction.id == item.id }//getIndex(data[position].id) //allData.indexOf(data.get(position));

        if (positionInAllData == 0 || allData[positionInAllData - 1].date?.take(6) != item.date?.take(6))
            holder.binding.txtDate.text = SinaUtils.getShamsiDateFromString(item.date?.take(6) ?: "")
         else
            holder.binding.txtDate.visibility = View.GONE

        holder.binding.txtTime.text = SinaUtils.getTimeForReceipt(item.date?.takeLast(6) ?: "")
        holder.binding.txtTrackingNumber.text = item.stan
        holder.binding.txtAmount.text = RialFormatter.format(item.amount ?: "") + "/-"
    }

}
