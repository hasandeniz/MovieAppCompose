package com.hasandeniz.movieappcompose.presentation.movielist

import com.hasandeniz.movieappcompose.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val searchString: String = "Batman"
)