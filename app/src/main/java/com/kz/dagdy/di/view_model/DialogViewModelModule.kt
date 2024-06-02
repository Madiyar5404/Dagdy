package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.di.view_model.base.ViewModelKey
import com.kz.dagdy.ui.qarjy.dialog.SelectionDateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class DialogViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectionDateViewModel::class)
    abstract fun bindSelectionDateViewModel(viewModel: SelectionDateViewModel): ViewModel

}