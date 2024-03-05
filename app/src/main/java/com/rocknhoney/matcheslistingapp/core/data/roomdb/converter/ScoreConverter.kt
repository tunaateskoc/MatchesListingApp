package com.rocknhoney.matcheslistingapp.core.data.roomdb.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rocknhoney.matcheslistingapp.core.data.model.Score

/**
 * Type converter for converting Score objects to and from JSON strings.
 * This converter is used to facilitate storing League objects in a Room database.
 */
object ScoreConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromJson(json: String): Score {
        return gson.fromJson(json, Score::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toJson(score: Score): String {
        return gson.toJson(score)
    }
}