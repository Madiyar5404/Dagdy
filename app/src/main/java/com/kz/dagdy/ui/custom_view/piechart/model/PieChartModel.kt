package com.kz.dagdy.ui.custom_view.piechart.model

import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint

data class PieChartModel(
    var percentOfCircle: Float = 0F,
    var percentToStartAt: Float = 0F,
    var colorOfLine: Int = 0,
    var stroke: Float = 0F,
    var paint: Paint = Paint(),
    var paintRound: Boolean = true
) {
    init {
        if (percentOfCircle < 0 || percentOfCircle > 100) {
            percentOfCircle = 100F
        }
        percentOfCircle = 360 * percentOfCircle / 100
        if (percentToStartAt < 0 || percentToStartAt > 100) {
            percentToStartAt = 0F
        }
        percentToStartAt = 360 * percentToStartAt / 100
        if (colorOfLine == 0) {
            colorOfLine = Color.parseColor("#000000")
        }

        paint = Paint()
        paint.color = colorOfLine
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = stroke
        paint.isDither = true;
        if (paintRound) {
            paint.strokeJoin = Paint.Join.ROUND;
            paint.strokeCap = Paint.Cap.ROUND;
            paint.pathEffect = CornerPathEffect(8F);
        }
    }
}