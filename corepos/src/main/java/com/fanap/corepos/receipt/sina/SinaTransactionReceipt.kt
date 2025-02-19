package com.fanap.corepos.receipt.sina

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.fanap.corepos.R
import com.fanap.corepos.databinding.SinaReceiptTransactionBinding
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.ChargeOrganization
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.IsoUtil
import com.fanap.corepos.utils.RialFormatter
import com.fanap.corepos.utils.Utils

class SinaTransactionReceipt(private val context: Context,private val data: HashMap<IsoFields, String>) {
    private lateinit var binding: SinaReceiptTransactionBinding

    fun generate(): View{
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.sina_receipt_transaction, null, false)
        val view = binding.getRoot()

        drawGeneralPartOfReceipt()

        when(data[IsoFields.Type]){
            TransactionType.Buy.name -> drawBuy()
            TransactionType.Bill.name -> drawBill()
            TransactionType.Balance.name -> drawBalance()
            TransactionType.Topup.name -> drawTopup()
            TransactionType.Voucher.name -> drawVoucher()
        }

        return view
    }

    private fun drawGeneralPartOfReceipt(){
        binding.txtTitle.text = data[IsoFields.MerchantName]  ?: ""
        binding.txtPhone.text = data[IsoFields.MerchantPhone]  ?: ""
        binding.txtTransactionType.text = data[IsoFields.TypeName]  ?: ""
        binding.txtTerminalAndCardNumber.text = IsoUtil.cardMaskForReceipt(data[IsoFields.Track2]) +"-"+ data[IsoFields.Terminal]
        binding.txtTime.text = data[IsoFields.TransactionTime] ?: ""
        binding.txtDate.text = data[IsoFields.TransactionDate] ?: ""
        binding.txtReferenceAndTrackingNumber.text = data[IsoFields.Rrn] +"-"+Utils.removeZeros(data[IsoFields.Stan]?:"")

        if (data[IsoFields.Status] == TransactionReceiptStatus.UnReceivedResponse.name ||
            data[IsoFields.Status] == TransactionReceiptStatus.Fail.name){
            binding.txtReferenceAndTrackingNumber.text = Utils.removeZeros(data[IsoFields.Stan]?:"")
            binding.txtReferenceAndTrackingNumberLabel.text = "شماره پیگیری"
        }

        if(data[IsoFields.IsAgainReceipt]?.toBoolean() == true)
            binding.txtAgainReceipt.visibility = View.VISIBLE

        if(data[IsoFields.IsShiftEnabled]?.toBoolean() == true){
            binding.shiftLayout.visibility = View.VISIBLE
            binding.txtShiftNumber.text = data[IsoFields.ShiftNumber] ?: ""
        }
    }

    private fun drawBuy(){
        binding.buyLayout.visibility = View.VISIBLE
        binding.line1.visibility = View.GONE

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.buyLayout.findViewById<TextView>(R.id.txtAmount).text = RialFormatter.format(data[IsoFields.Amount].toString()) + " ریال"
            }
            TransactionReceiptStatus.Fail.name->{
                binding.buyLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.buyLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.buyLayout.findViewById<TextView>(R.id.txtResponseCode).text = data[IsoFields.Response]  ?: ""
                binding.buyLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.buyLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.buyLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.buyLayout.findViewById<RelativeLayout>(R.id.responseCodeLayout).visibility = View.GONE
                binding.buyLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
                binding.failMessageDetailsLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun drawBill(){
        binding.billLayout.visibility = View.VISIBLE
        binding.billLayout.findViewById<TextView>(R.id.billId).text = data[IsoFields.BillId]  ?: ""
        binding.billLayout.findViewById<TextView>(R.id.paymentId).text = data[IsoFields.PayId]  ?: ""

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.billLayout.findViewById<TextView>(R.id.txtAmount).text = RialFormatter.format(data[IsoFields.Amount].toString())+ " ریال"
            }
            TransactionReceiptStatus.Fail.name->{
                binding.billLayout.findViewById<TextView>(R.id.txtTransactionResult).text = "عملیات ناموفق"
                binding.billLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.billLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.billLayout.findViewById<TextView>(R.id.txtResponseCode).text = data[IsoFields.Response]  ?: ""
                binding.billLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.billLayout.findViewById<TextView>(R.id.txtTransactionResult).text = "عملیات ناموفق"
                binding.billLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.billLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.billLayout.findViewById<RelativeLayout>(R.id.responseCodeLayout).visibility = View.GONE
                binding.billLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
                binding.failMessageDetailsLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun drawBalance(){
        binding.balanceLayout.visibility = View.VISIBLE

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.balanceLayout.findViewById<TextView>(R.id.txtAmount).text = RialFormatter.format(data[IsoFields.Balance].toString())+ " ریال"
            }
        }
    }

    private fun drawTopup(){
        binding.topupLayout.visibility = View.VISIBLE

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.topupLayout.findViewById<TextView>(R.id.txtAmount).text = RialFormatter.format(data[IsoFields.Amount].toString())+ " ریال"
                binding.topupLayout.findViewById<TextView>(R.id.topupPhoneNumber).text = data[IsoFields.PhoneNumber]  ?: ""
                binding.topupLayout.findViewById<TextView>(R.id.topupName).text = data[IsoFields.ChargeOrganization]  ?: ""
            }
            TransactionReceiptStatus.Fail.name->{
                binding.topupLayout.findViewById<TextView>(R.id.txtTransactionResult).text = "عملیات ناموفق"
                binding.topupLayout.findViewById<RelativeLayout>(R.id.topupPhoneNumberLayout).visibility = View.GONE
                binding.topupLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.topupLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.topupLayout.findViewById<TextView>(R.id.txtResponseCode).text = data[IsoFields.Response]  ?: ""
                binding.topupLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.topupLayout.findViewById<TextView>(R.id.txtTransactionResult).text = "عملیات ناموفق"
                binding.topupLayout.findViewById<RelativeLayout>(R.id.topupPhoneNumberLayout).visibility = View.GONE
                binding.topupLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.topupLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.topupLayout.findViewById<RelativeLayout>(R.id.responseCodeLayout).visibility = View.GONE
                binding.topupLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
                binding.failMessageDetailsLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun drawVoucher(){
        binding.voucherLayout.visibility = View.VISIBLE
        binding.voucherLayout.findViewById<TextView>(R.id.voucherCode).text = data[IsoFields.ChargePin]  ?: ""
        binding.voucherLayout.findViewById<TextView>(R.id.voucherSerial).text = data[IsoFields.ChargeSerial]  ?: ""

        when(data[IsoFields.Status]){
            TransactionReceiptStatus.Success.name->{
                binding.voucherLayout.findViewById<TextView>(R.id.txtAmount).text = RialFormatter.format(data[IsoFields.Amount].toString())+ " ریال"
                when(data[IsoFields.ChargeOrganization]){
                    ChargeOrganization.IRANCELL.chargeName -> binding.voucherLayout.findViewById<RelativeLayout>(R.id.guide_irancel).visibility = View.VISIBLE
                    ChargeOrganization.HAMRAH_AVAL.chargeName -> binding.voucherLayout.findViewById<RelativeLayout>(R.id.guide_hamrah_aval).visibility = View.VISIBLE
                    ChargeOrganization.RIGHTEL.chargeName -> binding.voucherLayout.findViewById<RelativeLayout>(R.id.guide_rightel).visibility = View.VISIBLE
                }
            }
            TransactionReceiptStatus.Fail.name->{
                binding.voucherLayout.findViewById<TextView>(R.id.txtTransactionResult).text = "عملیات ناموفق"
                binding.voucherLayout.findViewById<RelativeLayout>(R.id.voucherCodeLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<RelativeLayout>(R.id.voucherSerialLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<LinearLayout>(R.id.voucherGuideLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.voucherLayout.findViewById<TextView>(R.id.txtResponseCode).text = data[IsoFields.Response]  ?: ""
                binding.voucherLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
            }
            TransactionReceiptStatus.UnReceivedResponse.name->{
                binding.voucherLayout.findViewById<TextView>(R.id.txtTransactionResult).text = "عملیات ناموفق"
                binding.voucherLayout.findViewById<RelativeLayout>(R.id.voucherCodeLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<RelativeLayout>(R.id.voucherSerialLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<LinearLayout>(R.id.voucherGuideLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<LinearLayout>(R.id.amountLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<LinearLayout>(R.id.failLayout).visibility = View.VISIBLE
                binding.voucherLayout.findViewById<RelativeLayout>(R.id.responseCodeLayout).visibility = View.GONE
                binding.voucherLayout.findViewById<TextView>(R.id.txtMessage).text = data[IsoFields.FailMessage]  ?: ""
                binding.failMessageDetailsLayout.visibility = View.VISIBLE
            }
        }
    }


}