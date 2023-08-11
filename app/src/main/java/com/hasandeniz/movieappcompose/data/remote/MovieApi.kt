package com.hasandeniz.movieappcompose.data.remote

import com.hasandeniz.movieappcompose.data.remote.dto.MovieDetailDto
import com.hasandeniz.movieappcompose.data.remote.dto.MovieDto
import com.hasandeniz.movieappcompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("page") page: Int = 1
    ): MovieDto

    @GET(".")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetailDto
}