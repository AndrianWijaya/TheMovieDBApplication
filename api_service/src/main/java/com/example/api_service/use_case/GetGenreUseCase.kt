package com.example.api_service.use_case

import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.genre.Genre
import com.example.api_service.service.genre.GenreService
import kotlinx.coroutines.flow.flow

class GetGenreUseCase(
   val service: GenreService
) {
    operator fun invoke() = flow<AppResponse<List<Genre>>> {
        emit(AppResponse.loading())
        val dataGenre = service.getGenre()
        if (dataGenre.isSuccessful){
            dataGenre.body()?.let {
                emit(AppResponse.success(it.genres))
            }?: kotlin.run {
                emit(AppResponse.errorBackend(
                    dataGenre.code(),
                    dataGenre.errorBody()
                ))
            }
        } else {
            emit(AppResponse.errorBackend(
                dataGenre.code(),
                null
            ))
        }
    }
}