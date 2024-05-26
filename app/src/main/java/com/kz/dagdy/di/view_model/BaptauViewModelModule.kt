package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.baptau.BaptauViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class BaptauViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(BaptauViewModel::class)
    abstract fun bindBaptauViewModel(viewModel: BaptauViewModel): ViewModel
}