package com.lucasmarciano.remote.service

import com.lucasmarciano.remote.model.ProjectsResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingService {
    @GET("search/repositories")
    fun searchRepositories(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Observable<ProjectsResponseModel>
}