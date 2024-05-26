package com.kz.dagdy.network.api

import com.kz.dagdy.data.models.auth.LoginResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface AuthApi {

    @POST("login")
    fun login(@QueryMap map: Map<String, String>): Single<LoginResponse>

    companion object {
        const val EMAIL_KEY = "login"
        const val PASSWORD_KEY = "password"
    }
}