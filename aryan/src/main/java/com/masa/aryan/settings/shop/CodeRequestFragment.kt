package com.masa.aryan.settings.shop
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.fanap.corepos.database.service.model.User
import com.fanap.corepos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import com.masa.aryan.R
import com.masa.aryan.base.BaseFragment
import com.masa.aryan.databinding.FragmentCodeRequestBinding
import com.masa.aryan.settings.shop.viewmodel.CodeRequestViewModel

class CodeRequestFragment :BaseFragment<FragmentCodeRequestBinding>(){

 private val viewModel : CodeRequestViewModel by viewModels()
 private lateinit var users:User

 override fun getViewBinding(
  inflater: LayoutInflater,
  container: ViewGroup?
 ) =  FragmentCodeRequestBinding.inflate(inflater,container,false)


 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  binding.viewModel = viewModel

  viewModel.onError.observe(viewLifecycleOwner) {
   Utils.makeSnack(binding.root, it, Snackbar.LENGTH_SHORT).show()
  }

  viewModel.onSuccess.observe(viewLifecycleOwner) {
   Utils.makeSnack(binding.root, "تنظیمات پایانه با موفقیت ذخیره شدند.", Snackbar.LENGTH_SHORT)
    .show()
   navigate(
    this,
    R.id.action_codeRequestFragment_to_shiftFragment)
  }

  binding.exit.setOnClickListener {
   finish(this@CodeRequestFragment)
  }

  binding.back.setOnClickListener {
   finish(this@CodeRequestFragment)
  }

  onBackPressed.observe(viewLifecycleOwner) {
   finish(this@CodeRequestFragment)
  }



 }
}
