package com.example.firebasecloudstorage.data.imageList

import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.baseModel.ErrorModel
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageListFirebaseStorage @Inject constructor(private val storageReference: StorageReference) :
    ImageList {
    override suspend fun loadImages(): DataModel<List<String>> {
        return try {
            val result =  storageReference.child("images/").listAll().await().items
                .map {
                    it.downloadUrl.await().toString()
                }
            DataModel.Success(result)
        } catch (e: Exception){
            return DataModel.Error(ErrorModel(e.message.toString()))
        }
    }
}