package com.hasandeniz.movieappcompose.presentation.movielist.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.hasandeniz.movieappcompose.domain.model.Movie

@Composable
fun MovieListRow(movie: Movie, onItemClick: (Movie) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(movie)
        }
        .padding(5.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.poster),
            contentDescription = "Poster for movie: ${movie.title}",
            modifier = Modifier
                .size(160.dp, 200.dp)
                .clip(RectangleShape)
        )

        Column(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 30.dp)
        ) {
            Text(
                text = movie.title ?: "No Title",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                textAlign = TextAlign.Start,
            )
            Text(
                text = movie.year ?: "No Year",
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                textAlign = TextAlign.End
            )
        }
    }
}