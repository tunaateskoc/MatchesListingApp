package com.rocknhoney.matcheslistingapp.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    @SerializedName("i") val i: Int? = null,
    @SerializedName("sgi") val sgi: Int? = null,
    @SerializedName("d") val d: Long? = null,
    @SerializedName("st") val st: String? = null,
    @SerializedName("bri") val bri: Int? = null,
    @SerializedName("ht") val ht: Team? = null,
    @SerializedName("at") val at: Team? = null,
    @SerializedName("sc") val sc: Score? = null,
    @SerializedName("to") val to: League? = null,
    @SerializedName("v") val v: String? = null
) : Parcelable {
    fun toMatchViewEntity() = MatchViewEntity(
        i = i,
        sgi = sgi,
        d = d,
        st = st,
        bri = bri,
        ht = ht,
        at = at,
        sc = sc,
        to = to,
        v = v,
        isFavourite = false
    )
}
