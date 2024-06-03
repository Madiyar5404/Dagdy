package com.kz.dagdy.ui.custom_view.piechart.color_picker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kz.dagdy.R
import com.kz.dagdy.ui_common.extension.setSafeOnClickListener

class ColorAdapter(
    private val colors: MutableList<Int>,
    private val colorItemClickListener: OnColorItemClickListener
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_color_picker, parent, false)
        return ColorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]
        holder.bind(color, position == selectedPosition)
    }

    override fun getItemCount(): Int = colors.size

    fun addColor(color: Int) {
        colors.add(0, color)
        notifyDataSetChanged()
    }

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorPicker: View = itemView.findViewById(R.id.color_picker)
        val colorPickerBack: View = itemView.findViewById(R.id.color_picker_back)
        val colorPickerCircle: View = itemView.findViewById(R.id.color_picker_circle)

        fun bind(color: Int, isSelected: Boolean) {
            colorPicker.setBackgroundColor(color)

            if (isSelected) {
                colorPickerBack.isVisible = isSelected
                colorPickerCircle.isVisible = isSelected
            } else {
                colorPickerBack.isVisible = isSelected
                colorPickerCircle.isVisible = isSelected
            }

            colorPicker.setSafeOnClickListener {
                if (selectedPosition == adapterPosition) {
                    selectedPosition = RecyclerView.NO_POSITION
                } else {
                    selectedPosition = adapterPosition
                }
                notifyDataSetChanged()
                colorItemClickListener.onColorItemClick(color, isSelected)
            }
        }
    }
}