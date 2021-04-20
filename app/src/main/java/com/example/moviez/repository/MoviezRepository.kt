package com.example.moviez.repository

import android.content.Context
import com.example.moviez.API.RetrofitInstance
import com.example.moviez.model.Category.category_response
import com.example.moviez.model.MovieDetails.movie_details_response
import com.example.moviez.model.MovieList.movie_list_response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviezRepository @Inject constructor(

    private val retrofitInstance: RetrofitInstance

) : MovieRepoFunctions {

    override suspend fun getAllCategory(): Response<category_response> {
        return withContext(Dispatchers.IO){retrofitInstance.getAllCategory()}
    }

    override suspend fun getSearchMovies(query: String): Response<movie_list_response> {
        return withContext(Dispatchers.IO){retrofitInstance.getSearchMovieList(query)}
    }

    override suspend fun getMoviesListByCategory(category: String): Response<movie_list_response> {
        return withContext(Dispatchers.IO){ retrofitInstance.getMoviesListByCategory(category)}
    }

    override suspend fun getMovieListByLatestRelease(page: Int): Response<movie_list_response> {
        return withContext(Dispatchers.IO){retrofitInstance.getMovieListByLatestRelease(page)}
    }

    override suspend fun getMovieListByA_Z(page: Int): Response<movie_list_response> {
        return withContext(Dispatchers.IO){retrofitInstance.getMovieListByA_Z(page)}
    }

    override suspend fun getMovieListByRating(page: Int): Response<movie_list_response> {
        return withContext(Dispatchers.IO){retrofitInstance.getMovieListByRating(page)}
    }

    override suspend fun getMovieDetails(id: Int): Response<movie_details_response> {
        return withContext(Dispatchers.IO){retrofitInstance.getMovieDetails(id)}
    }
}