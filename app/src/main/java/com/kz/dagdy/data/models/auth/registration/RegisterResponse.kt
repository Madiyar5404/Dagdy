package com.kz.dagdy.data.models.auth.registration

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(
    @SerializedName("result")
    var token: String?,

) : Parcelable
