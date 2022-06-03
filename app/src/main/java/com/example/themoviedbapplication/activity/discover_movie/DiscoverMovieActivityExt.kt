package com.example.themoviedbapplication.activity.discover_movie


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMovieActivity.observeLiveData()= with(vm) {
    binding.recycler.adapter = adapter
    data.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    dataGenre.observe(this@observeLiveData){
        vm.getGenreId(it)
    }

    dataGenre.value = intent.getIntExtra("EXTRA_DATA",-1)
}

