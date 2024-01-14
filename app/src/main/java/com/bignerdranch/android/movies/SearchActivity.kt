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

        // Получаем переданные данные из Intent
        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        val releaseDate = intent.getStringExtra("RELEASE_DATE")

        // Вызываем метод searchMovies у viewModel
        if (movieTitle != null) {
            viewModel.searchMovies(movieTitle, releaseDate)
        }

        viewModel.movies.observe(this, Observer {
            // Обработка успешного состояния
            // Например, выведите данные в консоль
            it?.forEach { movie ->
                println("Movie: ${movie.Title}, Year: ${movie.Year}, Genre: ${movie.Genre}")
            }
        })

        adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                val resultIntent = Intent()
                resultIntent.putExtra("MOVIE_TITLE_RESULT", movie.Title)
                resultIntent.putExtra("RELEASE_DATE_RESULT", movie.Year)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        })


    }
}