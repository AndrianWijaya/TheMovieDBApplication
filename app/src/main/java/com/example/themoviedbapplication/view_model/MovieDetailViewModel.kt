package com.example.themoviedbapplication.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.api_service.use_case.GetMovieDetailUseCase
import com.example.api_service.use_case.GetMovieReviewUseCase
import com.example.api_service.use_case.GetMovieVideoUseCase
import com.example.common.BaseViewModule
//import com.example.common.SingleLiveEvent
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.movie_details.MovieDetailsResponse
import com.example.common.entity.movie_reviews.Review
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieReviewUseCase: GetMovieReviewUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    application: Application
) : BaseViewModule(application) {

    val data = MutableLiveData<AppResponse<MovieDetailsResponse>>()
    val dataPaging = MutableLiveData<PagingData<Review>>()
    val movie = MutableLiveData<Int>()
    val video = MutableLiveData<String>()


    fun getMovieDetail(movieId : Int){
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(movieId).collect{
                data.postValue(it)
            }
            getMovieVideoUseCase.invoke(movieId).collect{
                video.postValue(it.data?.results?.last()?.key?.ifEmpty { "jLMBLuGJTsA" })
            }
            getMovieReviewUseCase.invoke(movieId).cachedIn(viewModelScope).collect{
                dataPaging.postValue(it)
            }

        }
    }

}