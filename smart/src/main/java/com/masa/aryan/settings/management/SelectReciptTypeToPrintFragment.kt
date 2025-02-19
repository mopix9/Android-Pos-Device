package com.masa.aryan.settings.management
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentSelectReciptTypeToPrintBinding
import com.masa.aryan.databinding.FragmentShopBinding

class SelectReciptTypeToPrintFragment : BaseFragment<FragmentSelectReciptTypeToPrintBinding>() {

 override fun getViewBinding(
  inflater: LayoutInflater,
  container: ViewGroup?
 ) = FragmentSelectReciptTypeToPrintBinding.inflate(inflater, container, false)

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  binding.reciptPrintSetting.setOnClickListener {
   navigate(this, R.id.action_selectReciptTypeToPrintFragment_to_reciptPrintSettingFragment)
  }

  binding.firstSecondRecipt.setOnClickListener {
   navigate(this, R.id.action_selectReciptTypeToPrintFragment_to_firstReciptAndSecondReciptFragment)
  }

  binding.back.setOnClickListener {

   finish(this@SelectReciptTypeToPrintFragment)
  }

  onBackPressed.observe(viewLifecycleOwner) {
   finish(this@SelectReciptTypeToPrintFragment)
  }
 }
}


