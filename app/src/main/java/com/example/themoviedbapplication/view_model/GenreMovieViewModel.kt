package com.example.themoviedbapplication.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.BaseViewModule
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.genre.Genre
import com.example.api_service.use_case.GetGenreUseCase
import kotlinx.coroutines.launch

class GenreMovieViewModel(
   val getGenreUseCase: GetGenreUseCase,
    application: Application
) : BaseViewModule(application) {

    val data = MutableLiveData<AppResponse<List<Genre>>>()
    val selection = MutableLiveData<List<Genre>>()

    init {
        getGenre()
    }

    fun getGenre(){
        viewModelScope.launch {
            getGenreUseCase().collect{
                data.postValue(it)
            }
        }
    }
}