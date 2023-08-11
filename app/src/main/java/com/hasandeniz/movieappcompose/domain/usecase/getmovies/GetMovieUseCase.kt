package com.hasandeniz.movieappcompose.domain.usecase.getmovies

import com.hasandeniz.movieappcompose.data.remote.dto.toMovieList
import com.hasandeniz.movieappcompose.domain.model.Movie
import com.hasandeniz.movieappcompose.domain.repository.MovieRepository
import com.hasandeniz.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun execute(searchString: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(searchString)
            if (movieList.response == "True") {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error("No movie found"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        } catch (e: SocketTimeoutException) {
            emit(Resource.Error("Timeout"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}