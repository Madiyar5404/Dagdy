package com.kz.dagdy.ui.custom_view.piechart.color_picker

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Size
import android.util.TypedValue
import android.view.View

object ColorPickerUtils {
    fun setWrapSize(
        widthInPixel: Int,
        heightInPixel: Int,
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ): Size {
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        val width = when (widthMode) {
            View.MeasureSpec.EXACTLY -> {
                widthSize
            }

            View.MeasureSpec.AT_MOST -> {
                Math.min(widthInPixel, widthSize)
            }

            else -> {
                widthInPixel
            }
        }
        val height = when (heightMode) {
            View.MeasureSpec.EXACTLY -> {
                heightSize
            }

            View.MeasureSpec.AT_MOST -> {
                Math.min(heightInPixel, heightSize)
            }

            else -> {
                heightInPixel
            }
        }
        return Size(width, height)
    }

    fun fillPaint(color: Int = Color.BLACK): Paint = Paint().apply {
        style = Paint.Style.FILL
        this.color = color
    }

    fun toRGB(colorWithAlpha: Int): Int {
        return Color.rgb(
            Color.red(colorWithAlpha),
            Color.green(colorWithAlpha),
            Color.blue(colorWithAlpha)
        )
    }

    fun colorWithAlpha(a: Int, color: Int): Int {
        return Color.argb(a, Color.red(color), Color.green(color), Color.blue(color))
    }
}

fun RectF.fromXYWH(x: Float, y: Float, w: Float, h: Float): RectF {
    left = x
    top = y
    right = x + w
    bottom = y + h
    return this
}

fun Int.toHSV(): FloatArray {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    return hsv
}

fun View.getRectF() = RectF().fromXYWH(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
val Number.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()