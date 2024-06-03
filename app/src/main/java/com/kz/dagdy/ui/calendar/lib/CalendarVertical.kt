package com.kz.dagdy.ui.calendar.lib

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kz.dagdy.R
import com.kz.dagdy.databinding.CalendarVerticalViewBinding
import com.kz.dagdy.ui.calendar.adapter.CalendarVerticalAdapter
import com.kz.dagdy.ui.calendar.callback.OnclickRecyclerViewParent
import com.kz.dagdy.ui.calendar.model.CalendarVerticalModel
import com.kz.dagdy.ui.calendar.utils.CallbackCalendarVertical
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker.endSelectDate
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker.startSelectDate
import com.kz.dagdy.ui.calendar.utils.GetDataCalendar
import com.kz.dagdy.ui.calendar.utils.StickHeaderItemDecoration
import com.kz.dagdy.ui.calendar.utils.convertFormatDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class CalendarVertical : LinearLayout,
    View.OnClickListener,
    OnclickRecyclerViewParent {

    val adapter by lazy { CalendarVerticalAdapter(context) }
    lateinit var callback: CallbackCalendarVertical
    val data = ArrayList<CalendarVerticalModel>()
    var maxNextMonth = 0
    private lateinit var binding: CalendarVerticalViewBinding

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.CalendarVertical)
        maxNextMonth = styledAttrs.getInt(R.styleable.CalendarVertical_maxNextMonth, 0)

        styledAttrs.recycle()

        init()
    }

    private fun init() {
        setOrientation(VERTICAL)
        binding = CalendarVerticalViewBinding.inflate(LayoutInflater.from(context), this, true)

        setDafaultData()
        initRecyclerView()
    }

    private fun setDafaultData() {
        startSelectDate = ""
        endSelectDate = ""
        CodeDatePicker.minDate = Date()
        CodeDatePicker.maxDate = SimpleDateFormat("dd MM yyyy").parse("20 10 2050")
        CodeDatePicker.TYPE_SELECTED = CodeDatePicker.DOUBLE_SELECTED
        CodeDatePicker.formatDateOutput = ""
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvCalendar.layoutManager = layoutManager
        binding.rvCalendar.setItemAnimator(DefaultItemAnimator())
        binding.rvCalendar.setHasFixedSize(true)
        binding.rvCalendar.addItemDecoration(StickHeaderItemDecoration(adapter))
        binding.rvCalendar.setAdapter(adapter)

        val mCalendar = Calendar.getInstance()
        mCalendar.firstDayOfWeek = Calendar.MONDAY // Set Monday as the first day of the week

        binding.rvCalendar.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    if (maxNextMonth == 0) {
                        GetDataCalendar().getDataDates(mCalendar, 6).forEach {
                            data.add(addDataHeader(it.date, it.dateStr))
                            data.add(it)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

        if (maxNextMonth != 0) {
            GetDataCalendar().getDataDates(mCalendar, maxNextMonth).forEach {
                data.add(addDataHeader(it.date, it.dateStr))
                data.add(it)
            }
        } else {
            GetDataCalendar().getDataDates(mCalendar, 6).forEach {
                data.add(addDataHeader(it.date, it.dateStr))
                data.add(it)
            }
        }

        adapter.setData(data)
        adapter.callbackRecyclerView(this)
    }


    private fun addDataHeader(date: Date, dateStr: String): CalendarVerticalModel {
        val data = CalendarVerticalModel(
            date,
            dateStr,
            ArrayList(),
            CodeDatePicker.VIEW_TYPE_HEADER
        )
        return data
    }

    override fun onClick(v: View?) {

    }

    fun callbackCalendarListener(callbackCalendar: CallbackCalendarVertical) {
        callback = callbackCalendar
    }

    override fun callbackRecyclerView(
        viewParent: Int,
        positionParent: Int,
        view: Int,
        position: Int
    ) {


        when (viewParent) {
            CodeDatePicker.ONCLICK_DATE -> {
                val mCalendar = Calendar.getInstance()

                if (startSelectDate.isEmpty()) {
                    val isDateSelectable = !data[positionParent].data[position].date.after(mCalendar.time) ||
                            SimpleDateFormat(CodeDatePicker.formatDate).format(mCalendar.time) ==
                            data[positionParent].data[position].fullDay

                    if (isDateSelectable) {
                        startSelectDate = SimpleDateFormat(CodeDatePicker.formatDate).format(
                            data[positionParent].data[position].date
                        )
                        adapter.notifyDataSetChanged()
                        callback.startDate(convertDate(startSelectDate))
                    }
                } else {
                    val dateSelected = SimpleDateFormat(CodeDatePicker.formatDate).format(
                        data[positionParent].data[position].date
                    )

                    if (startSelectDate == dateSelected) {
                        clearSelection()
                    } else {
                        if (CodeDatePicker.TYPE_SELECTED == CodeDatePicker.SINGGLE_SELECTED) {
                            startSelectDate = SimpleDateFormat(CodeDatePicker.formatDate).format(
                                data[positionParent].data[position].date
                            )
                            adapter.notifyDataSetChanged()
                            callback.startDate(convertDate(startSelectDate))
                        } else {
                            if (data[positionParent].data[position].date.after(
                                    SimpleDateFormat(CodeDatePicker.formatDate).parse(startSelectDate)
                                )
                            ) {
                                showWarningToast()
                            } else {
                                endSelectDate = dateSelected
                                adapter.notifyDataSetChanged()
                                callback.startDate(convertDate(startSelectDate))
                                callback.endDate(convertDate(endSelectDate))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun convertDate(dateStr: String): String {
        return if (CodeDatePicker.formatDateOutput.isNotEmpty()) {
            convertFormatDate(dateStr, CodeDatePicker.formatDate, CodeDatePicker.formatDateOutput)
        } else {
            dateStr
        }
    }

    private fun clearSelection() {
        startSelectDate = ""
        endSelectDate = ""
        adapter.notifyDataSetChanged()
    }

    private fun showWarningToast() {
        Toast.makeText(context, CodeDatePicker.titleWarningPreviousDate, Toast.LENGTH_LONG).show()
    }

    fun typeSelected(type: Int) {
        CodeDatePicker.TYPE_SELECTED = type
    }
}