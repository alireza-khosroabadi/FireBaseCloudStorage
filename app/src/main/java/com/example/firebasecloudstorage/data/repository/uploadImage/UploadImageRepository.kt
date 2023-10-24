package com.example.firebasecloudstorage.data.repository.uploadImage

import android.net.Uri
import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.uploadImage.UploadImageResponse

interface UploadImageRepository {
    suspend fun uploadImage(uri: Uri, fileName:String):DataModel<UploadImageResponse>
}