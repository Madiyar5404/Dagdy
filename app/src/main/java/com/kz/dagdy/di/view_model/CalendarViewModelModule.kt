package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.ui.calendar.single.CalendarSingleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class CalendarViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(CalendarSingleViewModel::class)
    abstract fun bindCalendarSingleViewModel(viewModel: CalendarSingleViewModel): ViewModel
}