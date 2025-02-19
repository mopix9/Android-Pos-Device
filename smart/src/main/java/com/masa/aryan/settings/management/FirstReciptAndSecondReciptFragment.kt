package com.masa.aryan.settings.management

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanap.corepos.utils.Utils
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentFirstReciptAndSecondReciptBinding
import com.masa.aryan.databinding.FragmentSelectReciptTypeToPrintBinding
import com.masa.aryan.databinding.FragmentShopBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Boolean.getBoolean
import java.lang.reflect.Array.setBoolean
import javax.inject.Inject


const val RECIPT_PREF_KEY1 = " com.masa.aryan.switch1"
const val RECIPT_PREF_KEY2 = " com.masa.aryan.switch2"

@AndroidEntryPoint
class FirstReciptAndSecondReciptFragment :
 BaseFragment<FragmentFirstReciptAndSecondReciptBinding>() {

 @Inject
 lateinit var sharedpref: SharedPreferences


 override fun getViewBinding(
  inflater: LayoutInflater,
  container: ViewGroup?
 ) = FragmentFirstReciptAndSecondReciptBinding.inflate(inflater, container, false)

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  binding.switch1.isChecked = sharedpref.getBoolean(RECIPT_PREF_KEY1, true)
  binding.switch2.isChecked = sharedpref.getBoolean(RECIPT_PREF_KEY2, true)

/*  binding.switch1.setOnCheckedChangeListener { _, isChecked ->
   sharedpref.edit().apply {
    putBoolean(RECIPT_PREF_KEY1, isChecked)
   }.apply()
  }

  binding.switch2.setOnCheckedChangeListener { _, isChecked ->
   sharedpref.edit().apply {
    putBoolean(RECIPT_PREF_KEY2, isChecked)
   }.apply()
  }*/

  binding.sendamount.setOnClickListener {
   sharedpref.edit().apply {
    putBoolean(RECIPT_PREF_KEY1, binding.switch1.isChecked)
    putBoolean(RECIPT_PREF_KEY2, binding.switch2.isChecked)
   }.apply()

   Utils.makeSnack(binding.root, "تنظیمات با موفقیت ذخیره شد", 900).show()
   finish(this@FirstReciptAndSecondReciptFragment)
  }


  onBackPressed.observe(viewLifecycleOwner) {
   navigate(this, R.id.action_shopFragment_to_settingsFragment)
  }

  binding.exit.setOnClickListener {
   finish(this@FirstReciptAndSecondReciptFragment)
  }

  binding.back.setOnClickListener {
   finish(this@FirstReciptAndSecondReciptFragment)
  }

  onBackPressed.observe(viewLifecycleOwner) {
   finish(this@FirstReciptAndSecondReciptFragment)
  }

 }
}