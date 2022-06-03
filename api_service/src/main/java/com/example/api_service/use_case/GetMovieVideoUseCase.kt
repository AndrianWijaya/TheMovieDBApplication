package com.example.api_service.use_case


import com.example.api_service.service.video.MovieVideoService
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.video.MovieVideoResponse
import kotlinx.coroutines.flow.flow

class GetMovieVideoUseCase(
    private val getMovieVideoService: MovieVideoService
) {
    operator fun invoke(id : Int) = flow<AppResponse<MovieVideoResponse>> {
        emit(AppResponse.loading())
        val data = getMovieVideoService.getMovieVideo(id)
        if (data.isSuccessful){
            data.body()?.let {
                emit(AppResponse.success(it))
            } ?: kotlin.run {
                emit(AppResponse.errorBackend(
                    data.code(),
                    null
                ))
            }
        }else{
            emit(AppResponse.errorBackend(
                data.code(),
                data.errorBody()
            ))
        }
    }
}