package com.example.themoviedbapplication.di.module

import android.content.Context
import com.example.api_service.RetrofitClient
import com.example.api_service.service.genre.GenreService
import com.example.api_service.service.movie_detail.MovieDetailService
import com.example.api_service.service.movie_discover.DiscoverMovieService
import com.example.api_service.service.review.MovieReviewService
import com.example.api_service.service.video.MovieVideoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(context: Context) =
        RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) =
        retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverMovieService(retrofit: Retrofit) =
        retrofit.create(DiscoverMovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetail(retrofit: Retrofit) = retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideMovieReview(retrofit: Retrofit) = retrofit.create(MovieReviewService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideo(retrofit: Retrofit) = retrofit.create(MovieVideoService::class.java)
}