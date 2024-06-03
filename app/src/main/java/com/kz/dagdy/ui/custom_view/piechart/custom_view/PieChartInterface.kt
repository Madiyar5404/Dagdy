package com.kz.dagdy.ui.custom_view.piechart.custom_view

interface PieChartInterface {
    fun setDataChart(list: List<Pair<Int, String>>)
    fun startAnimation()
}