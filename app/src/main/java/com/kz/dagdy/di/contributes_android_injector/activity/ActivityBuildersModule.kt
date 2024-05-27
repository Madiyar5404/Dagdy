package com.kz.dagdy.di.contributes_android_injector.activity

import com.kz.dagdy.di.contributes_android_injector.fragment.AdetFragmentsBuildersModule
import com.kz.dagdy.di.contributes_android_injector.fragment.BaptauFragmentsBuildersModule
import com.kz.dagdy.di.contributes_android_injector.fragment.JazbaFragmentsBuildersModule
import com.kz.dagdy.di.contributes_android_injector.fragment.QarjyFragmentsBuildersModule
import com.kz.dagdy.ui.activities.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.kz.dagdy.di.contributes_android_injector.fragment.SplashFragmentsBuildersModule
import com.kz.dagdy.di.contributes_android_injector.fragment.UnauthorizedFragmentsBuildersModule
import com.kz.dagdy.ui.activities.authorized.AuthorizedActivity
import com.kz.dagdy.ui.activities.unauthorized.UnauthorizedActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            SplashFragmentsBuildersModule::class
        ]
    )
    internal abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(
        modules = [
            UnauthorizedFragmentsBuildersModule::class
        ]
    )
    internal abstract fun unauthorizedActivity(): UnauthorizedActivity

    @ContributesAndroidInjector(
        modules = [
            QarjyFragmentsBuildersModule::class,
            AdetFragmentsBuildersModule::class,
            JazbaFragmentsBuildersModule::class,
            BaptauFragmentsBuildersModule::class
        ]
    )
    internal abstract fun authorizedActivity(): AuthorizedActivity

}