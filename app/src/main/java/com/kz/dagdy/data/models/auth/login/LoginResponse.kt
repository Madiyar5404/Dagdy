package com.kz.dagdy.data.models.auth.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("result")
    var token: String?,
) : Parcelable

