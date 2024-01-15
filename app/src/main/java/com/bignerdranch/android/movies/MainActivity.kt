package com.bignerdranch.android.movies

import MovieDbAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.movies.ui.theme.MoviesTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var movieDao: MovieDao
    private lateinit var adapter: MovieDbAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.action_bar_layout)
            setDisplayShowCustomEnabled(true)
        }

        val movieDatabase = AppDatabase.getDatabase(applicationContext)
        movieDao = movieDatabase.movieDao()

        lifecycleScope.launch {
            val movieList = movieDao.getAllMovies()

            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            val adapter = MovieDbAdapter(movieList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val basketButton = supportActionBar?.customView?.findViewById<ImageButton>(R.id.basketButton)
        basketButton?.setOnClickListener {
            // код для обработки нажатия на кнопку корзины
            Toast.makeText(this, "Кнопка корзины нажата", Toast.LENGTH_SHORT).show()
        }

        val fabButton = findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
