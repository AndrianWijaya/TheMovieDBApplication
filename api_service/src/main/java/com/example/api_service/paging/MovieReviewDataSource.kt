package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.review.MovieReviewService
import com.example.common.entity.movie_reviews.Review

class MovieReviewDataSource(
   val getmovieReviewService: MovieReviewService,
   val id : Int
) : PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page -1
        try {
            val data = getmovieReviewService.getMovieReviews(id, page)
            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(it.results, prevPage, nextPage)
                }?: kotlin.run {
                    return LoadResult.Page(emptyList(),prevPage,null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend :${data.code()}"))
            }
        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

    companion object{
        fun createPager(
            pageSize : Int,
            getmovieReviewService: MovieReviewService,
            id : Int
        ) = Pager<Int, Review>(
            PagingConfig(pageSize), null
        ){
            MovieReviewDataSource(getmovieReviewService, id)
        }
    }
}