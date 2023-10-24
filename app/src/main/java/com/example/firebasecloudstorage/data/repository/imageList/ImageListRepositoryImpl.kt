package com.example.firebasecloudstorage.data.repository.imageList

import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.baseModel.ErrorModel
import com.example.firebasecloudstorage.data.model.image.ImageModel
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageListRepositoryImpl @Inject constructor(private val storageReference: StorageReference) :
    ImageListRepository {
    override suspend fun loadImages(): DataModel<List<ImageModel>> {
        return try {
            val result = storageReference.child("images/").listAll().await().items
                .map {
                    val url = it.downloadUrl.await()
                    ImageModel(url.lastPathSegment.toString(), url.toString())
                }
            DataModel.Success(result)
        } catch (e: Exception) {
            return DataModel.Error(ErrorModel(e.message.toString()))
        }
    }
}