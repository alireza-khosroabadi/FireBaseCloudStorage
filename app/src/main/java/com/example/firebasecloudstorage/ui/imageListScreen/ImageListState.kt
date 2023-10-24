package com.example.firebasecloudstorage.ui.imageListScreen

import com.example.firebasecloudstorage.data.model.image.ImageModel

sealed class ImageListState {
    data object Loading : ImageListState()
    data object EmptyList : ImageListState()
    data class ImageList(val data: List<ImageModel>) : ImageListState()

    data class Error(val errorMessage: String) : ImageListState()
}