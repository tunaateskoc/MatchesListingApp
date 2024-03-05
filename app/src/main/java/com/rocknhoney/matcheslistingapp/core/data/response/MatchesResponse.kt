package com.rocknhoney.matcheslistingapp.core.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rocknhoney.matcheslistingapp.core.data.model.Match
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchesResponse(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("data") val data: List<Match?>? = null
) : Parcelable