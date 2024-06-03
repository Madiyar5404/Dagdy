package com.kz.dagdy.ui.qosu.dialog.color_picker

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.kz.dagdy.R
import com.kz.dagdy.data.models.color.ColorItem
import com.kz.dagdy.databinding.DialogAddBinding
import com.kz.dagdy.ui.custom_view.piechart.color_picker.ColorAdapter
import com.kz.dagdy.ui.custom_view.piechart.color_picker.ColorPickerUtils
import com.kz.dagdy.ui.custom_view.piechart.color_picker.OnColorItemClickListener
import com.kz.dagdy.ui.custom_view.piechart.color_picker.toHSV

class AddDialog(
    private val listener: DialogListener,
    private val onListener: AddExpenseIncomeListener,
    private val colorListener: ColorListener,
    private val colorList: List<ColorItem>,
) : OnColorItemClickListener {
    private lateinit var binding: DialogAddBinding
    private lateinit var dialog: AlertDialog
    private var currentColor = MutableLiveData(Color.argb(255, 255, 0, 0))
    private var selectedColor = 0
    private val defaultColors = mutableListOf<ColorItem>()

    fun setColor(color: Int) {
        binding.apply {
            val hsv = color.toHSV()
            hueSlider.setHue(hsv[0].toInt())
            colorComposer.setColor(color)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initialize(context: Context) {
        binding = DialogAddBinding.inflate(LayoutInflater.from(context))
        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        dialog.show()

        binding.apply {

            defaultColors.addAll(colorList.map { ColorItem(it.color) }.reversed())
            defaultColors.addAll(
                mutableListOf(
                    ColorItem(context.getColor(R.color.goldenrod)),
                    ColorItem(context.getColor(R.color.vanilla)),
                    ColorItem(context.getColor(R.color.turquoise)),
                    ColorItem(context.getColor(R.color.pale)),
                    ColorItem(context.getColor(R.color.dusty)),
                    ColorItem(context.getColor(R.color.misty)),
                    ColorItem(context.getColor(R.color.hunter))
                )
            )

            val colorAdapter = ColorAdapter(
                defaultColors.map { it.color } as MutableList<Int>,
                this@AddDialog
            )

            rvColorPicker.adapter = colorAdapter
            rvColorPicker.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            setColor(currentColor.value!!)

            hueSlider.setOnChangeListener {
                colorComposer.setComposeColor(hueSlider.getColor())
            }

            colorComposer.setColorChangeListener {
                val alpha = Color.alpha(currentColor.value!!)
                val newColor = ColorPickerUtils.colorWithAlpha(alpha, colorComposer.getColor())
                currentColor.value = newColor
            }

            currentColor.observeForever { color ->
                color?.let {
                    binding.colorView.setColor(it)
                }
            }
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun showDialog(context: Context) {
        initialize(context)
        dialog.show()
    }

    override fun onColorItemClick(color: Int, isSelected: Boolean) {
        if (!isSelected) {
            selectedColor = color
        } else {
            selectedColor = 0
        }
    }
}