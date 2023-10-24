package com.example.firebasecloudstorage.data.repository.imageList

import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.image.ImageModel

interface ImageListRepository {
    suspend fun loadImages(): DataModel<List<ImageModel>>
}