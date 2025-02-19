package com.fanap.corepos.receipt.sina

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.fanap.corepos.R
import com.fanap.corepos.databinding.SinaFoundReceiptBinding
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.sina.SinaUtils
import java.util.HashMap

class SinaFundReceipt {

    lateinit var binding : SinaFoundReceiptBinding

    fun generateReceipt(
        context: Context,
        data: HashMap<String, String>,
        headerData: HashMap<IsoFields, String>
    ) : Bitmap?{

       // if (data.isEmpty()) return null

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.sina_found_receipt, null, false)
        val view = binding.root

        binding.txtMerchantName.text = headerData[IsoFields.MerchantName] ?: ""
        binding.txtMerchantPhone.text = headerData[IsoFields.MerchantPhone] ?: ""
        binding.txtMerchant.text = headerData[IsoFields.Merchant] ?: ""
        binding.txtTerminal.text = headerData[IsoFields.Terminal] ?: ""
        binding.txtDate.text = SinaUtils.getPersianDate()
        binding.txtTime.text = SinaUtils.getReceiptTime()
        binding.txtStartDate.text = data["startDate"]
        binding.txtStartTime.text = data["startTime"]
        binding.txtEndDate.text = data["endDate"]
        binding.txtEndTime.text = data["endTime"]


        binding.table.txtSuccessBuy.text = data["successBuy"]
        binding.table.txtSuccessBill.text = data["successBill"]
        binding.table.txtSuccessCharge.text = data["successCharge"]
        binding.table.txtSuccessTopup.text = data["successTopup"]

        binding.table.txtUnSuccessBuy.text = data["failBuy"]
        binding.table.txtUnSuccessBill.text = data["failBill"]
        binding.table.txtUnSuccessCharge.text = data["failCharge"]
        binding.table.txtUnSuccessTopup.text = data["failTopup"]

        binding.table.txtSumBuy.text = "-/"+data["sumBuy"]
        binding.table.txtSumBill.text = "-/"+data["sumBill"]
        binding.table.txtSumCharge.text = "-/"+data["sumCharge"]
        binding.table.txtSumTopup.text = "-/"+data["sumTopup"]
        
        return getBitmapFromViewRotate(view)
    }

    private fun getBitmapFromViewRotate(view: View): Bitmap? {
        view.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        val matrix = Matrix()
        matrix.postRotate(-90f)
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, view.getMeasuredWidth(), view.getMeasuredHeight(), true);

        //sizeFile(rotatedBitmap);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }


}