package com.example.firebasecloudstorage.di

import com.example.firebasecloudstorage.data.imageList.ImageList
import com.example.firebasecloudstorage.data.imageList.ImageListFirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ImageListModule {

    @Provides
    fun provideImageListFirebaseStorage(reference: StorageReference): ImageList =
        ImageListFirebaseStorage(reference)
}