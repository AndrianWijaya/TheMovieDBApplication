package com.example.themoviedbapplication.activity.genre

import android.os.Bundle
import androidx.activity.viewModels
import com.example.common.BaseActivity
import com.example.themoviedbapplication.R
import com.example.themoviedbapplication.databinding.LayoutGenreListBinding
import com.example.themoviedbapplication.view_model.GenreMovieViewModel

class GenreActivity : BaseActivity<LayoutGenreListBinding, GenreMovieViewModel>() {
    override val layoutResourceId: Int = R.layout.layout_genre_list
    override val vm: GenreMovieViewModel by viewModels { vmFactory }

    val adapter = GenreAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

}