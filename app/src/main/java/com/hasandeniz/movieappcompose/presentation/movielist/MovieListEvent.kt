package com.hasandeniz.movieappcompose.presentation.movielist

sealed class MovieListEvent {
    data class Search(val searchString: String) : MovieListEvent()
}
