package com.kz.dagdy.ui.activities.authorized

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.utils.live_data.Event
import javax.inject.Inject

class AuthorizedViewModel
@Inject constructor(
    private val app: Application
) : AndroidViewModel(app) {

    private val _openQosu = MutableLiveData<Event<Unit>>()
    val openQosu: LiveData<Event<Unit>>
        get() = _openQosu

    fun onQosuBtnClick() {
        _openQosu.postValue(Event(Unit))
    }
}