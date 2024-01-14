package com.bignerdranch.android.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val editTextMovieTitle = findViewById<EditText>(R.id.editTextMovieTitle)
        val editTextReleaseDate = findViewById<EditText>(R.id.editTextReleaseDate)
        val buttonAddMovie = findViewById<Button>(R.id.buttonAddMovie)

        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        val releaseDate = intent.getStringExtra("RELEASE_DATE")

        editTextMovieTitle.setText(movieTitle)
        editTextReleaseDate.setText(releaseDate)

        // кнопка лупы
        val buttonSearch = findViewById<ImageButton>(R.id.buttonSearch)
        buttonSearch.setOnClickListener {
            val movieTitle = editTextMovieTitle.text.toString().trim()
            val releaseDate = editTextReleaseDate.text.toString().trim()

            if (movieTitle.isNotEmpty()) {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("MOVIE_TITLE", movieTitle)
                intent.putExtra("RELEASE_DATE", releaseDate)
                startActivityForResult(intent, SEARCH_ACTIVITY_REQUEST_CODE)
            } else {
                Toast.makeText(this, "Введите название фильма", Toast.LENGTH_SHORT).show()
            }
        }

        buttonAddMovie.setOnClickListener {
            // код для добавления фильма в БД
            val movieTitle = editTextMovieTitle.text.toString()
            val releaseDate = editTextReleaseDate.text.toString()

            // TODO: Добавить логику для добавления фильма в БД

            // Закрываем текущую активность
            finish()
        }
    }
    companion object {
        const val SEARCH_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SEARCH_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val movieTitleResult = data?.getStringExtra("MOVIE_TITLE_RESULT")
            val releaseDateResult = data?.getStringExtra("RELEASE_DATE_RESULT")
            val posterResult = data?.getStringExtra("POSTER_RESULT")

            findViewById<EditText>(R.id.editTextMovieTitle).setText(movieTitleResult)
            findViewById<EditText>(R.id.editTextReleaseDate).setText(releaseDateResult)

            val posterImageView = findViewById<ImageView>(R.id.imageViewPoster)

            posterResult?.takeIf { it.isNotEmpty() }?.let {
                Picasso.get()
                    .load(it)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(posterImageView)
            } ?: posterImageView.setImageResource(R.drawable.placeholder)
        }
    }


}