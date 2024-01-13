package com.bignerdranch.android.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun searchMovies(title: String, year: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchMovies(title, year)
            _movies.postValue(result)
        }
    }


}
