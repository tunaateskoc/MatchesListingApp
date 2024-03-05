package com.rocknhoney.matcheslistingapp.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Half(
    @SerializedName("r") val r: Int? = null,
    @SerializedName("c") val c: Int? = null,
    @SerializedName("ht") val ht: Int? = null
) : Parcelable
