package com.bignerdranch.android.movies

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("t") title: String,
        @Query("y") year: String?
    ): Response<MovieResponse>
}