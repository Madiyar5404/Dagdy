package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.qosu.QosuViewModel
import com.kz.dagdy.ui.qosu.adet_qosu.AdetQosuViewModel
import com.kz.dagdy.ui.qosu.jazba_qosu.JazbaQosuViewModel
import com.kz.dagdy.ui.qosu.qarjy_qosu.QarjyQosuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class QosuViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(QosuViewModel::class)
    abstract fun bindQosuViewModel(viewModel: QosuViewModel): ViewModel

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(QarjyQosuViewModel::class)
    abstract fun bindQarjyQosuViewModel(viewModel: QarjyQosuViewModel): ViewModel

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(AdetQosuViewModel::class)
    abstract fun bindAdetQosuViewModel(viewModel: AdetQosuViewModel): ViewModel

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(JazbaQosuViewModel::class)
    abstract fun bindJazbaQosuViewModel(viewModel: JazbaQosuViewModel): ViewModel
}