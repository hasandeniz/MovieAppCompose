package com.hasandeniz.movieappcompose.domain.repository

import com.hasandeniz.movieappcompose.data.remote.dto.MovieDetailDto
import com.hasandeniz.movieappcompose.data.remote.dto.MovieDto

interface MovieRepository {
    suspend fun getMovies(searchString: String): MovieDto

    suspend fun getMovieDetail(imdbId: String): MovieDetailDto
}