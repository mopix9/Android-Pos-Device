package com.example.tian.settings.shop.viewmodel

import android.app.Application
import com.example.tian.base.BaseViewModel
import com.fanap.corepos.database.service.model.TransactionStatus
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.sayan.SayanUtils
import com.wang.avi.BuildConfig


class RollViewModel(application: Application) : BaseViewModel(application) {

    fun makeTransaction() =
        HashMap<IsoFields, String>().apply {
            put(IsoFields.Mti,"0100")
            put(IsoFields.ProcessCode, "590000")
            put(IsoFields.Stan, stanList[1].toString())
            put(IsoFields.TransactionTime, SayanUtils.getTime())
            put(IsoFields.TransactionDate, SayanUtils.getDate())
            put(IsoFields.NiiCode, DependencyManager.nii)
            put(IsoFields.Terminal, terminal)
            put(IsoFields.Merchant, merchant)
            put(IsoFields.Serial, serial)
            put(IsoFields.SoftwareVersion, BuildConfig.VERSION_CODE.toString())
            put(IsoFields.TerminalLanguageCode, "0")
            put(IsoFields.RequestType, "R")
            put(IsoFields.ConnectionType, "4")
            put(IsoFields.Status, TransactionStatus.TransactionSent.name)
        }


}