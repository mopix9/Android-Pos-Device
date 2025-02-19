package com.masa.aryan.settings.shop.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentSearchReceiptBinding
import com.masa.aryan.settings.shop.EDIT_PREF_TEXT


class SearchReceiptFragment : BaseFragment<FragmentSearchReceiptBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchReceiptBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchByRrn.setOnClickListener {
            navigate(
             this,
             R.id.action_searchReceiptFragment_to_searchTransactionFragment,
             bundleOf("TYPE" to 1)
            )
        }

        binding.searchByStan.setOnClickListener {
            navigate(
             this,
             R.id.action_searchReceiptFragment_to_searchTransactionFragment,
             bundleOf("TYPE" to 0)
            )

        }

        binding.back.setOnClickListener {
            finish(this)
        }

        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })

    }


}