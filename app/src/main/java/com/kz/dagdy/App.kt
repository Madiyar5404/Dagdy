package com.kz.dagdy

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.kz.dagdy.data.app_lifecycle.ApplicationObserver
import com.kz.dagdy.di.utils.AppInjector
import com.kz.dagdy.utils.locale.LocaleUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var lifecycleListener: ApplicationObserver

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        ProcessLifecycleOwner.get()
            .lifecycle
            .addObserver(lifecycleListener)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(base)
        super.attachBaseContext(LocaleUtils.setLocale(base))
        MultiDex.install(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }

}