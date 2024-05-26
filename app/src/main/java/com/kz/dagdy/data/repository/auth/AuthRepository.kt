package com.kz.dagdy.data.repository.auth

import androidx.lifecycle.LiveData
import com.kz.dagdy.data.models.auth.LoginResponse
import com.kz.dagdy.data.models.network.Resource
import com.kz.dagdy.utils.live_data.Event

interface AuthRepository {

    fun loginUser(email: String, password: String): LiveData<Event<Resource<LoginResponse>>>
}