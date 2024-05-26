package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.auth.login.LoginFragment
import com.kz.dagdy.ui.auth.registration.RegistrationFragment
import com.kz.dagdy.ui.auth.reset_password.ResetPasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UnauthorizedFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun registrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    internal abstract fun resetPasswordFragment(): ResetPasswordFragment
}