package com.fanap.sina.bill

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.fanap.corepos.utils.Utils
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentBillBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class BillFragment : BaseFragment<FragmentBillBinding>() {

    private val viewModel : BillViewModel by viewModels()
    private lateinit var track2 : String
    private val SCANNER_REQUEST = "100"

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBillBinding {
        return FragmentBillBinding.inflate(inflater,container,false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        startTimer()

        binding.edtBillId.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtBillId.onCreateInputConnection(EditorInfo()))
            false
        }

        binding.edtPayId.setOnTouchListener { _, _ ->
            binding.keyboard.setInputConnection(binding.edtPayId.onCreateInputConnection(EditorInfo()))
            false
        }

        track2 = arguments?.getString("Track2","") ?: ""

        onTimerFinish.observe(viewLifecycleOwner,{
            navigate(this,R.id.action_billFragment_to_swipeFragment)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            navigate(this,R.id.action_billFragment_to_swipeFragment)
        })
        onTouchListener.observe(viewLifecycleOwner,{ startTimer() })
        binding.edtBillId.addTextChangedListener { startTimer() }
        binding.edtPayId.addTextChangedListener { startTimer() }

        binding.back.setOnClickListener { navigate(this,R.id.action_billFragment_to_swipeFragment) }
        binding.cancel.setOnClickListener { navigate(this,R.id.action_billFragment_to_swipeFragment) }
        binding.scanner.setOnClickListener {
            navigate(this,R.id.action_billFragment_to_scannerFragment)
        }
        viewModel.onConfirmClicked.observe(viewLifecycleOwner,{
            navigate(this,R.id.action_billFragment_to_billDetailFragment, bundleOf("BillId" to viewModel.billId.get(),
            "PayId" to viewModel.payId.get(), "Amount" to viewModel.amount.get(), "Track2" to track2))
        })

        viewModel.onError.observe(viewLifecycleOwner,{
            Utils.makeSnack(binding.root,it, Snackbar.LENGTH_LONG).show()
        })


        setFragmentResultListener(SCANNER_REQUEST) { _, bundle ->
            val result = bundle.getString("data")
            try {
                binding.edtBillId.setText(result!!.substring(0, 13))
                binding.edtPayId.setText(result.substring(13, 26))
            } catch (e: Exception) {
                Utils.makeSnack(binding.root, "قبض نامعتبر!", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}