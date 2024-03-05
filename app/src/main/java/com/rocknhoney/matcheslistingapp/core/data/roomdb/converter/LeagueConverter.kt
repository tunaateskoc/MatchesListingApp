package com.rocknhoney.matcheslistingapp.core.data.roomdb.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rocknhoney.matcheslistingapp.core.data.model.League

/**
 * Type converter for converting League objects to and from JSON strings.
 * This converter is used to facilitate storing League objects in a Room database.
 */
object LeagueConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromJson(json: String): League {
        return gson.fromJson(json, League::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toJson(league: League): String {
        return gson.toJson(league)
    }
}