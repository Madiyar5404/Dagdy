package com.kz.dagdy.data.models.dialog.selection_sorted_date

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectionDate(
    val id: String,
    @DrawableRes val image: Int?,
    val title: String?,
    var selected: Boolean = false
) : Parcelable
