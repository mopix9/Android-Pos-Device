package com.masa.aryan.settings.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentShiftBinding

class ShiftFragment :BaseFragment<FragmentShiftBinding>(){
 override fun getViewBinding(
  inflater: LayoutInflater,
  container: ViewGroup?
 ) =  FragmentShiftBinding.inflate(inflater,container,false)


 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)




 }
}