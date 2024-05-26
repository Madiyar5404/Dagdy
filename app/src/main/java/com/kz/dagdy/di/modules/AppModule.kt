package com.kz.dagdy.di.modules

import dagger.Module
import com.kz.dagdy.di.modules.common.NetworkModule
import com.kz.dagdy.di.modules.common.PreferenceModule
import com.kz.dagdy.di.modules.common.UtilsModule
import com.kz.dagdy.di.view_model.base.ViewModelFactoryModule

@Module(
    includes = [
        NetworkModule::class,
        PreferenceModule::class,
        UtilsModule::class,

        //api
        ApiModule::class,

        //repository
        RepositoryModule::class,

        //
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
class AppModule {
}