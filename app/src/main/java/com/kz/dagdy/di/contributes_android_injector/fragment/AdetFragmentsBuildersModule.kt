package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.adet.AdetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AdetFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun adetFragment(): AdetFragment
}