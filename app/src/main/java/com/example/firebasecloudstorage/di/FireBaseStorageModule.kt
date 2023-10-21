package com.example.firebasecloudstorage.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FireBaseStorageModule {

    @Provides
    fun provideFireBaseStorage(): StorageReference {
        return Firebase.storage.reference
    }
}