package com.example.themoviedbapplication.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.api_service.use_case.GetDiscoverMovieUseCase
import com.example.common.BaseViewModule
import com.example.common.entity.discover_movie.ResultDiscoverMovie
import kotlinx.coroutines.launch


class DiscoverMovieViewModel(
    private val useCase: GetDiscoverMovieUseCase,
    application: Application
) : BaseViewModule(
    application
) {
    val data = MutableLiveData<PagingData<ResultDiscoverMovie>>()
    val dataGenre = MutableLiveData<ArrayList<Int>>()

    fun getGenreId(id: ArrayList<Int>) {
        viewModelScope.launch {
            useCase.invoke(id).collect {
                data.postValue(it)
            }
        }
    }

}