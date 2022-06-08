package com.example.api_service

import android.content.Context
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.GanderInterceptor
import com.ashokvarma.gander.imdb.GanderIMDB
import com.example.common.Constant
import com.example.common.interceptor.ConnectivityInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getClient(context: Context): Retrofit {
        Gander.setGanderStorage(GanderIMDB.getInstance())
        return Retrofit.Builder().client(
            OkHttpClient().newBuilder()
                .addInterceptor(ConnectivityInterceptor(context))
                .addInterceptor(GanderInterceptor(context).showNotification(true)).build()
        ).baseUrl(Constant.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            ).build()
    }
}