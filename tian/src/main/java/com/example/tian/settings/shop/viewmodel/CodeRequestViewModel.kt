package com.example.tian.settings.shop.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tian.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeRequestViewModel(application: Application) : BaseViewModel(application) {

    val codeRequest = ObservableField("")

    val onError = MutableLiveData<String>()
    val onSuccess = MutableLiveData<Boolean>()

    fun setOnConfirmClicked() {

        if (codeRequest.get() != "") {


            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val req = userRepository.getValue(codeRequest.get()!!.toLong())!!
                    if (codeRequest.get()!!.toLong() == req.userId)
                        onSuccess.postValue(true)
                } catch (e: Exception) {
                    onError.postValue("کد صندوق دار صحیح نمی باشد")
                }

            }

        } else onError.postValue("لطفا کد صندوق دار را وارد نمائید!")

    }

}