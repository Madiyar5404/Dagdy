package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.di.view_model.base.ViewModelKey
import com.kz.dagdy.ui.activities.authorized.AuthorizedViewModel
import com.kz.dagdy.ui.activities.splash.SplashViewModel
import com.kz.dagdy.ui.activities.unauthorized.UnauthorizedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindActivitySplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizedViewModel::class)
    abstract fun bindActivityAuthorizationViewModel(viewModel: AuthorizedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnauthorizedViewModel::class)
    abstract fun bindActivityUnauthorizedViewModel(viewModel: UnauthorizedViewModel): ViewModel

}