package com.kz.dagdy.ui.jazba

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class JazbaViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {

}