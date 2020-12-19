package com.example.postup.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

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