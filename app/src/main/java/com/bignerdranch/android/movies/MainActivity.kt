package com.bignerdranch.android.movies

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
import com.bignerdranch.android.movies.ui.theme.MoviesTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настройка ActionBar с собственным макетом
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.action_bar_layout)
            setDisplayShowCustomEnabled(true)
        }

        // Обработка нажатия на кнопку корзины
        val basketButton = supportActionBar?.customView?.findViewById<ImageButton>(R.id.basketButton)
        basketButton?.setOnClickListener {
            // код для обработки нажатия на кнопку корзины
            Toast.makeText(this, "Кнопка корзины нажата", Toast.LENGTH_SHORT).show()
        }

        // Получение ссылки на FAB
        val fabButton = findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            // Открытие второй активности (AddActivity) при нажатии на FAB
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
