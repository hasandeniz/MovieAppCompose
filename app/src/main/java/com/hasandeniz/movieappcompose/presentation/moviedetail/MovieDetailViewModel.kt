package com.hasandeniz.movieappcompose.presentation.moviedetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasandeniz.movieappcompose.domain.usecase.getmoviedetail.GetMovieDetailUseCase
import com.hasandeniz.movieappcompose.util.Constants.IMDB_ID
import com.hasandeniz.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        getMovieDetailUseCase.execute(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error")
                }

                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun retry() {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }
}
