package com.hasandeniz.movieappcompose.presentation.movielist.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hasandeniz.movieappcompose.presentation.Screen
import com.hasandeniz.movieappcompose.presentation.movielist.MovieListEvent
import com.hasandeniz.movieappcompose.presentation.movielist.MovieListViewModel

@Composable
fun MovieListScreen(navController: NavController, viewModel: MovieListViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                hint = "Enter movie name: Batman",
                onSearch = {
                    viewModel.onEvent(MovieListEvent.Search(it))
                }
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies) { movie ->
                    MovieListRow(movie = movie, onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbId}")
                    })
                }
            }
        }
        if (state.error.isNotBlank()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { viewModel.retry() }) {
                    Text(text = "Retry")
                }
            }


        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


