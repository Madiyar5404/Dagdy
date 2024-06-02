package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.qosu.QosuFragment
import com.kz.dagdy.ui.qosu.adet_qosu.AdetQosuFragment
import com.kz.dagdy.ui.qosu.jazba_qosu.JazbaQosuFragment
import com.kz.dagdy.ui.qosu.qarjy_qosu.QarjyQosuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QosuFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun qosuFragment(): QosuFragment

    @ContributesAndroidInjector
    internal abstract fun qarjyFragment(): QarjyQosuFragment

    @ContributesAndroidInjector
    internal abstract fun adetFragment(): AdetQosuFragment

    @ContributesAndroidInjector
    internal abstract fun jazbaFragment(): JazbaQosuFragment
}