package com.hasandeniz.movieappcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hasandeniz.movieappcompose.presentation.moviedetail.view.MovieDetailScreen
import com.hasandeniz.movieappcompose.presentation.movielist.views.MovieListScreen
import com.hasandeniz.movieappcompose.presentation.ui.theme.MovieAppComposeTheme
import com.hasandeniz.movieappcompose.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieListScreen.route
                    ) {
                        composable(route = Screen.MovieListScreen.route) {
                            MovieListScreen(navController = navController)
                        }

                        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}") {
                            MovieDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
