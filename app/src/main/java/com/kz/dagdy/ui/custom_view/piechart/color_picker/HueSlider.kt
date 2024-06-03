package com.kz.dagdy.ui.custom_view.piechart.color_picker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import com.kz.dagdy.R

class HueSlider(context: Context, attrs: AttributeSet) : SliderView(context, attrs) {
    private val huePaint = Paint()
    private var bitmap: Bitmap? = null
    private val hueRect = RectF()
    private val bitmapRect = RectF()
    private val roundSize = resources.getDimensionPixelSize(R.dimen.dp_15).toFloat()
    private val clipPath = Path()
    override fun onDraw(canvas: Canvas) {
        if (bitmap != null) {
            bitmapRect.left = (getRadius() + paddingLeft).toFloat()
            bitmapRect.top = paddingTop.toFloat()
            bitmapRect.right = maxTrackSize() + bitmapRect.left
            bitmapRect.bottom = (measuredHeight - paddingBottom).toFloat()

            canvas.save()
            clipPath.reset()
            clipPath.addRoundRect(bitmapRect, roundSize, roundSize, Path.Direction.CCW)
            canvas.clipPath(clipPath)
            canvas.drawBitmap(bitmap!!, null, bitmapRect, null)
            canvas.restore()
        }
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        onSize()
    }

    private fun onSize() {
        bitmap = Bitmap.createBitmap(
            maxTrackSize().toInt(),
            measuredHeight - paddingTop - paddingBottom,
            Bitmap.Config.ARGB_8888
        )
        updatePaint()
        updateBitmap()
    }

    private fun updateBitmap() {
        if (bitmap != null) {
            val spaceH = getRadius().toFloat()
            val spaceV = 2.toPx.toFloat()
            val w = bitmap!!.width.toFloat()
            hueRect.fromXYWH(
                0f, 0f, w,
                bitmap!!.height.toFloat()
            )
            val canvas = Canvas(bitmap!!)
            canvas.drawRect(hueRect, huePaint)
        }
    }

    private fun updatePaint() {
        val w = bitmap!!.width
        val hueIncrement = 360f / w
        var currentHue = 0f

        val hsv = FloatArray(3)
        hsv[1] = 1f
        hsv[2] = 1f

        val colors = IntArray(w)

        for (i in 0 until w) {
            hsv[0] = currentHue
            colors[i] = Color.HSVToColor(hsv)
            currentHue += hueIncrement
        }

        huePaint.shader = LinearGradient(
            0f, 0f, w.toFloat(), 0f,
            colors, null,
            Shader.TileMode.CLAMP
        )
    }

    fun setHue(hue: Int) {
        setRatio(hue / 360f)
    }

    fun getColor(): Int {
        return Color.HSVToColor(floatArrayOf(360 * currentRatio, 1f, 1f))
    }
}