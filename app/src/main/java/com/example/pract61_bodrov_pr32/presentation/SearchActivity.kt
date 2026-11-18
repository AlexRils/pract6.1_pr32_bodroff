
package com.example.pract61_bodrov_pr32.presentation

import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.pract61_bodrov_pr32.R

class SearchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val btnChats = findViewById<ImageButton>(R.id.btnChats)
        val btnMovies = findViewById<ImageButton>(R.id.btnMovies)
        val btnFavorites = findViewById<ImageButton>(R.id.btnFavorites)

        btnChats.setOnClickListener {
            val intent = Intent(this, ChatListActivity::class.java)
            startActivity(intent)
        }


        btnMovies.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }

        btnFavorites.setOnClickListener {
          Toast.makeText(this, "Сначала добавтье избранные фильмы", Toast.LENGTH_SHORT).show()
        }
    }
}
