package com.rocknhoney.matcheslistingapp.core.data.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity
import com.rocknhoney.matcheslistingapp.core.data.roomdb.dao.MatchDao

/**
 * Database class representing the Room database for the Matches Listing application.
 * This class defines the database configuration and provides access to the DAO.
 */
@Database(entities = [MatchViewEntity::class], version = 1, exportSchema = false)
abstract class MatchesListingAppDatabase : RoomDatabase() {

    abstract fun MatchDao(): MatchDao

}