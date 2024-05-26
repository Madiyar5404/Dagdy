package com.kz.dagdy.di.contributes_android_injector.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.kz.dagdy.ui.splash.SplashFragment

@Module
abstract class SplashFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun splashFragment(): SplashFragment
}