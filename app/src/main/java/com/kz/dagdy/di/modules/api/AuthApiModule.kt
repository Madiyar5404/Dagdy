package com.kz.dagdy.di.modules.api

import dagger.Module
import dagger.Provides
import com.kz.dagdy.network.api.AuthApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AuthApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}