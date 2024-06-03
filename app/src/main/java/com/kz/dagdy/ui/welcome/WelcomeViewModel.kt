package com.kz.dagdy.ui.welcome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class WelcomeViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {

}