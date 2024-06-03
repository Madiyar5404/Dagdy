package com.kz.dagdy.ui.calendar.model

import java.util.Date

data class DayDataVerticalModel(
    var year: Int = 0,
    var month: Int = 0,
    var day: Int = 0,
    var fullDay: String = "",
    var date: Date,
    var typeDay: Int = 0
)
