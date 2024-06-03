package com.kz.dagdy.ui.calendar.utils

import java.text.SimpleDateFormat
import java.util.Date

object CodeDatePicker {
    var titleWarningPreviousDate = "You can't select the previous date"
    val VIEW_TYPE_HEADER = 1
    val VIEW_TYPE_BODY = 2
    val DAY_PREVIOUS_MONTH = 3
    val DAY_THIS_MONTH = 4
    val DAY_NEXT_MONTH = 5
    val ONCLICK_DATE = 6
    val DAY_MONDAY = 2
    val DAY_SUNDAY = 7
    val DAY_SATURDAY = 6
    var fontDay = -1
    var fontMonthHeader = -1
    var fontDayHeader = -1
    var startSelectDate = ""
    var endSelectDate = ""
    val SINGGLE_SELECTED = 7
    val DOUBLE_SELECTED = 8
    var TYPE_SELECTED = DOUBLE_SELECTED
    val formatDate = "dd-MM-yyyy"
    var formatDateOutput = ""

    var minDate = Date()
    var maxDate = SimpleDateFormat("dd MM yyyy").parse("20 10 2050")
}
