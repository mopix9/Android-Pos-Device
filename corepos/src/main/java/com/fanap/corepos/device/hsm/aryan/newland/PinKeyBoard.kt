package com.fanap.corepos.device.hsm.aryan.newland

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.newland.nsdk.core.api.common.utils.LogUtils

class PinKeyBoard : View {
    private var width = 0f
    private var height = 0f
    private var paint: Paint? = null
    private lateinit var nums: IntArray
    private var contentsize = 0
    private var path: Path? = null
    private val normalTypeface = Typeface.createFromAsset(context.assets, "fonts/iransans.ttf")


    // For function key randomization
    private val rf = byteArrayOf(0x1B, 0x0A, 0x0D)
    private var dm: DisplayMetrics? = null

    // digital background color, the dividing line, the font color of the function key, the number, the cancel background, the backspace background, and the determination background
    var colors = intArrayOf(-0xa0a07, -0x1e1e1f, -0x1, -0x1000000, -0xdb3b3, -0xc1db0, -0x8f2ebb)

    constructor(context: Context) : super(context) {
        getScreenResolution(context)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getScreenResolution(context)
        init()
    }

    private fun getScreenResolution(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager


        dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        LogUtils.i(
            "N900PinKeyBoard----1",
            "height=" + dm!!.heightPixels + ";width" + dm!!.widthPixels
        )
    }

    private fun init() {
        path = Path()
        paint = Paint()
        paint!!.isAntiAlias = true
        paint!!.typeface = normalTypeface
        nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(colors[0])
        when {
            rf[0] == 0x1B.toByte() // red
            -> paint!!.color = colors[4]
            rf[0] == 0x0A.toByte() // yellow
            -> paint!!.color = colors[5]
            rf[0] == 0x0D.toByte() // green
            -> paint!!.color = colors[6]
        }
        canvas.drawRect(0f, height / 4 * 3 + 1, width / 4 - 1, height, paint!!)
        when {
            rf[1] == 0x1B.toByte() // red
            -> paint!!.color = colors[4]
            rf[1] == 0x0A.toByte() // yellow
            -> paint!!.color = colors[5]
            rf[1] == 0x0D.toByte() // green
            -> paint!!.color = colors[6]
        }
        canvas.drawRect(width / 4 * 3 + 1, 0f, width, height / 2, paint!!)
        when {
            rf[2] == 0x1B.toByte() // red
            -> paint!!.color = colors[4]
            rf[2] == 0x0A.toByte() // yellow
            -> paint!!.color = colors[5]
            rf[2] == 0x0D.toByte() // green
            -> paint!!.color = colors[6]
        }
        canvas.drawRect(width / 4 * 3 + 1, height / 2, width, height, paint!!)
        paint!!.color = colors[1]
        paint!!.strokeWidth = 1f
        canvas.drawLine(0f, height / 4, width / 4 * 3, height / 4, paint!!)
        canvas.drawLine(0f, height / 2, width / 4 * 3, height / 2, paint!!)
        canvas.drawLine(0f, height / 4 * 3, width / 4 * 3, height / 4 * 3, paint!!)
        canvas.drawLine(width / 4, 0f, width / 4, height, paint!!)
        canvas.drawLine(width / 2, 0f, width / 2, height / 4 * 3, paint!!)
        canvas.drawLine(width / 4 * 3, 0f, width / 4 * 3, height, paint!!)
        paint!!.color = colors[2]
        paint!!.textSize = contentsize.toFloat()
        for (i in rf.indices) {
            var cx = 0f
            var cy = 0f
            if (i == 0) {
                cx = width / 8
                cy = height / 8 * 7
            } else if (i == 1) {
                cx = width / 8 * 7
                cy = height / 4
            } else if (2 == i) {
                cx = width / 8 * 7
                cy = height / 4 * 3
            }
            when {
                rf[i] == 0x1B.toByte() -> {
                    drawStringCenter(canvas, cx, cy, "لغو")
                }
                rf[i] == 0x0A.toByte() -> {
                    drawdelete(canvas, cx, cy, height / 12)
                }
                rf[i] == 0x0D.toByte() -> {
                    drawStringCenter(canvas, cx, cy, "تایید")
                }
            }
        }
        paint!!.color = colors[3]
        paint!!.textSize = (contentsize + 10).toFloat()
        drawStringCenter(canvas, width / 8, height / 8, nums[0].toString() + "")
        drawStringCenter(canvas, width / 8 * 3, height / 8, nums[1].toString() + "")
        drawStringCenter(canvas, width / 8 * 5, height / 8, nums[2].toString() + "")
        drawStringCenter(canvas, width / 8, height / 8 * 3, nums[3].toString() + "")
        drawStringCenter(canvas, width / 8 * 3, height / 8 * 3, nums[4].toString() + "")
        drawStringCenter(canvas, width / 8 * 5, height / 8 * 3, nums[5].toString() + "")
        drawStringCenter(canvas, width / 8, height / 8 * 5, nums[6].toString() + "")
        drawStringCenter(canvas, width / 8 * 3, height / 8 * 5, nums[7].toString() + "")
        drawStringCenter(canvas, width / 8 * 5, height / 8 * 5, nums[8].toString() + "")
        drawStringCenter(canvas, width / 2, height / 8 * 7, nums[9].toString() + "")
    }

