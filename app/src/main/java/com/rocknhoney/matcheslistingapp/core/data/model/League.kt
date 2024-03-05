package com.rocknhoney.matcheslistingapp.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    @SerializedName("i") val i: Int? = null,
    @SerializedName("n") val n: String? = null,
    @SerializedName("sn") val sn: String? = null,
    @SerializedName("p") val p: Int? = null,
    @SerializedName("flag") val flag: String? = null,
) : Parcelable