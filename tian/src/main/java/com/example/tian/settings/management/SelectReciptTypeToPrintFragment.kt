package com.example.tian.settings.management
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tian.base.BaseFragment
import com.exmple.tian.R
import com.exmple.tian.databinding.FragmentSelectReciptTypeToPrintBinding

class SelectReciptTypeToPrintFragment : BaseFragment<FragmentSelectReciptTypeToPrintBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSelectReciptTypeToPrintBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reciptPrintSetting.setOnClickListener {
            navigate(
                this,
                R.id.action_selectReciptTypeToPrintFragment_to_reciptPrintSettingFragment
            )
        }

        binding.firstSecondRecipt.setOnClickListener {
            navigate(
                this,
                R.id.action_selectReciptTypeToPrintFragment_to_firstReciptAndSecondReciptFragment
            )
        }

        binding.back.setOnClickListener {

            finish(this@SelectReciptTypeToPrintFragment)
        }

        onBackPressed.observe(viewLifecycleOwner) {
            finish(this@SelectReciptTypeToPrintFragment)
        }
    }
}


