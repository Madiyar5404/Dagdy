package com.kz.dagdy.di.components

import android.app.Application
import com.kz.dagdy.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.kz.dagdy.di.contributes_android_injector.activity.ActivityBuildersModule
import com.kz.dagdy.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}