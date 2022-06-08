package com.example.api_service.use_case


import com.example.api_service.paging.DiscoverMovieDataSource
import com.example.api_service.service.movie_discover.DiscoverMovieService


class GetDiscoverMovieUseCase(
   val discoverMovieService: DiscoverMovieService
) {
    operator fun invoke(id : ArrayList<Int>) = DiscoverMovieDataSource.createPager(
        10,discoverMovieService,id
    ).flow
}