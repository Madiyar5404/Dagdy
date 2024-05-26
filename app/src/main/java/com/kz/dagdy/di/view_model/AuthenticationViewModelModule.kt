package com.kz.dagdy.di.view_model

import androidx.lifecycle.ViewModel
import com.kz.dagdy.di.view_model.base.ViewModelKey
import com.kz.dagdy.ui.auth.login.LoginViewModel
import com.kz.dagdy.ui.auth.registration.RegistrationViewModel
import com.kz.dagdy.ui.auth.reset_password.ResetPasswordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class AuthenticationViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun bindResetPasswordViewModel(viewModel: ResetPasswordViewModel): ViewModel
}