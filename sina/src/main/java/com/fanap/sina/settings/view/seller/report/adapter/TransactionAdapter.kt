package com.fanap.sina.settings.view.seller.report.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.sina.R
import com.fanap.sina.databinding.TransactionItemBinding
import com.fanap.sina.utils.SinaResponse
import com.fanap.corepos.utils.sina.SinaUtils

class TransactionAdapter(val data: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TransactionHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: TransactionItemBinding = TransactionItemBinding.bind(v)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionHolder(v)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val item: Transaction = data[position]
        holder.binding.txtBank.text = Utils.getBankName(item.card?.substring(0, 6) ?: "000000000000")
        holder.binding.txtCard.text = item.card
        holder.binding.txtDate.text = SinaUtils.getShamsiDateFromString(item.date?.take(6) ?: "")
        holder.binding.txtTime.text = SinaUtils.getTimeFromString(item.date?.takeLast(6) ?: "")
        if (item.rrn.isNullOrEmpty())
            item.rrn = "-"
        holder.binding.txtRrnValue.text = item.rrn.toString() + "/" + item.stan
        when (item.processCode) {
            "000000" -> drawBuy(item, holder.binding)
            "170000" -> drawBill(item, holder.binding)
            "190000" -> drawCharge(item, holder.binding)
            "180000" -> drawTopup(item, holder.binding)
        }
    }

    private fun drawBuy(item: Transaction, binding: TransactionItemBinding) {
        binding.txtTitle.text = "خرید"
        binding.txtResponse.setTextColor(
            if (item.response.equals("00"))
                Color.parseColor("#2E7D32")
            else
                Color.parseColor("#f20606")
        )
        if (item.response.equals("00")) {
            binding.txtResponse.text = "موفق"
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount?:"0") + "ریال"
        } else {
            binding.txtResponse.text = SinaResponse.getResponse(item.response?:"")
        }
    }

    private fun drawTopup(item: Transaction, binding: TransactionItemBinding) {
        binding.txtTitle.text = "شارژ مستقیم"
        binding.txtResponse.text = SinaResponse.getResponse(item.response?:"")
        binding.txtResponse.setTextColor(
            if (item.response.equals("00"))
                Color.parseColor("#2E7D32")
            else
                Color.parseColor("#f20606")
        )
        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ شارژ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount ?: "") + "ریال"
            binding.row2.visibility = View.VISIBLE
            binding.txtRow2.text = item.description
            binding.txtRow2Value.text = item.description2
        }
        //else {
//            binding.row1.visibility = View.VISIBLE
//            binding.txtRow1.setText(item.getDescription().split("~").get(0))
//            binding.txtRow1Value.setText(item.getDescription().split("~").get(1))
//        }
    }

    private fun drawCharge(item: Transaction, binding: TransactionItemBinding) {

        binding.txtTitle.text = "خرید شارژ " + item.description

        binding.txtResponse.text = SinaResponse.getResponse(item.response?:"")

        binding.txtResponse.setTextColor(
            if (item.response.equals("00"))
                Color.parseColor("#2E7D32")
            else Color.parseColor("#f20606")
        )

        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ شارژ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount?:"") + "ریال"
            }
//            else {
//                binding.txtResponse.setText("تراکنش ناموفق")
//                binding.txtResponse.setTextColor(Color.parseColor("#f20606"))
//            }
        }

    private fun drawBill(item: Transaction, binding: TransactionItemBinding) {
        binding.txtTitle.text = "پرداخت قبض"
        binding.txtResponse.text = SinaResponse.getResponse(item.response?:"")
        binding.txtResponse.setTextColor(
            if (item.response.equals("00"))
                Color.parseColor("#2E7D32")
            else Color.parseColor("#f20606")
        )

        if (item.response.equals("00")) {
            binding.row1.visibility = View.VISIBLE
            binding.txtRow1.text = "مبلغ"
            binding.txtRow1Value.text = RialFormatter.format(item.amount ?: "") + "ریال"
            binding.row2.visibility = View.VISIBLE
            binding.txtRow2.text = "سازمان قبض"
            binding.txtRow2Value.text = item.description
            binding.row3.visibility = View.VISIBLE
            binding.txtRow3.text = "شناسه قبض"
            binding.txtRow3Value.text = item.description2
            binding.row4.visibility = View.VISIBLE
            binding.txtRow4.text = "شناسه پرداخت"
            binding.txtRow4Value.text = item.description3
//            if (item.getStatus().equals(TransactionStatus.StartSuccessPrint.name())
//                || item.getStatus().equals(TransactionStatus.ReceiptPrinted.name())
//                || item.getStatus().equals(TransactionStatus.AdviceSentTimeOut.name())
//                || item.getStatus().equals(TransactionStatus.AdviceSent.name())
//                || item.getStatus().equals(TransactionStatus.AdviceResDidNotUnpack.name())
//                || item.getStatus().equals(TransactionStatus.AdviceResReceived.name())
//                || item.getStatus().equals(TransactionStatus.AdviceResUnpacked.name())
//            ) {
//            } else {
//                binding.txtResponse.setText("تراکنش ناموفق")
//                binding.txtResponse.setTextColor(Color.parseColor("#f20606"))
//            }
        } else {
            binding.row2.visibility = View.VISIBLE
            binding.txtRow2.text = "سازمان قبض"
            binding.txtRow2Value.text = item.description
            binding.row3.visibility = View.VISIBLE
            binding.txtRow3.text = "شناسه قبض"
            binding.txtRow3Value.text = item.description2
            binding.row4.visibility = View.VISIBLE
            binding.txtRow4.text = "شناسه پرداخت"
            binding.txtRow4Value.text = item.description3
        }
    }

}

