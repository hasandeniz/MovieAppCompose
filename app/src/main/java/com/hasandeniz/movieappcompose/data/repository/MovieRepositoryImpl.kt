package com.hasandeniz.movieappcompose.data.repository

import com.hasandeniz.movieappcompose.data.remote.MovieApi
import com.hasandeniz.movieappcompose.data.remote.dto.MovieDetailDto
import com.hasandeniz.movieappcompose.data.remote.dto.MovieDto
import com.hasandeniz.movieappcompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {

    override suspend fun getMovies(searchString: String): MovieDto {
        return api.getMovies(searchString = searchString)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetails(imdbId)
    }
}