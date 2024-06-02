package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.qarjy.QarjyViewModel
import com.kz.dagdy.ui.qarjy.barlygy.BarlygyViewModel
import com.kz.dagdy.ui.qarjy.kiris.KirisViewModel
import com.kz.dagdy.ui.qarjy.shygys.ShygysViewModel
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

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(KirisViewModel::class)
    abstract fun bindKirisViewModel(viewModel: KirisViewModel): ViewModel

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(ShygysViewModel::class)
    abstract fun bindShygysViewModel(viewModel: ShygysViewModel): ViewModel

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(BarlygyViewModel::class)
    abstract fun bindBarlygyViewModel(viewModel: BarlygyViewModel): ViewModel
}