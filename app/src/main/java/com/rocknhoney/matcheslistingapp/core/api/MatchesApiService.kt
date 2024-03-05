package com.rocknhoney.matcheslistingapp.core.api

import com.rocknhoney.matcheslistingapp.core.data.response.MatchesResponse
import com.rocknhoney.matcheslistingapp.core.util.Utils
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Interface defining the API endpoints for retrieving posts.
 */
interface MatchesApiService {

    /**
     * Sends a GET request to fetch a list of matches.
     * @return A [Single] emitting a list of matches when the request is successful.
     */
    @GET(Utils.MATCHES_URL)
    fun getMatches(): Single<MatchesResponse>
}