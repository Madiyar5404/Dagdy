package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.calendar.single.CalendarSingleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalendarFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun calendarSingleFragment(): CalendarSingleFragment
}