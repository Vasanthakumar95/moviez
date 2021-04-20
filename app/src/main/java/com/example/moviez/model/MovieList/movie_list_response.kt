package com.example.moviez.model.MovieList

data class movie_list_response(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)