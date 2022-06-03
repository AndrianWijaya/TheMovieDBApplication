package com.example.api_service.service.movie_detail

import com.example.common.entity.movie_details.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = "d656ac85ee9618f95e0adc0ffe06b9cd"
    ) : Response<MovieDetailsResponse>
}