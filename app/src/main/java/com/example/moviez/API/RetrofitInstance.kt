package com.example.moviez.API

import com.example.moviez.BuildConfig
import com.example.moviez.model.Category.category_response
import com.example.moviez.model.MovieDetails.movie_details_response
import com.example.moviez.model.MovieList.movie_list_response
import com.example.moviez.util.Constants.Companion.BASE_URL
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitInstance {

    // Get all available category
    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getAllCategory(): Response<category_response>

    // Get popular movies list by category
    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US&page=1&include_adult=false")
    suspend fun getMoviesListByCategory(
        @Query("with_genres") genre: String
    ): Response<movie_list_response>

    // get movies list by word query
    @GET("search/movie?api_key=${BuildConfig.API_KEY}&language=en-US&include_adult=false")
    suspend fun getSearchMovieList(
        @Query("query") query: String
    ): Response<movie_list_response>



    // get movie listing by latest release (default)
    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&primary_release_date.lte=2021-04-17&")
    suspend fun getMovieListByLatestRelease(
        @Query("page") page: Int
    ): Response<movie_list_response>

    //get movies alphabetically
    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US&sort_by=original_title.asc&include_adult=false")
    suspend fun getMovieListByA_Z(
        @Query("page") page: Int
    ): Response<movie_list_response>

    // get top rated movies
    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    suspend fun getMovieListByRating(
        @Query("page") page: Int
    ): Response<movie_list_response>



    //get movie details
    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ):Response<movie_details_response>

    companion object
    {
        fun create(): RetrofitInstance {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RetrofitInstance::class.java)
        }
    }

}