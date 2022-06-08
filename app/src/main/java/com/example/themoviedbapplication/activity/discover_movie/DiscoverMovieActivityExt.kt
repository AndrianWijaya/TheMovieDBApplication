package com.example.themoviedbapplication.activity.discover_movie


import android.view.View
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMovieActivity.observeLiveData()= with(vm) {
    binding.recycler.adapter = adapter
    data.observe(this@observeLiveData){
        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Error){
                binding.retry.visibility = View.VISIBLE
            } else {
                binding.retry.visibility = View.GONE
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    dataGenre.observe(this@observeLiveData){
        vm.getGenreId(it)
    }

    dataGenre.value = intent.getIntegerArrayListExtra("EXTRA_DATA")
}

fun DiscoverMovieActivity.initBinding() = with(vm){
    binding.retry.setOnClickListener{
        dataGenre.observe(this@initBinding){
                vm.getGenreId(it)
            }
        }
    }



