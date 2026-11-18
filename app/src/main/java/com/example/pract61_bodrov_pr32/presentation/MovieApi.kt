package com.example.pract61_bodrov_pr32.presentation

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class MovieResponse(
    val Search: List<Movie>?,
    val Response: String
)

data class Movie(
    val Title: String,
    val Year: String,
    val Poster: String
)

interface MovieApiService {

    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") title: String
    ): MovieResponse
}

object MovieApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MovieApiService =
        retrofit.create(MovieApiService::class.java)

    const val API_KEY = "e5b26486"
}
