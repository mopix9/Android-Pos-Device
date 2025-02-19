package com.fanap.sina.settings.view.seller.report.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanap.sina.base.BaseViewModel

class SuccessfulTransactionsViewModel(application: Application) : BaseViewModel(application) {
    var selectedTransaction: ObservableField<String> = ObservableField("000000")
    var refresh: MutableLiveData<Boolean> = MutableLiveData()

    fun setSelectedTransaction(value: String) {
        selectedTransaction.set(value)
        refresh.postValue(true)
    }

}
