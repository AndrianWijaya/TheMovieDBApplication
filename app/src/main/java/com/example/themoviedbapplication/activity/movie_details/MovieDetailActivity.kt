package com.example.themoviedbapplication.activity.movie_details

import android.os.Bundle
import androidx.activity.viewModels
import com.example.common.BaseActivity
import com.example.themoviedbapplication.R
import com.example.themoviedbapplication.databinding.LayoutDetailActivityItemBinding
import com.example.themoviedbapplication.view_model.MovieDetailViewModel

class MovieDetailActivity: BaseActivity<LayoutDetailActivityItemBinding, MovieDetailViewModel>() {
    override val layoutResourceId: Int = R.layout.layout_detail_activity_item
    override val vm: MovieDetailViewModel by viewModels {
        vmFactory
    }

    val adapter = MovieReviewPagingAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

}