package com.example.firebasecloudstorage.data.repository.deleteImage

import com.example.firebasecloudstorage.data.model.baseModel.DataModel

interface DeleteImageRepository {
    suspend fun deleteImage(imageName:String):DataModel<Unit>
}