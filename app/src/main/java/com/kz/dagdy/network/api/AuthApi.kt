package com.kz.dagdy.network.api

import com.kz.dagdy.data.models.auth.login.LoginRequest
import com.kz.dagdy.data.models.auth.login.LoginResponse
import com.kz.dagdy.data.models.auth.registration.RegisterResponse
import com.kz.dagdy.data.models.auth.registration.RegistrationRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("signup")
    fun register(
        @Body body: RegistrationRequest
    ): Single<RegisterResponse>

    @POST("signin")
    fun login(
        @Body body: LoginRequest
    ): Single<LoginResponse>
}