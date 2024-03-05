package com.rocknhoney.matcheslistingapp.core.data.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity

/**
 * Data Access Object (DAO) interface for Match entities.
 * This interface defines methods for accessing Match data in the database.
 */
@Dao
interface MatchDao {

    /**
     * Retrieves all matches from the database.
     * @return List of MatchViewEntity objects representing matches.
     */
    @Query("SELECT * FROM Match")
    fun getMatches(): List<MatchViewEntity>

    /**
     * Inserts one or more matches into the database.
     * @param match One or more MatchViewEntity objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(vararg match: MatchViewEntity)

    /**
     * Deletes a match from the database.
     * @param match MatchViewEntity object to be deleted.
     */
    @Delete
    suspend fun deleteMatch(match: MatchViewEntity)

}