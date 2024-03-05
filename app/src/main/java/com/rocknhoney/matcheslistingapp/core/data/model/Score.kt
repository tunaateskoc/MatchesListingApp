package com.rocknhoney.matcheslistingapp.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Score(
    @SerializedName("st") val st: Int? = null,
    @SerializedName("abbr") val abbr: String? = null,
    @SerializedName("ht") val ht: Half? = null,
    @SerializedName("at") val at: Half? = null
) : Parcelable
