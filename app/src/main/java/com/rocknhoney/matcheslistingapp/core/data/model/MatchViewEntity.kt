package com.rocknhoney.matcheslistingapp.core.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rocknhoney.matcheslistingapp.core.data.roomdb.converter.LeagueConverter
import com.rocknhoney.matcheslistingapp.core.data.roomdb.converter.ScoreConverter
import com.rocknhoney.matcheslistingapp.core.data.roomdb.converter.TeamConverter
import kotlinx.parcelize.Parcelize

/**
 * Represents a match entity that is ready to be used in the view layer.
 **/
@Entity(tableName = "Match")
@TypeConverters(*[TeamConverter::class, ScoreConverter::class, LeagueConverter::class])
@Parcelize
data class MatchViewEntity(
    @PrimaryKey @ColumnInfo(name = "i") val i: Int? = null,
    @ColumnInfo(name = "sgi") val sgi: Int? = null,
    @ColumnInfo(name = "d") val d: Long? = null,
    @ColumnInfo(name = "st") val st: String? = null,
    @ColumnInfo(name = "bri") val bri: Int? = null,
    @ColumnInfo(name = "ht") val ht: Team? = null,
    @ColumnInfo(name = "at") val at: Team? = null,
    @ColumnInfo(name = "sc") val sc: Score? = null,
    @ColumnInfo(name = "to") val to: League? = null,
    @ColumnInfo(name = "v") val v: String? = null,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean? = null
) : Parcelable