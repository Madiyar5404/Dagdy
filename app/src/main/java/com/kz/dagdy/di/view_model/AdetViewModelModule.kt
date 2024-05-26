package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.adet.AdetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class AdetViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(AdetViewModel::class)
    abstract fun bindAdetViewModel(viewModel: AdetViewModel): ViewModel
}