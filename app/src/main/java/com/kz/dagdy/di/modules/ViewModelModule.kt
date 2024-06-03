package com.kz.dagdy.di.modules

import dagger.Module
import com.kz.dagdy.di.view_model.ActivityViewModelModule
import com.kz.dagdy.di.view_model.AdetViewModelModule
import com.kz.dagdy.di.view_model.AuthenticationViewModelModule
import com.kz.dagdy.di.view_model.BaptauViewModelModule
import com.kz.dagdy.di.view_model.CalendarViewModelModule
import com.kz.dagdy.di.view_model.DialogViewModelModule
import com.kz.dagdy.di.view_model.JazbaViewModelModule
import com.kz.dagdy.di.view_model.QarjyViewModelModule
import com.kz.dagdy.di.view_model.QosuViewModelModule
import com.kz.dagdy.di.view_model.SplashViewModelModule

@Module(
    includes = [
        ActivityViewModelModule::class,
        SplashViewModelModule::class,
        AuthenticationViewModelModule::class,
        QarjyViewModelModule::class,
        AdetViewModelModule::class,
        JazbaViewModelModule::class,
        BaptauViewModelModule::class,
        QosuViewModelModule::class,
        DialogViewModelModule::class,
        CalendarViewModelModule::class
    ]
)
class ViewModelModule {
}