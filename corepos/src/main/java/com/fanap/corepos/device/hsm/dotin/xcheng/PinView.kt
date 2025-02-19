/*
package com.fanap.corepos.device.hsm.dotin.xcheng

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.fanap.corepos.R
import com.fanap.corepos.databinding.FragmentPasswordCardBinding
import java.nio.ByteBuffer

class PinpadView private constructor() {
	var byo_state = true
	private val pinpad_id =
		arrayOf(intArrayOf(1, 4, 7, 10), intArrayOf(2, 5, 8, 0), intArrayOf(3, 6, 9, 11))
	private var a: Context? = null
	var mDialog: AlertDialog? = null
	private var layout: FrameLayout? = null
	private var root: LinearLayout? = null
	private var byo: TextView? = null
	private var mHandle: Handler? = null
	private val isHardwareKeyboard = true
	private var pinpad_coordinate: ByteBuffer? = null
	private val key_view = arrayOfNulls<TextView>(12)
	private val pwdMold = 13
	private var title: String? = null
	@Suppress("DEPRECATION")
	fun create(context: Context?, title: String?) {
		a = context
		this.title = title
		if (mDialog == null) mDialog = AlertDialog.Builder(context!!.applicationContext).create()
		pinpad_coordinate = if (pwdMold == 12) {
			ByteBuffer.allocate(96)
		} else {
			ByteBuffer.allocate(104)
		}
		mHandle = Handler(Looper.getMainLooper())
		initMap()
		boundary(context)
		mDialog!!.show()
		mDialog!!.setCancelable(false)
		mDialog!!.setOnKeyListener { dialogInterface: DialogInterface?, i: Int, keyEvent: KeyEvent? -> false }

		mDialog!!.window!!.setContentView(layout)
		mDialog!!.window!!.setGravity(Gravity.BOTTOM)
		mDialog!!.window!!.setBackgroundDrawableResource(
			android.R.color.transparent
		)
		val wmlp = mDialog!!.window!!.attributes
		val wm = a!!
			.getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val d = wm.defaultDisplay
//		wmlp.height = 620;
		wmlp.width = d.width
//		wmlp.y = 0
		mDialog!!.window!!.attributes = wmlp
	}

	private fun initMap() {
		map["0"] = "0"
		map["1"] = "1"
		map["2"] = "2"
		map["3"] = "3"
		map["4"] = "4"
		map["5"] = "5"
		map["6"] = "6"
		map["7"] = "7"
		map["8"] = "8"
		map["9"] = "9"
		map["-21"] = "ESC"
		map["-35"] = "Enter"
	}

	fun close() {
		if (mDialog != null) {
			mDialog!!.cancel()
			mDialog = null
			layout = null
			root = null
			byo = null
			pinpad_coordinate!!.clear()
		}
	}

	fun calculation(keys: ByteArray): ByteArray {
		for (i in 0..key_view.size - 1) {
			val tv = key_view[i]
			Log.d("TAG", (keys[i] - 0x30).toString() + "")
			tv!!.text = map[(keys[i] - 0x30).toString() + ""]
			val pos = ByteArray(8)
			val location = IntArray(2)
			tv.getLocationOnScreen(location)
			val leftx = location[0]
			val lefty = location[1]
			val rightx = location[0] + tv.width
			val righty = location[1] + tv.height
			val tmp = intToBytes(leftx)
			val tmp1 = intToBytes(lefty)
			val tmp2 = intToBytes(rightx)
			val tmp3 = intToBytes(righty)
			pos[0] = tmp[2]
			pos[1] = tmp[3]
			pos[2] = tmp1[2]
			pos[3] = tmp1[3]
			pos[4] = tmp2[2]
			pos[5] = tmp2[3]
			pos[6] = tmp3[2]
			pos[7] = tmp3[3]
			pinpad_coordinate!!.put(pos)
		}
		return pinpad_coordinate!!.array()
	}

	private fun passwordLengthShow(context: Context): TextView {
		byo = TextView(context)
		val ll = LinearLayout.LayoutParams(
			match_content_width, 0, 1F
		)
		byo!!.layoutParams = ll
		byo!!.gravity = Gravity.CENTER
		byo!!.textSize = 20f
		return byo as TextView
	}

	private fun titleShow(context: Context): TextView {
		val mTitle = TextView(context)
		val ll = LinearLayout.LayoutParams(
			match_content_width, 20, 1F
		)
		mTitle.layoutParams = ll
		mTitle.gravity = Gravity.CENTER
		mTitle.textSize = 20f
		mTitle.text = if (title == null) "Please enter PIN" else title
		return mTitle
	}

	private fun boundary(context: Context?) {
		layout = FrameLayout(context!!)
		layout!!.layoutParams = FrameLayout.LayoutParams(
			match_content_width, match_content_height
		)
		//layout.setOrientation(LinearLayout.VERTICAL);
		layout!!.setBackgroundColor(Color.WHITE)
		layout!!.setPadding(10, 10, 10, 10)
		if (byo_state) {
			//layout.setWeightSum(8);
			//layout.addView(titleShow(context));
			//layout.addView(passwordLengthShow(context));
			val binding: FragmentPasswordCardBinding = DataBindingUtil.inflate(
				LayoutInflater.from(context),
				R.layout.fragment_password_card,
				null,
				false
			)
			layout = binding.bottomSheetDialog
			byo = binding.edtPassword
		} else {
			//layout.setWeightSum(6);
		}
		root = LinearLayout(context)
		root!!.layoutParams = LinearLayout.LayoutParams(
			match_content_width,
			0, 6F
		)
		root!!.orientation = LinearLayout.HORIZONTAL
		root!!.weightSum = 3f
		if (!isHardwareKeyboard) {
			root!!.addView(column(context, 0, pinpad_id))
			root!!.addView(column(context, 1, pinpad_id))
			root!!.addView(column(context, 2, pinpad_id))
		}
		layout!!.addView(root)
	}

	fun byoShow(s: String) {
		if (byo != null) {
			mHandle!!.post { byo!!.text = s }
		}
	}

	private fun column(context: Context?, index: Int, id: Array<IntArray>): LinearLayout {
		val column = LinearLayout(context)
		val column_ll = LinearLayout.LayoutParams(
			match_content_width, match_content_height
		)
		column_ll.weight = 1f
		column.layoutParams = column_ll
		column.orientation = LinearLayout.VERTICAL
		column.setBackgroundColor(Color.WHITE)
		column.weightSum = 4f
		column.addView(row(context, id[index][0]))
		column.addView(row(context, id[index][1]))
		column.addView(row(context, id[index][2]))
		column.addView(row(context, id[index][3]))
		return column
	}

	@SuppressLint("NewApi")
	private fun row(context: Context?, id: Int): TextView {
		val tv = TextView(context)
		val tv_ll = LinearLayout.LayoutParams(
			match_content_width, match_content_height
		)
		tv_ll.weight = 1f
		tv.layoutParams = tv_ll
		tv.gravity = Gravity.CENTER
		tv.textSize = 18f
		tv.setTextColor(Color.BLACK)
		tv.paint.isFakeBoldText = true
		tv.background = buttonResult()
		tv.id = id
		key_view[id] = tv
		return tv
	}

	private fun buttonResult(): Drawable {
		val sld = StateListDrawable()
		sld.addState(
			intArrayOf(android.R.attr.state_pressed),
			genDrawable(-0x29292a, 1, -0x161617, 0f)
		)
		sld.addState(
			intArrayOf(-android.R.attr.state_pressed),
			genDrawable(-0x1, 1, -0x161617, 0f)
		)
		return sld
	}

	private fun genDrawable(
		color: Int, weight: Int, linellae_color: Int,
		radius: Float
	): Drawable {
		val gd_down = GradientDrawable()
		gd_down.setColor(color)
		gd_down.setStroke(weight, linellae_color)
		gd_down.cornerRadius = radius
		return gd_down
	}

	private fun intToBytes(value: Int): ByteArray {
		return byteArrayOf(
			(value shr 24 and 255).toByte(),
			(value shr 16 and 255).toByte(),
			(value shr 8 and 255).toByte(),
			(value and 255).toByte()
		)
	} */
/*FragmentPasswordCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(a), R.layout.fragment_password_card, null, false);
			layout = binding.bottomSheetDialog;
			byo = binding.edtPassword;
*//*


	companion object {
		const val warp_content_width = -2
		const val warp_content_height = -2
		const val match_content_width = -1
		const val match_content_height = -1
		private var mPinpad: PinpadView? = null
		private val map = HashMap<String, String>()
		fun INSTANCE(): PinpadView {
			return if (mPinpad == null) PinpadView().also {
				mPinpad = it
			} else mPinpad!!
		}
	}
}*/
