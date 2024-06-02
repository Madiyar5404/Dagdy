package com.kz.dagdy.ui.qarjy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.R
import com.kz.dagdy.data.enums.app.SortedTypeDateEnums
import com.kz.dagdy.data.models.dialog.selection_sorted_date.SelectionDate
import javax.inject.Inject

class QarjyViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {
    // Selection sorted date
    private var _selectionTypeDateList = emptyList<SelectionDate>()
    val selectionTypeDateList: List<SelectionDate>
        get() = _selectionTypeDateList

    private val _selectedTypeDate = MutableLiveData<SelectionDate>()
    val selectedTypeDate: LiveData<SelectionDate>
        get() = _selectedTypeDate

    private fun initTypeDateList() {
        val temp = mutableListOf<SelectionDate>()

        temp.add(
            SelectionDate(
                id = SortedTypeDateEnums.MONTH.id,
                image = R.drawable.ic_aiyna,
                title = "Aiyna"
            )
        )
        temp.add(
            SelectionDate(
                id = SortedTypeDateEnums.WEEK.id,
                image = R.drawable.ic_aptasyna,
                title = "Aptasyna"
            )
        )
        temp.add(
            SelectionDate(
                id = SortedTypeDateEnums.DAY.id,
                image = R.drawable.ic_kunine,
                title = "Künıne"
            )
        )
        temp.add(
            SelectionDate(
                id = SortedTypeDateEnums.INTERVAL.id,
                image = R.drawable.ic_period_aralyk,
                title = "Period aralyq"
            )
        )
        _selectionTypeDateList = temp
        _selectedTypeDate.value = temp.first()
    }

    fun getOrganization(): List<SelectionDate> {
        val temp = mutableListOf<SelectionDate>()
        _selectionTypeDateList.forEachIndexed { _, it ->
            val selected = (it.id == _selectedTypeDate.value?.id)
            temp.add(
                it.copy(
                    selected = selected
                )
            )
        }
        return temp
    }

    fun setOrganization(item: SelectionDate) {
        _selectedTypeDate.value = item
    }

    init {
        initTypeDateList()
    }
}