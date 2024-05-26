package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.welcome.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WelcomeFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun welcomeFragment(): WelcomeFragment
}