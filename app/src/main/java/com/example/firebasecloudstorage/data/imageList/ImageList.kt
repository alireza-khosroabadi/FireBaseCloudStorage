package com.example.firebasecloudstorage.data.imageList

import com.example.firebasecloudstorage.data.model.baseModel.DataModel

interface ImageList {
    suspend fun loadImages(): DataModel<List<String>>
}