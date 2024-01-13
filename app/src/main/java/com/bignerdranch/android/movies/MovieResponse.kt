package com.bignerdranch.android.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search") val movies: List<Movie>?,
    @SerializedName("Error") val error: String?
)