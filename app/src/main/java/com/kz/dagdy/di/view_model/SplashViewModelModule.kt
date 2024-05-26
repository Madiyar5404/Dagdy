package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.kz.dagdy.ui.splash.SplashViewModel
import com.kz.dagdy.ui.welcome.WelcomeViewModel

@Suppress("unused")
@Module
abstract class SplashViewModelModule {

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @com.kz.dagdy.di.view_model.base.ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(viewModel: WelcomeViewModel): ViewModel
}