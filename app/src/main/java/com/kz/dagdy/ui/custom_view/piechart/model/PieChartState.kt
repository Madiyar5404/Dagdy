package com.kz.dagdy.ui.custom_view.piechart.model

import android.os.Parcelable
import android.view.View.BaseSavedState

class PieChartState(
    private val superSavepoint: Parcelable?,
    val dataList: List<Pair<Int, String>>
) : BaseSavedState(superSavepoint), Parcelable