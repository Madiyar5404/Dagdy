package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.qarjy.QarjyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class QarjyViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(QarjyViewModel::class)
    abstract fun bindQarjyViewModel(viewModel: QarjyViewModel): ViewModel
}