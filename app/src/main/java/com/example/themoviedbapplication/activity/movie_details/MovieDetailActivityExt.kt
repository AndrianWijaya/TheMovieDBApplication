package com.example.themoviedbapplication.activity.movie_details

import android.app.ProgressDialog
import android.util.Log
import android.widget.Toast
import com.example.common.Constant
import com.example.common.entity.base_response.AppResponse
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

    var dialog : ProgressDialog? = null
    data.observe(this@observeLiveData){
        when(it.state){
            AppResponse.ERROR ->{
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Network Error(${it.code.toString()})",
                    Toast.LENGTH_SHORT).show()

            }
            AppResponse.SUCCESS -> {
                dialog?.dismiss()

            }
            AppResponse.LOADING ->{
                dialog?.dismiss()
                dialog = ProgressDialog(this@observeLiveData).apply {
                    setCancelable(false)
                    setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    setMessage("Memproses ..")
                    show()
                }


            }
        }

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

fun MovieDetailActivity.initBinding() = with(vm){
    binding.retry.setOnClickListener{
        movie.observe(this@initBinding){
            vm.getMovieDetail(it)
        }
    }
}
