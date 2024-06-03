package com.kz.dagdy.ui.qosu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.R
import com.kz.dagdy.ui_common.callbacks.AppBarButtonsClickCallback
import com.kz.dagdy.utils.live_data.Event
import javax.inject.Inject

class QosuViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {
    val titleFieldText = MutableLiveData<Any>()

    init {
        titleFieldText.postValue(app.getString(R.string.qosu))
    }

    val appBarButtonsClickCallback = object :
        AppBarButtonsClickCallback {

        override fun onBackButtonClick() {
            _popBackStack.postValue(Event(Unit))
        }

    }

    private val _popBackStack = MutableLiveData<Event<Unit>>()
    val popBackStack: LiveData<Event<Unit>>
        get() = _popBackStack
}