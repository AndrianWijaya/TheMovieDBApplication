package com.example.themoviedbapplication.di.module

import com.example.themoviedbapplication.TheMovieApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component (modules = [AndroidInjectionModule::class, ViewModelProvideFactoryModule::class])
abstract class AppComponent : AndroidInjector<TheMovieApplication> {

    @Component.Builder
    abstract class Builder{

        @BindsInstance
        abstract fun app(application: DaggerApplication) : Builder
        abstract fun build() : AppComponent
    }
}