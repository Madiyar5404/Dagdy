package com.kz.dagdy.ui.qosu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class QosuViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {

}