
package com.example.pract61_bodrov_pr32.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.pract61_bodrov_pr32.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MoviesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val movieContainer = findViewById<LinearLayout>(R.id.movieContainer)

        lifecycleScope.launch {
            try {
                val titles = listOf("The Witcher", "Jessica Jones", "The Magicians")

                for (title in titles) {
                    val result = MovieApi.service.searchMovies(
                        MovieApi.API_KEY,
                        title
                    )

                    val movies = result.Search

                    if (result.Response == "True" && movies != null) {
                        for (movie in movies) {
                            addMovie(movieContainer, movie)
                        }
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@MoviesActivity,
                    "Ошибка загрузки фильмов",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun addMovie(container: LinearLayout, movie: Movie) {

        val movieLayout = LinearLayout(this)
        movieLayout.orientation = LinearLayout.VERTICAL
        movieLayout.gravity = Gravity.CENTER
        movieLayout.setPadding(0, 0, 0, 16)

        val poster = ImageView(this)
        poster.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            220
        )
        poster.scaleType = ImageView.ScaleType.CENTER_CROP

        if (movie.Poster != "N/A") {
            Picasso.get()
                .load(movie.Poster)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(poster)
        }

        val title = TextView(this)
        title.text = "${movie.Title} (${movie.Year})"
        title.setTextColor(android.graphics.Color.WHITE)
        title.textSize = 15f
        title.gravity = Gravity.CENTER
        title.setPadding(0, 6, 0, 0)

        movieLayout.addView(poster)
        movieLayout.addView(title)

        container.addView(movieLayout)
    }
}
