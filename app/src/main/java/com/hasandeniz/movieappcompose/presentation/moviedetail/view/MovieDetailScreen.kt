package com.hasandeniz.movieappcompose.presentation.moviedetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hasandeniz.movieappcompose.presentation.moviedetail.MovieDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {

    val state = movieDetailViewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = state.movie?.title ?: "Movie Detail",
                    color = Color.White,
                    maxLines = 2
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
        ) {

            state.movie?.let { movieDetail ->
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = movieDetail.poster),
                            contentDescription = "Poster of movie: ${movieDetail.title}",
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 8.dp, start = 8.dp)
                                .size(240.dp, 360.dp)
                                .clip(RectangleShape)
                                .border(0.4.dp, Color.White, RectangleShape)
                        )


                        Column(
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(horizontalAlignment = CenterHorizontally) {
                                MovieDetailTitleTextCentered(text = "Duration")
                                MovieDetailDescriptionTextCentered(text = movieDetail.length ?: "-")
                            }

                            Column(horizontalAlignment = CenterHorizontally) {
                                MovieDetailTitleTextCentered(text = "Year")
                                MovieDetailDescriptionTextCentered(text = movieDetail.year ?: "-")
                            }

                            Column(horizontalAlignment = CenterHorizontally) {
                                MovieDetailTitleTextCentered(text = "Language")
                                MovieDetailDescriptionTextCentered(
                                    text = movieDetail.language ?: "-"
                                )
                            }
                            Column(horizontalAlignment = CenterHorizontally) {
                                MovieDetailTitleTextCentered(text = "IMDb Rating")
                                MovieDetailDescriptionTextCentered(
                                    text = movieDetail.imdbRating ?: "-"
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(12.dp))


                    }
                    Column {
                        MovieDetailTitleText(text = "Plot")
                        MovieDetailDescriptionText(text = movieDetail.plot ?: "-")
                        MovieDetailTitleText(text = "Director")
                        MovieDetailDescriptionText(text = movieDetail.director ?: "-")
                        MovieDetailTitleText(text = "Actors")
                        MovieDetailDescriptionText(text = movieDetail.actors ?: "-")
                        MovieDetailTitleText(text = "Awards")
                        MovieDetailDescriptionText(text = movieDetail.awards ?: "-")
                    }
                }
            }

            if (state.error.isNotBlank()) {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Center)
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

                    Button(onClick = { movieDetailViewModel.retry() }) {
                        Text(text = "Retry")
                    }
                }


            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
        }
    }
}

@Composable
fun MovieDetailTitleTextCentered(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 4.dp),
        color = Color.White,
        fontSize = 16.sp
    )
}

@Composable
fun MovieDetailDescriptionTextCentered(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 12.dp),
        color = Color.White,
        fontSize = 14.sp
    )
}

@Composable
fun MovieDetailTitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp),
        color = Color.White,
        fontSize = 16.sp
    )
}

@Composable
fun MovieDetailDescriptionText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = 12.dp, start = 8.dp),
        color = Color.White,
        fontSize = 14.sp
    )
}