package com.rocknhoney.matcheslistingapp.core.data.roomdb.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rocknhoney.matcheslistingapp.core.data.model.Team

/**
 * Type converter for converting Team objects to and from JSON strings.
 * This converter is used to facilitate storing League objects in a Room database.
 */
object TeamConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromJson(json: String): Team {
        return gson.fromJson(json, Team::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toJson(team: Team): String {
        return gson.toJson(team)
    }
}