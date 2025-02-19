package com.masa.aryan.settings.shop.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentReportsBinding
import com.masa.aryan.databinding.FragmentShopBinding


class ReportsFragment : BaseFragment<FragmentReportsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReportsBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.total.setOnClickListener {
            navigate(this,R.id.action_reportsFragment_to_totalReportFragment)
        }

        binding.reportDetails.setOnClickListener {
            navigate(this,R.id.action_reportsFragment_to_transactionDetailsFragment)
        }

        binding.printReceipt.setOnClickListener {
            navigate(this,R.id.action_reportsFragment_to_printReceiptFragment)
        }

        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })
    }

}