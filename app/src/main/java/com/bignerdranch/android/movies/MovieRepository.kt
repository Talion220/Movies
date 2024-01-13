package com.bignerdranch.android.movies

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private val apiService: MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    suspend fun searchMovies(title: String, year: String?): List<Movie> {
        val apiKey = "2c7fd3a5"
        val response = apiService.searchMovies(apiKey, title, year)

        return if (response.isSuccessful) {
            response.body()?.movies ?: emptyList()
        } else {
            // Обработка ошибки (например, вывод в лог или выброс исключения)
            emptyList()
        }
    }
}