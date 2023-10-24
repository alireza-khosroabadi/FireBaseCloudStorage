package com.example.firebasecloudstorage.data.repository.deleteImage

import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.baseModel.ErrorModel
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DeleteImageRepositoryImpl @Inject constructor(private val firebaseStorage: StorageReference) :
    DeleteImageRepository {
    override suspend fun deleteImage(imageName: String): DataModel<Unit> {
        return try {
            firebaseStorage.child(imageName).delete().await()
            DataModel.Success(Unit)
        } catch (e: Exception) {
            DataModel.Error(ErrorModel(e.message.toString()))
        }
    }
}