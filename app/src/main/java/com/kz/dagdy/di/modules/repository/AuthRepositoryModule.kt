package com.kz.dagdy.di.modules.repository

import com.kz.dagdy.data.repository.auth.AuthRepository
import dagger.Binds
import dagger.Module
import com.kz.dagdy.data.repository.auth.AuthRepositoryImpl
import javax.inject.Singleton

@Module
abstract class AuthRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}