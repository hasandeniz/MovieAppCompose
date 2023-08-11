package com.hasandeniz.movieappcompose.presentation.moviedetail

import com.hasandeniz.movieappcompose.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)