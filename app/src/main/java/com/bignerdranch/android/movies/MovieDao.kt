package com.bignerdranch.android.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>
}