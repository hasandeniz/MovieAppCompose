package com.hasandeniz.movieappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.hasandeniz.movieappcompose.domain.model.Movie

data class MovieDto(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    val totalResults: String
)

fun MovieDto.toMovieList(): List<Movie> {
    return search.map { search -> Movie(search.imdbID, search.year, search.title, search.poster) }

}