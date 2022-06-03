package com.example.themoviedbapplication

import com.example.themoviedbapplication.di.module.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TheMovieApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().app(this).build()

}