package com.fanap.sina.balance

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.ReceiptFactory
import com.fanap.sina.R
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentBalanceBinding
import com.fanap.sina.main.fragments.LoadingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class BalanceFragment : BaseFragment<FragmentBalanceBinding>() {

    @Inject
    lateinit var appContext: Context
    private val viewModel : BalanceViewModel by viewModels()
    @Inject
    lateinit var loading: LoadingFragment
    private val transactionManager: IIso by lazy { DependencyManager.provideIsoTransaction() }

    private val hsm: HSMInterface by lazy { DeviceSDKManager.getHSMInterface(appContext)!! }
    private lateinit var track2 : String

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBalanceBinding =
        FragmentBalanceBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        track2 = arguments?.getString("Track2","") ?: ""
        startTimer(30_000)

        onTimerFinish.observe(viewLifecycleOwner,{
            navigate(this,R.id.action_balanceFragment_to_swipeFragment)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            navigate(this,R.id.action_balanceFragment_to_swipeFragment)
        })

        binding.back.setOnClickListener { navigate(this,R.id.action_balanceFragment_to_swipeFragment) }
        binding.cancel.setOnClickListener { navigate(this,R.id.action_balanceFragment_to_swipeFragment) }

        showPinPad()
    }


    private fun showPinPad(){
        hsm.inputPin(track2,appContext)
        hsm.mutablePassword.observe(viewLifecycleOwner,{
            if (!it.equals("")) {
                doTransaction(it)
            } else {
                navigate(this,R.id.action_balanceFragment_to_swipeFragment)
            }
        })
    }

    private fun doTransaction(pinBlock: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                stopTimer()
                loading.show(childFragmentManager, "")
                loading.isCancelable = false
            }

            val map = viewModel.makeTransaction(track2,pinBlock)
            val transaction = viewModel.insertTransaction(map)
            val result = transactionManager.doTransaction(map)

            withContext(Dispatchers.Main) {
                if (result != null) {
                    val responseCode = result[IsoFields.Response] ?: ""
                    if (responseCode == "00") {
                        transaction.response = responseCode
                        transaction.status = TransactionStatus.StartSuccessPrint.name
                        transaction.rrn = result[IsoFields.Rrn] ?: ""
                        viewModel.updateTransaction(transaction)
                        navigate(this@BalanceFragment, R.id.action_balanceFragment_to_balanceSuccessFragment,
                            bundleOf("Result" to result, "Track2" to track2))
                    }else{
                        transaction.response = responseCode
                        transaction.status = TransactionStatus.TransactionResUnpackedResponseError.name
                        viewModel.updateTransaction(transaction)
                        navigate(this@BalanceFragment, R.id.action_balanceFragment_to_buyFailFragment,
                            bundleOf("Result" to "خطا: $responseCode")
                        )
                    }
                } else {
                    transaction.response = "-1"
                    transaction.status = TransactionStatus.TransactionSentTimeOut.name
                    viewModel.updateTransaction(transaction)
                    navigate(this@BalanceFragment, R.id.action_balanceFragment_to_buyFailFragment,
                        bundleOf("Result" to "عدم دریافت پاسخ تراکنش"))
                }
            }
        }
    }

}