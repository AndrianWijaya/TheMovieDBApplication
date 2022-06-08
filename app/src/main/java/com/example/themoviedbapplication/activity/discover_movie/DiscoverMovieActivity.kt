package com.example.themoviedbapplication.activity.discover_movie

import android.os.Bundle
import androidx.activity.viewModels

import com.example.common.BaseActivity
import com.example.themoviedbapplication.R
import com.example.themoviedbapplication.databinding.LayoutDiscoverActivityListBinding
import com.example.themoviedbapplication.view_model.DiscoverMovieViewModel


class DiscoverMovieActivity : BaseActivity<LayoutDiscoverActivityListBinding, DiscoverMovieViewModel>() {
    override val layoutResourceId: Int = R.layout.layout_discover_activity_list
    override val vm: DiscoverMovieViewModel by viewModels{
        vmFactory
    }
    val adapter = DiscoverMoviePagingAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        initBinding()
    }
}