package com.kz.dagdy.data.repository.auth

import androidx.lifecycle.LiveData
import com.kz.dagdy.data.models.auth.login.LoginRequest
import com.kz.dagdy.data.models.auth.login.LoginResponse
import com.kz.dagdy.data.models.auth.registration.RegisterResponse
import com.kz.dagdy.data.models.auth.registration.RegistrationRequest
import com.kz.dagdy.data.models.network.Resource
import com.kz.dagdy.utils.live_data.Event

interface AuthRepository {
    fun register(body: RegistrationRequest): LiveData<Event<Resource<RegisterResponse>>>
    fun login(body: LoginRequest): LiveData<Event<Resource<LoginResponse>>>
}