package com.example.api_service.service.review

import com.example.common.entity.movie_reviews.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "d656ac85ee9618f95e0adc0ffe06b9cd"
    ) : Response<MovieReviewsResponse>
}