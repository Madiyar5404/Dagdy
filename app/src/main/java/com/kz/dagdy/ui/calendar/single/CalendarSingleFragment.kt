package com.kz.dagdy.ui.calendar.single

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentCalendarSingleBinding
import com.kz.dagdy.ui.calendar.utils.CallbackCalendarVertical
import com.kz.dagdy.ui.calendar.utils.CodeDatePicker
import com.kz.dagdy.ui.calendar.utils.TimeUtils
import com.kz.dagdy.ui_common.base.BaseFragment

class CalendarSingleFragment : BaseFragment() {

    private lateinit var binding: FragmentCalendarSingleBinding
    private lateinit var viewModel: CalendarSingleViewModel

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_calendar_single, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(CalendarSingleViewModel::class.java)
        binding.viewModel = viewModel

        binding.calendarViewVertical.typeSelected(CodeDatePicker.SINGGLE_SELECTED) // or CodeDatePicker.DOUBLE_SELECTED
        binding.calendarViewVertical.callbackCalendarListener(object : CallbackCalendarVertical {
            override fun startDate(string: String) {
                selectedDate = string
                showTimePickerDialog()
            }

            override fun endDate(string: String) {

            }
        })

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.apply {
            popBackStack.observe(
                viewLifecycleOwner,
                Observer {
                    it.getContentIfNotHandled()?.let {
                        findNavController().popBackStack()
                    }
                }
            )
        }
    }

    private fun showTimePickerDialog() {
        TimeUtils.showTimePickerDialog(this) { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
            selectedTime = TimeUtils.formatTime(selectedHour, selectedMinute)
        }
    }

    companion object {
        fun newInstance(): CalendarSingleFragment {
            return CalendarSingleFragment()
        }
    }
}