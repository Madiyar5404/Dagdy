package com.kz.dagdy.ui.baptau

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class BaptauViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {

}