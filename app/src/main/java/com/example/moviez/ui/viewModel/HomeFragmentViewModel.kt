package com.example.moviez.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.model.Category.category_response
import com.example.moviez.model.MovieList.movie_list_response
import com.example.moviez.repository.MovieRepoFunctions
import com.example.moviez.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val moviezRepository : MovieRepoFunctions
): ViewModel() {

    val categoryLiveData: MutableLiveData<Resource<category_response>> = MutableLiveData()
    val moviezListLiveData: MutableLiveData<Resource<movie_list_response>> = MutableLiveData()

    init
    {
        getCategory()
        // loaded by default 28 = action
        getMovieListByCategory("28")
    }

    /**
    * get all available category
    */

    fun getCategory()
    {
        viewModelScope.launch {
            val response = moviezRepository.getAllCategory()
            categoryLiveData.postValue(handleCategoryResponse(response))
        }
    }

    fun handleCategoryResponse(response: Response<category_response>) : Resource<category_response>
    {
        if(response.isSuccessful)
        {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    /**
     * get default popular movies and selected category movies
     */

    fun getMovieListByCategory(category: String)
    {
        viewModelScope.launch {
            val response = moviezRepository.getMoviesListByCategory(category)
            moviezListLiveData.postValue(handleMovieListResponse(response))
        }
    }

    /**
     * get popular movies by search
     */

    fun getMovieListBySearch(query: String)
    {
        viewModelScope.launch {
            val response = moviezRepository.getSearchMovies(query)
            moviezListLiveData.postValue(handleMovieListResponse(response))
        }
    }

    fun handleMovieListResponse(response: Response<movie_list_response>) : Resource<movie_list_response>
    {
        if(response.isSuccessful)
        {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.toString())
    }


}