    private fun drawStringCenter(
        canvas: Canvas, centerpointX: Float,
        centerpointY: Float, s: String
    ) {
        paint!!.style = Paint.Style.FILL
        paint!!.strokeWidth = 4f
        val fmtemp = paint!!.fontMetrics
        val ctwidth = paint!!.measureText(s).toInt()
        val ctheight =
            Math.ceil((fmtemp.descent - fmtemp.ascent).toDouble()).toInt()
        val ctdescent = fmtemp.descent.toInt()
        canvas.drawText(
            s, centerpointX - ctwidth / 2, centerpointY - ctdescent
                    + ctheight / 2, paint!!
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        width = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        height = width / 5 * 4
        for (size in 15..99) {
            paint!!.textSize = size.toFloat()
            val fm = paint!!.fontMetrics
            val cs = fm.descent - fm.ascent
            val tempw = paint!!.measureText("تایید")
            if (cs > height / 8 || tempw > width / 8) {
                contentsize = size
                break
            }
        }
        setMeasuredDimension(width.toInt(), height.toInt())
    }

    fun loadRandomKeyboardfinished(sr: ByteArray) {
        val numsbyte = byteArrayOf(
            sr[0], sr[1], sr[2], sr[4], sr[5],
            sr[6], sr[8], sr[9], sr[10], sr[12]
        )
        val nums = IntArray(numsbyte.size)
        for (i in numsbyte.indices) {
            nums[i] = numsbyte[i] - 48
        }
        rf[0] = sr[3]
        rf[1] = sr[7]
        rf[2] = sr[14]
        setRandomNumber(nums)
    }

    // Set the corresponding key position after random
    fun setRandomNumber(nums: IntArray) {
        this.nums = nums
        invalidate()
    }

    private lateinit var coordinateInt: IntArray//Cancel

    //Delete
    // null
    // 0
    // null
    //confirm
    // Initial set of coordinates
    // Gets the random keyboard key coordinates
    val coordinate: ByteArray
        get() {
            val l = IntArray(2)
            getLocationOnScreen(l)
            val x0 = l[0]
            val x1 = (l[0] + width / 4).toInt()
            val x2 = (l[0] + width / 2).toInt()
            val x3 = (l[0] + width / 4 * 3).toInt()
            val x4 = (l[0] + width).toInt()
            val y0 = l[1]
            val y1 = (l[1] + height / 4).toInt()
            val y2 = (l[1] + height / 2).toInt()
            val y3 = (l[1] + height / 4 * 3).toInt()
            val y4 = (l[1] + height).toInt()
            if (l[1] != 0) coordinateInt = intArrayOf(
                x0, y0, x1, y1, x1, y0, x2, y1, x2, y0, x3, y1,
                x0, y3, x1, y4,  //Cancel
                x0, y1, x1, y2, x1, y1, x2, y2, x2, y1, x3, y2,
                x3, y0, x4, y2,  //Delete
                x0, y2, x1, y3, x1, y2, x2, y3, x2, y2, x3, y3,
                x0, y0, x0, y0,  // null
                x1, y3, x3, y4,  // 0
                x4, y4, x4, y4,  // null
                x3, y2, x4, y4
            ) //confirm
            // Initial set of coordinates
            val initCoordinate = ByteArray(coordinateInt.size * 2)
            var i = 0
            var j = 0
            while (i < coordinateInt.size) {
                initCoordinate[j] = (coordinateInt[i] shr 8 and 0xff).toByte()
                j++
                initCoordinate[j] = (coordinateInt[i] and 0xff).toByte()
                i++
                j++
            }
            return initCoordinate
        }

    private fun drawdelete(
        canvas: Canvas, centerX: Float, centerY: Float,
        sizeheight: Float
    ) {
        val left = centerX - sizeheight
        val top = centerY - sizeheight / 2
        path!!.reset()
        path!!.moveTo(left, top + sizeheight / 2)
        path!!.lineTo(left + sizeheight / 2, top)
        path!!.lineTo(left + 2 * sizeheight, top)
        path!!.lineTo(left + 2 * sizeheight, top + sizeheight)
        path!!.lineTo(left + sizeheight / 2, top + sizeheight)
        path!!.close()
        val gap = 8
        path!!.moveTo(left + 2 * sizeheight / 8 * 3 + 5, top + gap)
        path!!.lineTo(left + 2 * sizeheight / 8 * 7 - 5, top + sizeheight - gap)
        path!!.moveTo(left + 2 * sizeheight / 8 * 7 - 5, top + gap)
        path!!.lineTo(left + 2 * sizeheight / 8 * 3 + 5, top + sizeheight - gap)
        paint!!.strokeWidth = 4f
        //		paint.setColor(keyboardcolors[2]);
        paint!!.style = Paint.Style.STROKE
        canvas.drawPath(path!!, paint!!)
    }

    object PinKeySeq {
        /**
         * Number key and function key are not random.
         */
        const val NORMAL = 0

        /**
         * Numbers are random,but function keys are not random.
         */
        const val RANDOM_NUM = 1

        /**
         * Number key and function key are random.
         */
        const val RANDOM_ALL = 2
    }
}
