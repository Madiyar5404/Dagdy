package com.kz.dagdy.ui.calendar.utils

import com.kz.dagdy.ui.calendar.model.CalendarVerticalModel
import com.kz.dagdy.ui.calendar.model.DayDataVerticalModel
import java.text.SimpleDateFormat
import java.util.Calendar

class GetDataCalendar {

    val calendarModels = ArrayList<CalendarVerticalModel>()

    fun getDataDates(mCalendar: Calendar, totalMonth: Int): ArrayList<CalendarVerticalModel> {
        for (i in 0 until totalMonth) {
            calendarModels.add(addCalendarThisMonth(mCalendar))
            mCalendar.add(Calendar.MONTH, -1)
        }
        return calendarModels
    }

    private fun addCalendarThisMonth(mCalendar: Calendar): CalendarVerticalModel {
        return CalendarVerticalModel(
            mCalendar.time,
            SimpleDateFormat("MMM yyyy").format(mCalendar.time),
            addDataDate(mCalendar), CodeDatePicker.VIEW_TYPE_BODY
        )
    }

    private fun addDataDate(mCalendar: Calendar): ArrayList<DayDataVerticalModel> {
        val data = ArrayList<DayDataVerticalModel>()

        val calendar = mCalendar.clone() as Calendar
        calendar.set(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), 1)
        val dayNum = calendar.get(Calendar.DAY_OF_WEEK)
        calendar.add(Calendar.MONTH, -1)
        val icnt = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        if ((dayNum - 2) != 0) {
            for (i in (icnt - (dayNum - 2)) until icnt) {
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val monthStr = assignPad10(month)
                val dayStr = assignPad10(i + 1)
                val dateStr = String.format("%s-%s-%s", dayStr, monthStr, year)
                val dayData = DayDataVerticalModel(
                    year,
                    month,
                    i + 1,
                    dateStr,
                    SimpleDateFormat(CodeDatePicker.formatDate).parse(dateStr),
                    CodeDatePicker.DAY_PREVIOUS_MONTH
                )
                data.add(dayData)
            }
        }

        //get date at this month
        val totalDate = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 0 until totalDate) {
            val year = mCalendar.get(Calendar.YEAR)
            val month = mCalendar.get(Calendar.MONTH) + 1
            val monthStr = assignPad10(month)
            val dayStr = assignPad10(i + 1)
            val dateStr = String.format("%s-%s-%s", dayStr, monthStr, year)
            val calendar = mCalendar.clone() as Calendar
            val date = SimpleDateFormat(CodeDatePicker.formatDate).parse(dateStr)
            calendar.time = date
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val dayData =
                DayDataVerticalModel(year, month, i + 1, dateStr, date, setTypeDay(dayOfWeek))
            data.add(dayData)
        }

        val afterCalendar = mCalendar.clone() as Calendar
        afterCalendar.set(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), 1)
        afterCalendar.add(Calendar.MONTH, +1)
        val daynumNextMonth = afterCalendar.get(Calendar.DAY_OF_WEEK)

        if (data.size % 7 != 0) {
            for (i in 0 until 7 - (daynumNextMonth - 1)) {
                val year = afterCalendar.get(Calendar.YEAR)
                val month = afterCalendar.get(Calendar.MONTH) + 1
                val monthStr = assignPad10(month)
                val dayStr = assignPad10(i + 1)
                val dateStr = String.format("%s-%s-%s", dayStr, monthStr, year)
                val dayData = DayDataVerticalModel(
                    year,
                    month,
                    i + 1,
                    dateStr,
                    SimpleDateFormat(CodeDatePicker.formatDate).parse(dateStr),
                    CodeDatePicker.DAY_NEXT_MONTH
                )
                data.add(dayData)
            }
        }

        return data
    }

    private fun setTypeDay(dayOfWeek: Int): Int {
        var n = CodeDatePicker.DAY_THIS_MONTH
        if (dayOfWeek == Calendar.MONDAY) {
            n = CodeDatePicker.DAY_MONDAY
        }
        return n
    }

}