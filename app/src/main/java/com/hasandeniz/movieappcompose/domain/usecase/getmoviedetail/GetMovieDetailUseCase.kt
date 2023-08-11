package com.hasandeniz.movieappcompose.domain.usecase.getmoviedetail

import com.hasandeniz.movieappcompose.data.remote.dto.toMovieDetail
import com.hasandeniz.movieappcompose.domain.model.MovieDetail
import com.hasandeniz.movieappcompose.domain.repository.MovieRepository
import com.hasandeniz.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        } catch (e: IOError) {
            emit(Resource.Error("No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Http Error"))
        } catch (e: SocketTimeoutException) {
            emit(Resource.Error("Timeout"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

}