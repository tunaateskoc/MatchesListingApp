package com.rocknhoney.matcheslistingapp.core.api.repository

import com.rocknhoney.matcheslistingapp.core.api.MatchesApiService
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity
import com.rocknhoney.matcheslistingapp.core.data.roomdb.dao.MatchDao
import javax.inject.Inject

/**
 * Repository responsible for fetching matches from an API.
 * @param apiService An instance of [MatchesApiService] used to make network requests to retrieve matches.
 * @param matchDao An instance of [MatchDao] used to interact with the local database for match data.
 */
class MatchesRepository @Inject constructor(
    private val apiService: MatchesApiService,
    private val matchDao: MatchDao
) : MatchesRepositoryInterface {

    /**
     * Fetches a list of matches asynchronously from the remote API.
     * @return List of matches retrieved from the remote API.
     */
    override suspend fun getMatches() = apiService.getMatches()

    /**
     * Retrieves a list of favorite matches from the local database.
     * @return List of favorite matches retrieved from the local database.
     */
    override fun getFavouriteMatchesFromLocal(): List<MatchViewEntity> {
        return matchDao.getMatches()
    }

    /**
     * Inserts matches into the local database.
     * @param match List of matches to be inserted.
     */
    override suspend fun insertMatch(vararg match: MatchViewEntity) {
        matchDao.insertMatch(*match)
    }

    /**
     * Deletes a match from the local database.
     * @param match Match to be deleted.
     */
    override suspend fun deleteMatch(match: MatchViewEntity) {
        matchDao.deleteMatch(match)
    }

}