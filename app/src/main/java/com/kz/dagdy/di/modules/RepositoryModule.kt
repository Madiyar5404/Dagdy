package com.kz.dagdy.di.modules

import dagger.Module
import com.kz.dagdy.di.modules.repository.AuthRepositoryModule

@Module(
    includes = [
        AuthRepositoryModule::class
    ]
)
class RepositoryModule {
}