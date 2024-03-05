package com.rocknhoney.matcheslistingapp.core.api.repository

import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity
import com.rocknhoney.matcheslistingapp.core.data.response.MatchesResponse
import io.reactivex.rxjava3.core.Single

/**
 * Interface defining the contract for fetching matches.
 */
interface MatchesRepositoryInterface {

    /**
     * Retrieves a list of matches from the API asynchronously.
     * @return A [Single] emitting a list of matches when the operation is complete.
     */
    suspend fun getMatches(): Single<MatchesResponse>

    /**
     * Retrieves a list of favorite matches from the local database.
     * @return List of favorite matches retrieved from the local database.
     */
    fun getFavouriteMatchesFromLocal(): List<MatchViewEntity>

    /**
     * Inserts matches into the local database.
     * @param match List of matches to be inserted.
     */
    suspend fun insertMatch(vararg match: MatchViewEntity)

    /**
     * Deletes a match from the local database.
     * @param match Match to be deleted.
     */
    suspend fun deleteMatch(match: MatchViewEntity)
}