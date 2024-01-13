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

        if (response.isSuccessful) {
            val movies = response.body()?.movies
            if (movies != null) {
                // Выводим результаты запроса в консоль
                for (movie in movies) {
                    println("Title: ${movie.Title}, Year: ${movie.Year}, Genre: ${movie.Genre}, Poster: ${movie.Poster}")
                }
                return movies
            }
        }

        // Обработка ошибки (например, вывод в лог или выброс исключения)
        println("Unsuccessful response. Code: ${response.code()}")
        return emptyList()
    }
}