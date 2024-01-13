package com.bignerdranch.android.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bignerdranch.android.movies.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MovieAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

        viewModel.movies.observe(this, Observer {
            adapter.submitList(it)
        })

        // Получаем переданные данные из Intent
        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        val releaseDate = intent.getStringExtra("RELEASE_DATE")

        // Вызываем метод searchMovies у viewModel
        if (movieTitle != null) {
            viewModel.searchMovies(movieTitle, releaseDate)
        }
    }
}