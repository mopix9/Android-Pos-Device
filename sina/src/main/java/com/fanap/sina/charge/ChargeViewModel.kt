package com.fanap.sina.charge

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.receipt.enum.ChargeOrganization
import com.fanap.corepos.receipt.enum.TransactionReceiptStatus
import com.fanap.corepos.receipt.enum.TransactionType
import com.fanap.corepos.utils.Operator
import com.fanap.corepos.utils.Utils
import com.fanap.sina.base.BaseViewModel
import com.fanap.corepos.utils.sina.SinaUtils
import java.lang.Exception

class ChargeViewModel(application: Application) : BaseViewModel(application) {
    ////////////General////////////////
    var isTopUp: ObservableBoolean
    var codeTabClicked: MutableLiveData<Boolean>

    /////////////Top up/////////////////////////
    var amount: ObservableInt
    var isMci: ObservableBoolean
    var isMtn: ObservableBoolean
    var isRightel: ObservableBoolean
    var phone: ObservableField<String>

    /////////////Charge code////////////////////
    var isMciChargeCode: ObservableBoolean
    var isMtnChargeCode: ObservableBoolean
    var isRightelChargeCode: ObservableBoolean

    ////////////General////////////////
    fun onTabClicked(isTopUpClicked: Boolean) {
        isTopUp.set(isTopUpClicked)
        if (!isTopUpClicked) codeTabClicked.postValue(true)
    }

    ////////////////////////Charge Code//////////////////////////////////////
    fun toggleChargeCode(type: Int) {
        if (type == 1) {
            isMciChargeCode.set(!isMciChargeCode.get())
            isMtnChargeCode.set(false)
            isRightelChargeCode.set(false)
        } else if (type == 2) {
            isMciChargeCode.set(false)
            isMtnChargeCode.set(!isMtnChargeCode.get())
            isRightelChargeCode.set(false)
        } else {
            isMciChargeCode.set(false)
            isMtnChargeCode.set(false)
            isRightelChargeCode.set(!isRightelChargeCode.get())
        }
    }

