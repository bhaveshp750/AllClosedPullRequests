package com.bhaveshp750.allclosedpullrequests.modal

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PullRequestsApi {
    @GET("/repos/bhaveshp750/AllClosedPullRequests/pulls?state=closed")
    suspend fun getPullRequests(): List<PullRequest>


    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}