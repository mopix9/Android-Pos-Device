package com.fanap.corepos.utils

import android.content.Context
import android.view.inputmethod.InputConnection
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.util.SparseArray
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.LinearLayout
import com.fanap.corepos.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomKeyboard(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr), View.OnClickListener , CoroutineScope {

    private val TAG = this::class.java.simpleName

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    // constructors
    constructor(context: Context?) : this(context, null, 0) {
        Log.d(TAG,"constructor(context: Context?)")
    }
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        Log.d(TAG,"constructor(context: Context?, attrs: AttributeSet?)")
    }

    private var  slideDown: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
    private var  slideUp: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
    private var isOpen = false

    private lateinit var root: LinearLayout
    // keyboard keys (buttons)
    private lateinit var mButton1: TextView
    private lateinit var mButton2: TextView
    private lateinit var mButton3: TextView
    private lateinit var mButton4: TextView
    private lateinit var mButton5: TextView
    private lateinit var mButton6: TextView
    private lateinit var mButton7: TextView
    private lateinit var mButton8: TextView
    private lateinit var mButton9: TextView
    private lateinit var mButton0: TextView
    private lateinit var mButtonDelete: TextView
    private lateinit var mButtonDot: TextView
    private lateinit var mButtonFocus: TextView
    private lateinit var mButtonClose: TextView
    private lateinit var mButtonSharp: TextView
    private lateinit var mButtonStar: TextView

    var keyValues = SparseArray<String>()

    private lateinit var inputConnection: InputConnection
    private fun init(context: Context?, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true)

        mButton1 = findViewById<View>(R.id.button_1) as TextView
        mButton2 = findViewById<View>(R.id.button_2) as TextView
        mButton3 = findViewById<View>(R.id.button_3) as TextView
        mButton4 = findViewById<View>(R.id.button_4) as TextView
        mButton5 = findViewById<View>(R.id.button_5) as TextView
        mButton6 = findViewById<View>(R.id.button_6) as TextView
        mButton7 = findViewById<View>(R.id.button_7) as TextView
        mButton8 = findViewById<View>(R.id.button_8) as TextView
        mButton9 = findViewById<View>(R.id.button_9) as TextView
        mButton0 = findViewById<View>(R.id.button_0) as TextView
        mButtonDelete = findViewById<View>(R.id.button_delete) as TextView
        mButtonDot = findViewById<View>(R.id.button_dot) as TextView
        mButtonFocus = findViewById<View>(R.id.button_focus) as TextView
        mButtonClose = findViewById<View>(R.id.button_close) as TextView
        mButtonSharp = findViewById<View>(R.id.button_sharp) as TextView
        mButtonStar = findViewById<View>(R.id.button_star) as TextView

        root = findViewById<View>(R.id.lnr_keyboard_root) as LinearLayout

        // set button click listeners
        mButton1.setOnClickListener(this)
        mButton2.setOnClickListener(this)
        mButton3.setOnClickListener(this)
        mButton4.setOnClickListener(this)
        mButton5.setOnClickListener(this)
        mButton6.setOnClickListener(this)
        mButton7.setOnClickListener(this)
        mButton8.setOnClickListener(this)
        mButton9.setOnClickListener(this)
        mButton0.setOnClickListener(this)
        mButtonDelete.setOnClickListener(this)
        mButtonDot.setOnClickListener(this)
        mButtonFocus.setOnClickListener(this)
        mButtonClose.setOnClickListener(this)
        mButtonSharp.setOnClickListener(this)
        mButtonStar.setOnClickListener(this)

        root.setOnClickListener(this)

        // map buttons IDs to input strings
        keyValues.put(R.id.button_1, "1")
        keyValues.put(R.id.button_2, "2")
        keyValues.put(R.id.button_3, "3")
        keyValues.put(R.id.button_4, "4")
        keyValues.put(R.id.button_5, "5")
        keyValues.put(R.id.button_6, "6")
        keyValues.put(R.id.button_7, "7")
        keyValues.put(R.id.button_8, "8")
        keyValues.put(R.id.button_9, "9")
        keyValues.put(R.id.button_0, "0")
        keyValues.put(R.id.button_dot, ".")
        keyValues.put(R.id.button_sharp, "#")
        keyValues.put(R.id.button_star, "*")

    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.lnr_keyboard_root -> { }
            R.id.button_delete -> {
                val selectedText = inputConnection.getSelectedText(0)
                if (TextUtils.isEmpty(selectedText))
                    inputConnection.deleteSurroundingText(1, 0)
                else
                    inputConnection.commitText("", 1)
            }
            R.id.button_close ->{
                slideDownAnimation()
            }

            else ->{
                val value = keyValues[v.id]
                inputConnection.commitText(value, 1)
            }
        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    @JvmName("setInputConnection1")
    fun setInputConnection(ic: InputConnection) {
        slideUpAnimation()
        inputConnection = ic
    }

    init {
        init(context, attrs)
    }

    private fun slideUpAnimation(){
        if (!isOpen) {
            isOpen = true
            visibility = View.VISIBLE
            this.startAnimation(slideUp)
        }
    }

    fun slideDownAnimation(){
        if(isOpen) {
            this.startAnimation(slideDown)
            launch {
                delay(500)
                visibility = View.GONE
                isOpen = false
            }
        }
    }

}