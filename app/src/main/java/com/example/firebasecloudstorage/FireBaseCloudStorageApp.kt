package com.example.firebasecloudstorage

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FireBaseCloudStorageApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}