package com.example.tian.balance

import android.content.Context
import android.graphics.Color.rgb
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tian.balance.viewmodel.BalanceViewModel
import com.example.tian.base.BaseFragment
import com.example.tian.main.view.LoadingFragment
import com.exmple.tian.R
import com.exmple.tian.databinding.FragmentBalanceBinding
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class BalanceFragment : BaseFragment<FragmentBalanceBinding>()  {

    @Inject
    lateinit var appContext: Context
    private val viewModel : BalanceViewModel by viewModels()
    @Inject
    lateinit var loading: LoadingFragment
    private val transactionManager: IIso by lazy { DependencyManager.provideIsoTransaction() }

    private val hsm: HSMInterface by lazy { DeviceSDKManager.getHSMInterface(appContext)!! }
    private lateinit var track2 : String

//    var presentationDisplay2 :Presentation = Presentation(context ,getDisplay2(context!!) )
//    var presentationDisplay2 :PresentationManager = PresentationManager()


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

        onTimerFinish.observe(viewLifecycleOwner) {
            navigate(
             this,
             R.id.action_balanceFragment_to_swipeFragment
            )
        }
        onBackPressed.observe(viewLifecycleOwner) {
            navigate(
             this,
             R.id.action_balanceFragment_to_swipeFragment
            )
        }

        binding.back.setOnClickListener { navigate(
         this,
         R.id.action_balanceFragment_to_swipeFragment
        ) }
        binding.cancel.setOnClickListener { navigate(
         this,
         R.id.action_balanceFragment_to_swipeFragment
        ) }


       /* val secondDisplay = AnotherDisplayPresentation(context!!)*/
        /*if (getDisplay2(context!!) != null) {
            presentationDisplay2.add()
        }else
            null*/
showPinPad()


    }


    private fun showPinPad(){

//        Toast karmozd

        val toast = Toast.makeText(
            appContext,
            "کارمزد 1206 ریال",
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.TOP, 0, 50)
        val toastLayout = toast.getView() as ViewGroup
        val toastTV = toastLayout.getChildAt(0) as TextView
        toastTV.textSize =30F
        toastTV.typeface = Typeface.createFromAsset(appContext.assets, "fonts/iransansbold.ttf")
        toastTV.setTextColor(rgb(255, 0, 0))
            toast.show()

        hsm.inputPin(track2,appContext)
        hsm.mutablePassword.observe(viewLifecycleOwner) {
            if (!it.equals("")) {
                Log.d("password",it)
                doTransaction(it)
            } else {
                navigate(
                 this,
                 R.id.action_balanceFragment_to_swipeFragment
                )
            }
        }
    }

    private fun doTransaction(pinBlock: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                stopTimer()
                loading.show(childFragmentManager, "")
                loading.isCancelable = false
            }

            val map = viewModel.makeTransaction(track2,pinBlock)
            Log.d("map",map.toString())
            val transaction = viewModel.insertTransaction(map)
            val result = transactionManager.doTransaction(map)
            Log.d("resultMap" , result.toString())

            withContext(Dispatchers.Main) {
                if (result != null) {
                    val responseCode = result[IsoFields.Response] ?: ""
                    if (responseCode == "00") {
                        transaction.response = responseCode
                        transaction.status = TransactionStatus.StartSuccessPrint.name
                        transaction.rrn = result[IsoFields.Rrn] ?: ""
                        transaction.description = result[IsoFields.IdentificationCode] ?: ""
                        viewModel.updateTransaction(transaction)
                        navigate(
                         this@BalanceFragment,
                         R.id.action_balanceFragment_to_balanceSuccessFragment,
                         bundleOf("Result" to result, "Track2" to track2)
                        )
                    }else{
                        transaction.response = responseCode
                        transaction.status = TransactionStatus.TransactionResUnpackedResponseError.name
                        viewModel.updateTransaction(transaction)
                        navigate(
                         this@BalanceFragment, R.id.action_balanceFragment_to_failFragment,
                         bundleOf("Result" to "خطا: $responseCode")
                        )
                    }
                } else {
                    transaction.response = "-1"
                    transaction.status = TransactionStatus.TransactionSentTimeOut.name
                    viewModel.updateTransaction(transaction)
                    navigate(
                     this@BalanceFragment, R.id.action_balanceFragment_to_failFragment,
                     bundleOf("Result" to "عدم دریافت پاسخ تراکنش")
                    )
                }
            }
        }
    }

   /* fun getDisplay2(context: Context): Display? {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val displays = displayManager.displays
        return if (displays.size > 1) {
            displays[1]
        } else {
            null
        }
    }*/

}

/*
fun getDisplay2(context: Context): Display? {
    val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    val displays = displayManager.displays
    return if (displays.size > 1) {
        displays[1]
    } else {
        null
    }
}*/
