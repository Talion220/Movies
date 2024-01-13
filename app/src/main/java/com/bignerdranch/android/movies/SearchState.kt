package com.bignerdranch.android.movies

sealed class SearchState {
    object Loading : SearchState()
    object Success : SearchState()
    data class Error(val message: String?) : SearchState()
}
