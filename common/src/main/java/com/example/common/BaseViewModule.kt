package com.example.common

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import dagger.android.DaggerApplication

open class BaseViewModule(application: Application) : AndroidViewModel(application) {

}