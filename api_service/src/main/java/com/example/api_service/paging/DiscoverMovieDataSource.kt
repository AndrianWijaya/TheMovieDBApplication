package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.movie_discover.DiscoverMovieService
import com.example.common.entity.discover_movie.ResultDiscoverMovie


class DiscoverMovieDataSource(
    private val discoverMovieService: DiscoverMovieService,
    val genre: Int
) : PagingSource<Int, ResultDiscoverMovie>() {
    override fun getRefreshKey(state: PagingState<Int, ResultDiscoverMovie>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDiscoverMovie> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1
        try {
            val data = discoverMovieService.getDiscoverMovie(
                genre.toString(), page
            )
            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.resultDiscoverMovies.isEmpty()) null else page + 1
                    return LoadResult.Page(it.resultDiscoverMovies, prevPage, nextPage)
                } ?: kotlin.run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend :${data.code()}"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)

        }
    }
    companion object{
        fun createPager(
            pageSize : Int,
            discoverMovieService: DiscoverMovieService,
            genre: Int
        ) = Pager<Int, ResultDiscoverMovie>(
            PagingConfig(pageSize), null
        ){
            DiscoverMovieDataSource(discoverMovieService, genre)
        }
    }
}