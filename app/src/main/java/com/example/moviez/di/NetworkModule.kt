package com.example.moviez.di

import com.example.moviez.API.RetrofitInstance
import com.example.moviez.repository.MovieRepoFunctions
import com.example.moviez.repository.MoviezRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkModule(): RetrofitInstance
    {
        return RetrofitInstance.create()
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        retrofitInstance: RetrofitInstance
    ) = MoviezRepository(retrofitInstance) as MovieRepoFunctions

}