package com.hasandeniz.movieappcompose.di

import com.hasandeniz.movieappcompose.data.remote.MovieApi
import com.hasandeniz.movieappcompose.data.repository.MovieRepositoryImpl
import com.hasandeniz.movieappcompose.domain.repository.MovieRepository
import com.hasandeniz.movieappcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api = api)
    }
}