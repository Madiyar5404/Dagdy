package com.kz.dagdy.ui.qosu.dialog.color_picker

interface AddExpenseIncomeListener {
    fun onAddClicked(title: String, color: Int)
    fun onDeleteClicked(title: String)
    fun onEditClicked(titleOld: String, titleNew: String)
}
