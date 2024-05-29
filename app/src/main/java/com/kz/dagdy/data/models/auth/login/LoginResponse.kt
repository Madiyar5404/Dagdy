package com.kz.dagdy.data.models.auth.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("result")
    var token: String?
)

