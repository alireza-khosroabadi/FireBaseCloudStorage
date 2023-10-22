package com.example.firebasecloudstorage.di

import com.example.firebasecloudstorage.data.uploadImage.UploadImageFirebaseStorage
import com.example.firebasecloudstorage.data.uploadImage.UploadImage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UploadImageModule {

    @Provides
    fun provideFireBaseStorage(storage: StorageReference): UploadImage = UploadImageFirebaseStorage(storage)
}