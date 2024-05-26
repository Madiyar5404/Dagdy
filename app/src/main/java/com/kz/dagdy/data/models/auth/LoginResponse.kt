package com.kz.dagdy.data.models.auth

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("status")
    var status: String?,
    @SerializedName("tokenM")
    val access_token: String?,
    val token_type: String?,
    @SerializedName("error_description")
    val error_description: String?
) : Parcelable

