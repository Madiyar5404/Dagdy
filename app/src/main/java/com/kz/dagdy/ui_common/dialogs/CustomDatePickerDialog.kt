package com.kz.dagdy.ui_common.dialogs

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.kz.dagdy.R
import java.util.*

class CustomDatePickerDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var currentDate: Date? = null
    private var minDate: Date? = null
    private var maxDate: Date? = null

    companion object {
        fun newInstance(
            currentDate: Date? = null,
            minDate: Date? = null,
            maxDate: Date? = null
        ): CustomDatePickerDialog {
            val dialog = CustomDatePickerDialog()
            dialog.currentDate = currentDate
            dialog.minDate = minDate
            dialog.maxDate = maxDate
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        if (currentDate != null) {
            calendar.time = currentDate
        }

        val yy = calendar.get(Calendar.YEAR)
        val mm = calendar.get(Calendar.MONTH)
        val dd = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(), R.style.DatePickerDialogTheme, this, yy, mm, dd
        )

        if (minDate != null) {
            datePickerDialog.datePicker.minDate = minDate!!.time
        }

        if (maxDate != null) {
            val cal = Calendar.getInstance()
            cal.time = maxDate
            cal.add(Calendar.DATE, 1)
            datePickerDialog.datePicker.maxDate = cal.timeInMillis
        }

        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val date = calendar.time

        val bundle = Bundle()
        bundle.putSerializable("date", date)

        setActivityResult(bundle)
        dismiss()
    }

    private fun setActivityResult(bundle: Bundle) {
        val intent = Intent()
        intent.putExtras(bundle)

        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }

}
