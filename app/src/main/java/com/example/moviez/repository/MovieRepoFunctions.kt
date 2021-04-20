package com.example.moviez.repository

import com.example.moviez.model.Category.category_response
import com.example.moviez.model.MovieDetails.movie_details_response
import com.example.moviez.model.MovieList.movie_list_response
import retrofit2.Response

interface MovieRepoFunctions {

    suspend fun getAllCategory(): Response<category_response>
    suspend fun getSearchMovies(query: String): Response<movie_list_response>
    suspend fun getMoviesListByCategory(category: String): Response<movie_list_response>

    suspend fun getMovieListByLatestRelease(page : Int): Response<movie_list_response>
    suspend fun getMovieListByA_Z(page: Int): Response<movie_list_response>
    suspend fun getMovieListByRating(page: Int): Response<movie_list_response>

    suspend fun getMovieDetails(id: Int):Response<movie_details_response>

}