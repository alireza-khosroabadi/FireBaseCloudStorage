package com.example.firebasecloudstorage.data.uploadImage

import android.net.Uri
import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.uploadImage.UploadImageResponse

interface UploadImage {
    suspend fun uploadImage(uri: Uri, fileName:String):DataModel<UploadImageResponse>
}