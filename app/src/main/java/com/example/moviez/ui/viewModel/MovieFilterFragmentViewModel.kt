package com.example.moviez.ui.viewModel

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.MoviezApp
import com.example.moviez.model.Category.category_response
import com.example.moviez.model.MovieList.movie_list_response
import com.example.moviez.repository.MovieRepoFunctions
import com.example.moviez.repository.MoviezRepository
import com.example.moviez.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieFilterFragmentViewModel @Inject constructor(
    private val moviezRepository : MovieRepoFunctions
): ViewModel()
{
    val movieListLiveData: MutableLiveData<Resource<movie_list_response>> = MutableLiveData()
    var ListResponse: movie_list_response? = null

    /**
     * get movie by latest release
     */

    fun getListByLatestRelease(page: Int)
    {
        viewModelScope.launch {
            val response = moviezRepository.getMovieListByLatestRelease(page)
            movieListLiveData.postValue(handleListResponse(response))
        }
    }

    /**
     * get movie by latest release
     */

    fun getListByA_Z(page: Int)
    {
        viewModelScope.launch {
            val response = moviezRepository.getMovieListByA_Z(page)
            movieListLiveData.postValue(handleListResponse(response))
        }
    }

    /**
     * get movie by latest release
     */
    fun getListByRating(page: Int)
    {
        viewModelScope.launch {
            val response = moviezRepository.getMovieListByRating(page)
            movieListLiveData.postValue(handleListResponse(response))
        }
    }


    fun handleListResponse(response: Response<movie_list_response>) : Resource<movie_list_response>
    {
        if(response.isSuccessful)
        {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }




}