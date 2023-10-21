package com.example.firebasecloudstorage.data.dataStore

import android.net.Uri
import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.baseModel.ErrorModel
import com.example.firebasecloudstorage.data.model.uploadImage.UploadImageResponse
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseStorage @Inject constructor(private val storageReference: StorageReference) :
    UploadImage {
    override suspend fun uploadImage(uri: Uri, fileName: String): DataModel<UploadImageResponse> {
        return try {
            storageReference.child("images/$fileName").putFile(uri).await()
            DataModel.Success(UploadImageResponse("Image Uploaded"))
        } catch (e: Exception) {
            DataModel.Error(ErrorModel(e.message.toString()))
        }
    }

}