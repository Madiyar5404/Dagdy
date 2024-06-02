package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.qarjy.QarjyFragment
import com.kz.dagdy.ui.qarjy.barlygy.BarlygyFragment
import com.kz.dagdy.ui.qarjy.kiris.KirisFragment
import com.kz.dagdy.ui.qarjy.shygys.ShygysFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QarjyFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun qarjyFragment(): QarjyFragment

    @ContributesAndroidInjector
    internal abstract fun kirisFragment(): KirisFragment

    @ContributesAndroidInjector
    internal abstract fun barlygyFragment(): BarlygyFragment

    @ContributesAndroidInjector
    internal abstract fun shygysFragment(): ShygysFragment
}