    /////////////Top up/////////////////////////
    fun onEdittextChanged(phoneNumber: CharSequence) {
        try {
            if (phoneNumber.length >= 4) {
                val prefix = phoneNumber.toString().trim { it <= ' ' }
                    .substring(0, 4)
                val mciList = Utils.MCI_PREFIXES
                val mtnList = Utils.MTN_PREFIXES
                val rightelList = Utils.RIGHTEL_PREFIXES
                if (mciList.contains(prefix)) {
                    isMci.set(true)
                    isMtn.set(false)
                    isRightel.set(false)
                } else if (mtnList.contains(prefix)) {
                    isMci.set(false)
                    isMtn.set(true)
                    isRightel.set(false)
                } else if (rightelList.contains(prefix)) {
                    isMci.set(false)
                    isMtn.set(false)
                    isRightel.set(true)
                } else {
                    isMci.set(false)
                    isMtn.set(false)
                    isRightel.set(false)
                }
            } else {
                isMci.set(false)
                isMtn.set(false)
                isRightel.set(false)
            }

//            if (phoneNumber.length() >= 11){
//                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val isMciTopUp: Unit
        get() {
            isMci.set(!isMci.get())
            isMtn.set(false)
            isRightel.set(false)
        }
    val isMtnTopUp: Unit
        get() {
            isMtn.set(!isMtn.get())
            isMci.set(false)
            isRightel.set(false)
        }
    val isRightelTopUp: Unit
        get() {
            isRightel.set(!isRightel.get())
            isMci.set(false)
            isMtn.set(false)
        }
    val topupOperator: Operator?
        get() = if (isMci.get()) Operator.MCI else if (isMtn.get()) Operator.MTN else if (isRightel.get()) Operator.RIGHTEL else null
    val chargeCodeOperator: Operator?
        get() = if (isMciChargeCode.get()) Operator.MCI else if (isMtnChargeCode.get()) Operator.MTN else if (isRightelChargeCode.get()) Operator.RIGHTEL else null

    init {

        ////////////General////////////////
        isTopUp = ObservableBoolean(true)
        codeTabClicked = MutableLiveData()
        /////////////Top up/////////////////////////
        amount = ObservableInt(0)
        isMci = ObservableBoolean(false)
        isMtn = ObservableBoolean(false)
        isRightel = ObservableBoolean(false)
        phone = ObservableField("")

        ////////////////////////Charge Code////////////////////
        isMciChargeCode = ObservableBoolean(true)
        isMtnChargeCode = ObservableBoolean(false)
        isRightelChargeCode = ObservableBoolean(false)
    }

    fun makeTransaction(track2: String, pinBlock: String,amount : String) =
        HashMap<IsoFields, String>().apply {
            put(IsoFields.Mti,"0200")
            put(IsoFields.CardNumber,track2.split("=")[0])
            put(IsoFields.LastStan, stanList[0].toString())
            put(IsoFields.Stan, stanList[1].toString())
            put(IsoFields.TransactionTime, SinaUtils.getTime())
            put(IsoFields.TransactionDate, SinaUtils.getDate())
            put(IsoFields.CardExpireDate,track2.split("=")[1].take(4))
            put(IsoFields.Track2,track2)
            put(IsoFields.Serial, serial)
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Merchant, merchant)
            put(IsoFields.PinBlock,pinBlock)
            put(IsoFields.Amount, amount)
            put(IsoFields.Status, TransactionStatus.TransactionSent.name)

            if (isTopUp.get()){
                put(IsoFields.ProcessCode, "180000")
                put(IsoFields.PhoneNumber, phone.get()?: "")
                if (isMci.get())
                    put(IsoFields.ChargeOrganization,"9914")
                else if(isMtn.get())
                    put(IsoFields.ChargeOrganization,"9937")
                else if (isRightel.get())
                    put(IsoFields.ChargeOrganization,"9921")
            }else{
                put(IsoFields.ProcessCode, "190000")

                if (isMciChargeCode.get())
                    put(IsoFields.ChargeOrganization,"9912")
                else if(isMtnChargeCode.get())
                    put(IsoFields.ChargeOrganization,"9935")
                else if (isRightelChargeCode.get())
                    put(IsoFields.ChargeOrganization,"9920")
            }

        }

    fun makeReceipt(track2: String, data: HashMap<IsoFields, String>): HashMap<IsoFields, String> {
        val amount = Utils.removeZeros(data[IsoFields.Amount]?: "")
        if(isTopUp.get())
            data[IsoFields.Type] =  TransactionType.Topup.name
        else
            data[IsoFields.Type] =  TransactionType.Voucher.name

        data.apply {
            put(IsoFields.Amount,amount)
            put(IsoFields.MerchantName, name)
            put(IsoFields.MerchantPhone, merchantPhone)
            put(IsoFields.TransactionTime, StringBuilder(SinaUtils.getTime().take(4)).insert(2, ":").toString())
            put(IsoFields.TransactionDate, SinaUtils.getPersianDate())
            put(IsoFields.TypeName, "خرید شارژ")
            put(IsoFields.Status, TransactionReceiptStatus.Success.name)
            put(IsoFields.Track2,track2)
            put(IsoFields.ChargeOrganization,getChargeOrganization())
            put(IsoFields.PhoneNumber,phone.get()?:"")

        }
        return data
    }

    private fun getChargeOrganization(): String{
        if (isTopUp.get()){
            if (isMci.get())
                return ChargeOrganization.HAMRAH_AVAL.chargeName
            if (isMtn.get())
                return ChargeOrganization.IRANCELL.chargeName
            if (isRightel.get())
                return ChargeOrganization.RIGHTEL.chargeName
        }else{
            if (isMciChargeCode.get())
                return ChargeOrganization.HAMRAH_AVAL.chargeName
            if (isMtnChargeCode.get())
                return ChargeOrganization.IRANCELL.chargeName
            if (isRightelChargeCode.get())
                return ChargeOrganization.RIGHTEL.chargeName
        }

        return ""
    }

}
