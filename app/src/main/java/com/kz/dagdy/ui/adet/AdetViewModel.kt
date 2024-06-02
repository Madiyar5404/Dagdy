package com.kz.dagdy.ui.adet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.R
import com.kz.dagdy.data.enums.app.SortedTypeDateEnums
import com.kz.dagdy.data.models.dialog.selection_sorted_date.SelectionDate
import javax.inject.Inject

class AdetViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {

}