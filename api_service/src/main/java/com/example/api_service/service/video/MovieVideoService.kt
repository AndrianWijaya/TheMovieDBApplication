package com.example.api_service.service.video

import com.example.common.entity.video.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieVideoService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = "d656ac85ee9618f95e0adc0ffe06b9cd"
    ) : Response<MovieVideoResponse>
}