package com.fanap.corepos.receipt.aryan

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.fanap.corepos.R
import com.fanap.corepos.databinding.AryanTransactionReceiptBinding
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.Receipt
import com.fanap.corepos.receipt.enum.ChargeOrganization
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils
import com.fanap.corepos.utils.aryan.AryanResponse
import com.fanap.corepos.utils.aryan.AryanUtils

class AryanTransactionReceipt (private val context: Context, private val data: HashMap<IsoFields, String>) :
    Receipt() {
    private lateinit var binding: AryanTransactionReceiptBinding

    override fun generate(): Bitmap {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aryan_transaction_receipt, null, false)
        val view = binding.getRoot()

        drawGeneralPartOfReceipt()

        when(data[IsoFields.Type]){
            TransactionType.Buy.name -> drawBuy()
            TransactionType.Bill.name -> drawBill()
            TransactionType.Balance.name -> drawBalance()
            TransactionType.Topup.name -> drawTopup()
            TransactionType.Voucher.name -> drawVoucher()
        }

        return view.convertViewToBitmap()
    }

    private fun drawGeneralPartOfReceipt(){
        binding.header.txtTitle.text = data[IsoFields.TypeName]  ?: ""
        binding.header.txtMerchant.text = data[IsoFields.MerchantName]  ?: ""
        binding.header.txtMerchantPhone.text =  "تلفن"+data[IsoFields.MerchantPhone]
        binding.header.txtDateTime.text = data[IsoFields.TransactionDate] +"-"+ data[IsoFields.TransactionTime]

        binding.header.txtMerchantTerminal.text = data[IsoFields.Merchant]?.trim()  +"/"+ data[IsoFields.Terminal]?.trim()
        binding.header.txtBankName.text = Utils.getBankName(data[IsoFields.Track2]?: "000000")
        binding.header.txtCard.text = data[IsoFields.Track2]?: "000000"

        if(data[IsoFields.Rrn].isNullOrBlank()){
            binding.header.txtRrnStan.text = data[IsoFields.Stan]
        }else{
            binding.header.txtRrnStanTitle.text = "مرجع/پیگیری"
            binding.header.txtRrnStan.text = data[IsoFields.Rrn] +"/"+ data[IsoFields.Stan]
        }



    }

    private fun drawBuy(){
        binding.buy.visibility = View.VISIBLE

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.buy.findViewById<TextView>(R.id.txt_result).text = "عملیات موفق"
                binding.buy.findViewById<TextView>(R.id.txt_amount_or_error).text = RialFormatter.format(data[IsoFields.Amount].toString()) + " ریال"

            }
            TransactionReceiptStatus.Merchant.name->{
                binding.buy.findViewById<TextView>(R.id.txt_result).text = "عملیات موفق"
                binding.buy.findViewById<TextView>(R.id.txt_amount_or_error).text = RialFormatter.format(data[IsoFields.Amount].toString()) + " ریال"
                binding.buy.findViewById<RelativeLayout>(R.id.rtl_seller_receipt).visibility = View.VISIBLE
            }
            TransactionReceiptStatus.Fail.name->{
                binding.buy.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق " + data[IsoFields.Response]
                binding.buy.findViewById<TextView>(R.id.txt_amount_or_error).text = AryanResponse.getResponse(data[IsoFields.Response] ?: "")
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.buy.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق"
                binding.buy.findViewById<LinearLayout>(R.id.failMessageLayout).visibility = View.VISIBLE
            }
        }
    }

    private fun drawBill(){
        binding.billLayout.visibility = View.VISIBLE
        binding.billLayout.findViewById<TextView>(R.id.txt_bill_id).text = data[IsoFields.BillId]  ?: ""
        binding.billLayout.findViewById<TextView>(R.id.txt_pay_id).text = data[IsoFields.PayId]  ?: ""
        binding.billLayout.findViewById<TextView>(R.id.txt_bill_name).text = data[IsoFields.Buffer1]  ?: ""

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.billLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات موفق"
                binding.billLayout.findViewById<TextView>(R.id.txt_amount_or_error).text = RialFormatter.format(data[IsoFields.Amount].toString())+ " ریال"
            }
            TransactionReceiptStatus.Fail.name->{
                binding.billLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق ${data[IsoFields.Response]  ?: ""}"
                binding.billLayout.findViewById<TextView>(R.id.txt_amount_or_error).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.billLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق"
                binding.billLayout.findViewById<LinearLayout>(R.id.failMessageLayout).visibility = View.VISIBLE
            }
        }
    }

    private fun drawBalance(){
        binding.balanceLayout.visibility = View.VISIBLE

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.balanceLayout.findViewById<TextView>(R.id.txt_balance).text = RialFormatter.format(data[IsoFields.Balance].toString())+ " ریال"
            }
        }
    }

    private fun drawTopup(){
        binding.topupLayout.visibility = View.VISIBLE
        binding.topupLayout.findViewById<TextView>(R.id.txt_phone).text = data[IsoFields.PhoneNumber]  ?: ""

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.topupLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات موفق"
                binding.topupLayout.findViewById<TextView>(R.id.txt_amount_or_error).text = RialFormatter.format(data[IsoFields.Amount].toString())+ " ریال"
            }
            TransactionReceiptStatus.Fail.name->{
                binding.topupLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق ${data[IsoFields.Response]  ?: ""}"
                binding.topupLayout.findViewById<TextView>(R.id.txt_amount_or_error).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.topupLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق"
                binding.topupLayout.findViewById<LinearLayout>(R.id.failMessageLayout).visibility = View.VISIBLE
            }
        }
    }

    private fun drawVoucher(){
        binding.voucherLayout.visibility = View.VISIBLE

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{

                binding.voucherLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات موفق"
                binding.voucherLayout.findViewById<TextView>(R.id.txt_amount_or_error).text = RialFormatter.format(data[IsoFields.Amount].toString())+ " ریال"

                binding.voucherLayout.findViewById<TextView>(R.id.voucherCode).text = data[IsoFields.ChargePin]  ?: ""
                binding.voucherLayout.findViewById<TextView>(R.id.voucherSerial).text = data[IsoFields.ChargeSerial]  ?: ""

                when(data[IsoFields.ChargeOrganization]){
                    ChargeOrganization.IRANCELL.chargeName -> binding.voucherLayout.findViewById<RelativeLayout>(
                        R.id.guide_irancel).visibility = View.VISIBLE
                    ChargeOrganization.HAMRAH_AVAL.chargeName -> binding.voucherLayout.findViewById<RelativeLayout>(
                        R.id.guide_hamrah_aval).visibility = View.VISIBLE
                    ChargeOrganization.RIGHTEL.chargeName -> binding.voucherLayout.findViewById<RelativeLayout>(
                        R.id.guide_rightel).visibility = View.VISIBLE
                }
            }
            TransactionReceiptStatus.Fail.name->{
                binding.voucherLayout.findViewById<LinearLayout>(R.id.lnr_success).visibility = View.GONE
                binding.voucherLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق ${data[IsoFields.Response]  ?: ""}"
                binding.voucherLayout.findViewById<TextView>(R.id.txt_amount_or_error).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.voucherLayout.findViewById<LinearLayout>(R.id.lnr_success).visibility = View.GONE
                binding.voucherLayout.findViewById<TextView>(R.id.txt_result).text = "عملیات ناموفق"
                binding.voucherLayout.findViewById<LinearLayout>(R.id.failMessageLayout).visibility = View.VISIBLE
            }
        }
    }


}