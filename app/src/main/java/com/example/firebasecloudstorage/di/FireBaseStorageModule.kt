package com.example.firebasecloudstorage.di

import com.example.firebasecloudstorage.data.repository.deleteImage.DeleteImageRepository
import com.example.firebasecloudstorage.data.repository.deleteImage.DeleteImageRepositoryImpl
import com.example.firebasecloudstorage.data.repository.imageList.ImageListRepository
import com.example.firebasecloudstorage.data.repository.imageList.ImageListRepositoryImpl
import com.example.firebasecloudstorage.data.repository.uploadImage.UploadImageRepository
import com.example.firebasecloudstorage.data.repository.uploadImage.UploadImageRepositoryImpl
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FireBaseStorageModule {

    @Provides
    fun provideFireBaseStorage(): StorageReference {
        return Firebase.storage.reference
    }

    @Provides
    fun provideImageListRepository(reference: StorageReference): ImageListRepository =
        ImageListRepositoryImpl(reference)

    @Provides
    fun provideUploadImageRepository(storage: StorageReference): UploadImageRepository =
        UploadImageRepositoryImpl(storage)

    @Provides
    fun provideDeleteImageRepository(storage: StorageReference): DeleteImageRepository =
        DeleteImageRepositoryImpl(storage)
}