package com.kz.dagdy.ui.custom_view.piechart.customView

interface PieChartInterface {
    fun setDataChart(list: List<Pair<Int, String>>)
    fun startAnimation()
}