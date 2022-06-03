package com.example.api_service.use_case

import com.example.api_service.service.movie_detail.MovieDetailService
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.movie_details.MovieDetailsResponse
import kotlinx.coroutines.flow.flow

class GetMovieDetailUseCase(
    private val getMovieDetailService: MovieDetailService
) {
    operator fun invoke(id : Int) = flow<AppResponse<MovieDetailsResponse>> {
        emit(AppResponse.loading())
        val data = getMovieDetailService.getMovieDetails(id)
        if (data.isSuccessful){
            data.body()?.let {
                emit(AppResponse.success(it))
            } ?: kotlin.run {
                emit(AppResponse.errorBackend(
                    data.code(),
                    null
                ))
            }
        } else{
            emit(AppResponse.errorBackend(
                data.code(),
                data.errorBody()
            ))
        }
    }
}