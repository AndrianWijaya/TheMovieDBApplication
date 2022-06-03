package com.example.api_service.service.genre

import com.example.common.Constant
import com.example.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey : String = Constant.API_KEY
    ): Response<GenreResponse>
}