package com.example.themoviedbapplication.activity.movie_details

import android.util.Log
import com.example.common.Constant
import com.example.themoviedbapplication.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun MovieDetailActivity.observeLiveData() = with(vm) {
    binding.recycler.adapter = adapter
    dataPaging.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
    movie.observe(this@observeLiveData){
        vm.getMovieDetail(it)
    }
    movie.value = intent.getIntExtra("EXTRA_DATA",-1)
//    vm.video.postValue(dataVideo.value?.data?.results?.last()?.key?.ifEmpty { "jLMBLuGJTsA" })
//    vm.video.postValue("jLMBLuGJTsA")
    vm.video.observe(this@observeLiveData){
        val youtubeFragment = YouTubePlayerSupportFragmentX.newInstance()
        with(supportFragmentManager){
            beginTransaction().apply {
                add(R.id.video, youtubeFragment)
                commit()
            }
        }
        youtubeFragment.initialize(
            Constant.YOUTUBE_KEY,
            object : YouTubePlayerSupportFragmentX.OnInitializedListener(){
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.cueVideo(it)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.e("YoutubePlayer","error ${p1?.name}")
                }

            }
        )
    }
}
