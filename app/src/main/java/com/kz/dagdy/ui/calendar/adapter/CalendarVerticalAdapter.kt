package com.kz.dagdy.ui.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kz.dagdy.R
import com.kz.dagdy.databinding.BodyCalendarViewBinding
import com.kz.dagdy.databinding.HeaderCalendarViewBinding
import com.kz.dagdy.ui.calendar.callback.OnclickRecyclerView
import com.kz.dagdy.ui.calendar.callback.OnclickRecyclerViewParent
import com.kz.dagdy.ui.calendar.model.CalendarVerticalModel
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker.VIEW_TYPE_BODY
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker.VIEW_TYPE_HEADER
import com.kz.dagdy.ui.calendar.utils.StickyHeaderInterface
import java.util.ArrayList

class CalendarVerticalAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderInterface {

    var dataList = ArrayList<CalendarVerticalModel>()
    lateinit var onclick: OnclickRecyclerViewParent

    fun setData(dataList: ArrayList<CalendarVerticalModel>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderHolder(
                HeaderCalendarViewBinding.inflate(inflater, parent, false)
            )

            else -> BodyHolder(
                BodyCalendarViewBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER -> (holder as HeaderHolder).bind(dataList[position], position)
            VIEW_TYPE_BODY -> (holder as BodyHolder).bind(dataList[position], position)
        }
    }

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        val typeLayout = dataList[position].typeLayout
        return when (typeLayout) {
            VIEW_TYPE_HEADER -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_BODY
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class HeaderHolder(private val binding: HeaderCalendarViewBinding) :
        ViewHolder(binding.root) {

        fun bind(data: CalendarVerticalModel, position: Int) {
            binding.tvTitleMonth.text = data.dateStr
            setFontHeader(
                binding.tvDayMon,
                binding.tvDayTue,
                binding.tvDayWed,
                binding.tvDayThu,
                binding.tvDayFri,
                binding.tvDaySat,
                binding.tvDaySun,
                binding.tvTitleMonth
            )
        }
    }

    private fun setFontHeader(
        tvDayMon: TextView,
        tvDayThu: TextView,
        tvDayWed: TextView,
        tvDayTue: TextView,
        tvDayFri: TextView,
        tvDaySat: TextView,
        tvDaySun: TextView,
        tvTitleMonth: TextView
    ) {
        if (CodeDatePicker.fontDayHeader != -1) {
            try {
                val typefaceTitle = ResourcesCompat.getFont(context, CodeDatePicker.fontDayHeader)
                tvDayMon.typeface = typefaceTitle
                tvDayThu.typeface = typefaceTitle
                tvDayWed.typeface = typefaceTitle
                tvDayTue.typeface = typefaceTitle
                tvDayFri.typeface = typefaceTitle
                tvDaySat.typeface = typefaceTitle
                tvDaySun.typeface = typefaceTitle
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (CodeDatePicker.fontMonthHeader != -1) {
            try {
                val typefaceTitle = ResourcesCompat.getFont(context, CodeDatePicker.fontMonthHeader)
                tvTitleMonth.typeface = typefaceTitle
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    inner class BodyHolder(private val binding: BodyCalendarViewBinding) :
        ViewHolder(binding.root) {

        fun bind(data: CalendarVerticalModel, positionParent: Int) {
            val dateAdapter by lazy { DateVerticalAdapter(context, data.data) }
            val mLayoutManager = GridLayoutManager(context, 7)
            with(binding) {
                rvDate.layoutManager = mLayoutManager
                rvDate.setHasFixedSize(true)
                rvDate.adapter = dateAdapter
                rvDate.isNestedScrollingEnabled = false
            }

            dateAdapter.setOnclickListener(object : OnclickRecyclerView {
                override fun callbackRecyclerView(view: Int, position: Int) {
                    onclick.callbackRecyclerView(
                        CodeDatePicker.ONCLICK_DATE,
                        positionParent,
                        -1,
                        position
                    )
                }
            })
        }
    }

    fun callbackRecyclerView(callback: OnclickRecyclerViewParent) {
        onclick = callback
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        return itemPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.header_calendar_view
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        val headerBinding = HeaderCalendarViewBinding.bind(header)
        val tvTitleMonth = headerBinding.tvTitleMonth
        val tvDaySun = headerBinding.tvDaySun
        val tvDayMon = headerBinding.tvDayMon
        val tvDayThu = headerBinding.tvDayThu
        val tvDayWed = headerBinding.tvDayWed
        val tvDayTue = headerBinding.tvDayTue
        val tvDayFri = headerBinding.tvDayFri
        val tvDaySat = headerBinding.tvDaySat

        setFontHeader(
            tvTitleMonth,
            tvDaySun,
            tvDayMon,
            tvDayThu,
            tvDayWed,
            tvDayTue,
            tvDayFri,
            tvDaySat
        )

        tvTitleMonth.text = dataList[headerPosition].dateStr
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return dataList.get(itemPosition).typeLayout == VIEW_TYPE_HEADER
    }
}