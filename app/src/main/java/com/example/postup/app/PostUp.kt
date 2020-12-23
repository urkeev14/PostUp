package com.example.postup.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PostUp : Application() {

    companion object {
        var application: PostUp? = null
        val context by lazy { application!!.applicationContext }

    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}