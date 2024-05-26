package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.jazba.JazbaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class JazbaFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun jazbaFragment(): JazbaFragment
}