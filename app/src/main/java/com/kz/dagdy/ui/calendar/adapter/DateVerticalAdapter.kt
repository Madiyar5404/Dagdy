package com.kz.dagdy.ui.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.kz.dagdy.R
import com.kz.dagdy.databinding.ItemDateVerticalBinding
import com.kz.dagdy.ui.calendar.callback.OnclickRecyclerView
import com.kz.dagdy.ui.calendar.model.DayDataVerticalModel
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar

class DateVerticalAdapter(var context: Context, var items: ArrayList<DayDataVerticalModel>) :
    RecyclerView.Adapter<DateVerticalAdapter.ViewHolder>() {

    lateinit var onclick: OnclickRecyclerView

    class ViewHolder(val binding: ItemDateVerticalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDateVerticalBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    fun setOnclickListener(onclickListenerRecyclerView: OnclickRecyclerView) {
        this.onclick = onclickListenerRecyclerView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        val binding = holder.binding

        binding.tvItemDate.text = data.day.toString()
        if (data.date.before(Calendar.getInstance().time)) {
            binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayActive))
        } else {
            binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayNoActive))
            holder.itemView.setOnClickListener(null)
        }

        if (CodeDatePicker.fontDay != -1) {
            try {
                val typefaceTitle = ResourcesCompat.getFont(context, CodeDatePicker.fontDay)
                binding.tvItemDate.setTypeface(typefaceTitle)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (data.date.after(Calendar.getInstance().time)) {
            binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayNoActive))
        } else if (data.date.after(CodeDatePicker.maxDate)) {
            binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayNoActive))
        } else {
            if (data.typeDay == CodeDatePicker.DAY_NEXT_MONTH || data.typeDay == CodeDatePicker.DAY_PREVIOUS_MONTH) {
                binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayNoActive))
            } else if (data.typeDay == CodeDatePicker.DAY_SUNDAY) {
                binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorTextHoliday))
            } else {
                if (CodeDatePicker.startSelectDate.isNotEmpty()) {
                    if (data.fullDay == CodeDatePicker.startSelectDate) {
                        binding.tvItemDate.setTextColor(context.resources.getColor(R.color.white))
                        binding.tvItemDate.background = ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.circle_black_background,
                            null
                        )
                    } else {
                        if (CodeDatePicker.TYPE_SELECTED == CodeDatePicker.SINGGLE_SELECTED) {
                            binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayActive))
                        } else {
                            if (CodeDatePicker.endSelectDate.isNotEmpty()) {
                                if (data.date.after(
                                        SimpleDateFormat("dd-MM-yyyy").parse(
                                            CodeDatePicker.startSelectDate
                                        )
                                    ) && data.date.before(
                                        SimpleDateFormat("dd-MM-yyyy").parse(
                                            CodeDatePicker.endSelectDate
                                        )
                                    )
                                ) {
                                    binding.tvItemDate.setTextColor(
                                        context.resources.getColor(
                                            R.color.white
                                        )
                                    )

                                    binding.tvItemDate.background = ResourcesCompat.getDrawable(
                                        context.resources,
                                        R.drawable.circle_black_background,
                                        null
                                    )
                                } else if (data.fullDay == CodeDatePicker.endSelectDate) {
                                    binding.tvItemDate.setTextColor(
                                        context.resources.getColor(
                                            R.color.white
                                        )
                                    )
                                    binding.tvItemDate.background = ResourcesCompat.getDrawable(
                                        context.resources,
                                        R.drawable.circle_black_background,
                                        null
                                    )
                                } else {
                                    binding.tvItemDate.setTextColor(
                                        context.resources.getColor(
                                            R.color.colorDayActive
                                        )
                                    )
                                }
                            } else {
                                binding.tvItemDate.setTextColor(
                                    context.resources.getColor(
                                        R.color.colorDayActive
                                    )
                                )
                            }
                        }
                    }
                } else {
                    binding.tvItemDate.setTextColor(context.resources.getColor(R.color.colorDayActive))
                }
            }

            holder.itemView.setOnClickListener {
                onclick.callbackRecyclerView(-1, position)
            }
        }
    }
}