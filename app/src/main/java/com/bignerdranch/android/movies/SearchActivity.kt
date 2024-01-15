package com.bignerdranch.android.movies

import android.app.Activity
import android.content.Intent
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

        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        val releaseDate = intent.getStringExtra("RELEASE_DATE")

        if (movieTitle != null) {
            viewModel.searchMovies(movieTitle, releaseDate)
        }

        adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                val resultIntent = Intent()
                resultIntent.putExtra("MOVIE_TITLE_RESULT", movie.Title)
                resultIntent.putExtra("RELEASE_DATE_RESULT", movie.Year)
                resultIntent.putExtra("POSTER_RESULT", movie.Poster)

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        })


    }
}