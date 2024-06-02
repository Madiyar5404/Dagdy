package com.kz.dagdy.ui.custom_view.piechart.extension

import android.content.Context
import android.util.TypedValue

fun Context.dpToPx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}

fun Context.spToPx(sp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp.toFloat(),
        this.resources.displayMetrics
    );
}