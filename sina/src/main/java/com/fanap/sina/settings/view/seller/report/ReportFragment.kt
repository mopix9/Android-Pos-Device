package com.fanap.sina.settings.view.seller.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentReportBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class ReportFragment : BaseFragment<FragmentReportBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding = FragmentReportBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
        onTimerFinish.observe(viewLifecycleOwner,{
            binding.back.callOnClick()
        })
        onBackPressed.observe(viewLifecycleOwner,{
            binding.back.callOnClick()
        })
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })
        binding.back.setOnClickListener { navigate(this,R.id.action_reportFragment_to_sellerFragment) }

        binding.fund.setOnClickListener {
            navigate(this,R.id.action_reportFragment_to_fundFragment)
        }

        binding.dailyReport.setOnClickListener {
            navigate(this,R.id.action_reportFragment_to_dailyReportFragment)
        }

        binding.reportByDate.setOnClickListener {
            navigate(this,R.id.action_reportFragment_to_reportByDateFragment)
        }

        binding.successTransactions.setOnClickListener {
            navigate(this,R.id.action_reportFragment_to_successfulTransactionsFragment)
        }

        binding.singleReport.setOnClickListener {
            navigate(this,R.id.action_reportFragment_to_singleReportFragment)
        }
    }
}