package com.bignerdranch.android.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val editTextMovieTitle = findViewById<EditText>(R.id.editTextMovieTitle)
        val editTextReleaseDate = findViewById<EditText>(R.id.editTextReleaseDate)
        val buttonAddMovie = findViewById<Button>(R.id.buttonAddMovie)

        // Получаем переданные данные из Intent
        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        val releaseDate = intent.getStringExtra("RELEASE_DATE")

        // Устанавливаем значения в EditText
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
                // Показать сообщение об ошибке, если movieTitle пуст
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

        Log.d("SearchActivity", "onActivityResult: requestCode=$requestCode, resultCode=$resultCode")

        if (requestCode == SEARCH_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val movieTitleResult = data?.getStringExtra("MOVIE_TITLE_RESULT")
            val releaseDateResult = data?.getStringExtra("RELEASE_DATE_RESULT")

            Log.d("SearchActivity", "onActivityResult: movieTitleResult=$movieTitleResult, releaseDateResult=$releaseDateResult")

            // Заполните editTextMovieTitle и editTextReleaseDate данными из результата
            findViewById<EditText>(R.id.editTextMovieTitle).setText(movieTitleResult)
            findViewById<EditText>(R.id.editTextReleaseDate).setText(releaseDateResult)
        }
    }


}