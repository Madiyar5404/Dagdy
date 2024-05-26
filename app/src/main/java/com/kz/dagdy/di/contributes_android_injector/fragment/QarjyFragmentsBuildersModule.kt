package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.qarjy.QarjyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QarjyFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun qarjyFragment(): QarjyFragment
}