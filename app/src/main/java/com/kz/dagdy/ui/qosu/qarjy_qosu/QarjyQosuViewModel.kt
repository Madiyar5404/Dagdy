package com.kz.dagdy.ui.qosu.qarjy_qosu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.R
import com.kz.dagdy.data.enums.app.TransactionTypeEnums
import com.kz.dagdy.data.models.dialog.selection_type_transaction.Transaction
import com.kz.dagdy.utils.live_data.Event
import javax.inject.Inject

class QarjyQosuViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {
    fun onCalendarPage() {
        _openCalendar.postValue(Event(Unit))
    }

    private val _openCalendar = MutableLiveData<Event<Unit>>()
    val openCalendar: LiveData<Event<Unit>>
        get() = _openCalendar

    private var _transactionTypeList = emptyList<Transaction>()
    val transactionTypeList: List<Transaction>
        get() = _transactionTypeList

    private val _selectedTypeTransaction = MutableLiveData<Transaction>()
    val selectedTypeTransaction: LiveData<Transaction>
        get() = _selectedTypeTransaction

    private fun initTypeTransactionList() {
        val temp = mutableListOf<Transaction>()

        temp.add(
            Transaction(
                id = TransactionTypeEnums.AQŞALAİ.id,
                image = R.drawable.ic_akshalai,
                title = "Aqşalai"
            )
        )
        temp.add(
            Transaction(
                id = TransactionTypeEnums.KARTAMEN.id,
                image = R.drawable.ic_card,
                title = "Kartamen"
            )
        )
        _transactionTypeList = temp
        _selectedTypeTransaction.value = temp.first()
    }

    fun getOrganization(): List<Transaction> {
        val temp = mutableListOf<Transaction>()
        _transactionTypeList.forEachIndexed { _, it ->
            val selected = (it.id == _selectedTypeTransaction.value?.id)
            temp.add(
                it.copy(
                    selected = selected
                )
            )
        }
        return temp
    }

    fun setTransactionType(item: Transaction) {
        _selectedTypeTransaction.value = item
    }

    init {
        initTypeTransactionList()
    }

}