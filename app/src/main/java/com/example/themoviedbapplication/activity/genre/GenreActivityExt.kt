package com.example.themoviedbapplication.activity.genre


fun GenreActivity.observeLiveData()= with(vm) {
    binding.recycler.adapter = adapter
    data.observe(this@observeLiveData){
        adapter.submitData(it.data.orEmpty())
    }
}


