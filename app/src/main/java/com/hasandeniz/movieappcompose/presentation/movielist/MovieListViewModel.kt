package com.hasandeniz.movieappcompose.presentation.movielist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasandeniz.movieappcompose.domain.usecase.getmovies.GetMovieUseCase
import com.hasandeniz.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    private var job: Job? = null

    init {
        getMovies(_state.value.searchString)
    }

    private fun getMovies(searchString: String) {
        job?.cancel()
        job = getMovieUseCase.execute(searchString).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = MovieListState(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MovieListState(error = it.message ?: "Error")
                }

                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }

    fun retry() {
        getMovies(_state.value.searchString)
    }
}