package com.fanap.sina.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.fanap.sina.base.BaseFragment
import com.fanap.sina.databinding.FragmentFullScreenBinding
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScannerFragment : BaseFragment<FragmentFullScreenBinding>(),
    ZXingScannerView.ResultHandler{
    private var mScannerView: ZXingScannerView? = null
    private var mFlash = false
    private var mAutoFocus = true
    private var mCameraId = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mScannerView = ZXingScannerView(activity)
        return mScannerView
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera(mCameraId)
        mScannerView?.flash = mFlash
        mScannerView?.setAutoFocus(mAutoFocus)
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        setFragmentResult("100", bundleOf("data" to rawResult?.text))
        finish(this)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFullScreenBinding {
        return FragmentFullScreenBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()

        onTimerFinish.observe(viewLifecycleOwner,{
            finish(this)
        })
        onBackPressed.observe(viewLifecycleOwner,{
            finish(this)
        })

    }
}