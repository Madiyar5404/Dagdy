package com.kz.dagdy.network.api

import com.kz.dagdy.data.models.auth.login.LoginRequest
import com.kz.dagdy.data.models.auth.login.LoginResponse
import com.kz.dagdy.data.models.auth.registration.RegisterResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface AuthApi {

    @POST("signup")
    fun register(
        @QueryMap map: Map<String, String>
    ): Single<RegisterResponse>

    @POST("signin")
    fun login(
        @Body body: LoginRequest
    ): Single<LoginResponse>
}