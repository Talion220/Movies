package com.bignerdranch.android.movies

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val editTextMovieTitle = findViewById<EditText>(R.id.editTextMovieTitle)
        val editTextReleaseDate = findViewById<EditText>(R.id.editTextReleaseDate)
        val buttonAddMovie = findViewById<Button>(R.id.buttonAddMovie)

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
            // Ваш код для обработки изменения текста
            // валидация ввода или другие действия
        }
    }
}