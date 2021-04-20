package com.example.moviez.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.model.MovieDetails.movie_details_response
import com.example.moviez.repository.MovieRepoFunctions
import com.example.moviez.repository.MoviezRepository
import com.example.moviez.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailsFragmentViewModel @Inject constructor(
    private val moviezRepository : MovieRepoFunctions
): ViewModel(){

    val selectedMovieLiveData: MutableLiveData<Resource<movie_details_response>> = MutableLiveData()

    fun getSelectedMovieDetail(movie_id: Int)
    {
        viewModelScope.launch {
            val response = moviezRepository.getMovieDetails(movie_id)
            selectedMovieLiveData.postValue(handleSelectedMovieDetailResponse(response))
        }
    }

    fun handleSelectedMovieDetailResponse(response: Response<movie_details_response>) : Resource<movie_details_response>
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