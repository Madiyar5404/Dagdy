package com.kz.dagdy.di.modules

import dagger.Module
import com.kz.dagdy.di.modules.api.AuthApiModule

@Module(
    includes = [
        AuthApiModule::class
    ]
)
class ApiModule {
}