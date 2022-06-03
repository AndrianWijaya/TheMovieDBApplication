package com.example.api_service.use_case

import com.example.api_service.paging.MovieReviewDataSource
import com.example.api_service.service.review.MovieReviewService

class GetMovieReviewUseCase(
    val getMovieReviewService: MovieReviewService
) {
    operator fun invoke(id : Int) = MovieReviewDataSource.createPager(
        10, getMovieReviewService, id
    ).flow
}