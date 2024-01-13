package com.bignerdranch.android.movies

import android.content.Intent
import android.os.Bundle
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

        // кнопка лупы
        val buttonSearch = findViewById<ImageButton>(R.id.buttonSearch)
        buttonSearch.setOnClickListener {
            val movieTitle = editTextMovieTitle.text.toString().trim()
            val releaseDate = editTextReleaseDate.text.toString().trim()

            if (movieTitle.isNotEmpty()) {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("MOVIE_TITLE", movieTitle)
                intent.putExtra("RELEASE_DATE", releaseDate)
                startActivity(intent)
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

        editTextMovieTitle.doOnTextChanged { text, _, _, _ ->
            // код для обработки изменения текста
            // валидация ввода или другие действия
        }

        editTextReleaseDate.doOnTextChanged { text, _, _, _ ->
            //  код для обработки изменения текста
            // валидация ввода или другие действия
        }
    }
}