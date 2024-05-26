package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.baptau.BaptauFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BaptauFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun baptauFragment(): BaptauFragment
}