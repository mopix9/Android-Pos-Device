package com.masa.aryan.settings.shop.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.fanap.corepos.receipt.enum.TransactionType
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentReportsBinding
import com.masa.aryan.databinding.FragmentTransactionDetailsBinding

class TransactionDetailsFragment : BaseFragment<FragmentTransactionDetailsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTransactionDetailsBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.all.setOnClickListener {
            navigate(this,R.id.action_transactionDetailsFragment_to_transactionListFragment,
                bundleOf("TYPE" to TransactionType.Total))
        }

        binding.buy.setOnClickListener {
            navigate(this,R.id.action_transactionDetailsFragment_to_transactionListFragment,
                bundleOf("TYPE" to TransactionType.Buy))
        }

        binding.bill.setOnClickListener {
            navigate(this,R.id.action_transactionDetailsFragment_to_transactionListFragment,
                bundleOf("TYPE" to TransactionType.Bill))
        }

        binding.topup.setOnClickListener {
            navigate(this,R.id.action_transactionDetailsFragment_to_transactionListFragment,
                bundleOf("TYPE" to TransactionType.Topup))
        }

        binding.voucher.setOnClickListener {
            navigate(this,R.id.action_transactionDetailsFragment_to_transactionListFragment,
                bundleOf("TYPE" to TransactionType.Voucher))
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
    }

}