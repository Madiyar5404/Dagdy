package com.kz.dagdy.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class DateTimeValue(
    val value: Date?,
    val date: String?,
    val time: String?,
    val ms: Long?
) : Parcelable