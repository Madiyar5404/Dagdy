package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.jazba.JazbaViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class JazbaViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(JazbaViewModel::class)
    abstract fun bindJazbaViewModel(viewModel: JazbaViewModel): ViewModel
}