package com.kz.dagdy.ui.calendar.model

import java.util.Date

data class CalendarVerticalModel(
    var date: Date = Date(),
    var dateStr: String = "",
    var data: ArrayList<DayDataVerticalModel> = ArrayList(),
    var typeLayout: Int
)