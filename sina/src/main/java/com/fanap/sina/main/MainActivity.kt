package com.fanap.sina.main

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import com.fanap.corepos.device.hsm.util.smartpeak.GlobalData.ifEntransActivityExist
import com.fanap.corepos.utils.NetworkChecker
import com.fanap.sina.databinding.ActivityMainBinding
import com.fanap.sina.main.fragments.SetupFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    var onTouchEvent = MutableLiveData(false)

    private lateinit var binding: ActivityMainBinding

    var track2 = ""

    lateinit var shared : SharedPreferences

    lateinit var editor : SharedPreferences.Editor

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var setupFragment: SetupFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//new
        ifEntransActivityExist = true

        shared = getSharedPreferences("DATA", MODE_PRIVATE)
        editor = shared.edit()

        lifecycleScope.launch(Dispatchers.Main) {
            delay(3_000)
            setupFragment.isCancelable = false
            if (!setupFragment.isAdded)
                setupFragment.show(supportFragmentManager, "")
        }

        NetworkChecker.onConnectionLost.observe(this,{
            val currentFragment = try {
                (binding.myNavHostFragment.findNavController().currentDestination as FragmentNavigator.Destination).className.split('.').last()

            }catch (e : Exception){
                (binding.myNavHostFragment.findNavController().currentDestination as DialogFragmentNavigator.Destination).className.split('.').last()
            }
            if(it == true){
                if (!setupFragment.isAdded && currentFragment=="SwipeFragment")
                    setupFragment.show(supportFragmentManager, "")
            }
        })

        setContentView(binding.root)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        onTouchEvent.postValue(true)
        return super.dispatchTouchEvent(ev)
    }

}