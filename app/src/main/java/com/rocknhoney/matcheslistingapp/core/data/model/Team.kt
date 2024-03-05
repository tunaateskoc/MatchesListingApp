package com.rocknhoney.matcheslistingapp.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    @SerializedName("i") val i: Int? = null,
    @SerializedName("n") val n: String? = null,
    @SerializedName("p") val p: Int? = null,
    @SerializedName("sn") val sn: String? = null,
    @SerializedName("rc") val rc: Int? = null
) : Parcelable
