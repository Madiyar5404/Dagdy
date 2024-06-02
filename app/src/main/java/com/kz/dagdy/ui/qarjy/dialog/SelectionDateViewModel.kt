package com.kz.dagdy.ui.qarjy.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.data.models.dialog.selection_sorted_date.SelectionDate
import com.kz.dagdy.ui_common.callbacks.RecyclerViewItemClickCallback
import com.kz.dagdy.utils.live_data.Event
import javax.inject.Inject

class SelectionDateViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {

    private val _list = MutableLiveData<List<Any>>()
    val list: LiveData<List<Any>>
        get() = _list

    private val _selectedDate = MutableLiveData<Any>()
    val selectedDate: LiveData<Any>
        get() = _selectedDate

    private val _selectedItem = MutableLiveData<Any>()
    val selectedItem: LiveData<Any>
        get() = _selectedItem

    fun setList(list: List<Any>) {
        val temp = mutableListOf<Any>()

        if (list.isNotEmpty()) {
            temp.addAll(list)
            getOrganizationTitle(list)
        }

        _list.postValue(temp)
    }

    private val _dismiss = MutableLiveData<Event<Unit>>()
    val dismiss: LiveData<Event<Unit>>
        get() = _dismiss

    val onRecyclerViewItemClickListener = object : RecyclerViewItemClickCallback {
        override fun onRecyclerViewItemClick(any: Any) {
            _selectedItem.value = any
            _dismiss.postValue(Event(Unit))
        }
    }

    private fun getOrganizationTitle(listOrganization: List<Any>) {
        listOrganization.forEach {
            if (it is SelectionDate) {
                if (it.selected) {
                    _selectedDate.postValue(it)
                }
            }
        }
    }
}
