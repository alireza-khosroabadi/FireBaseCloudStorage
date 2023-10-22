package com.example.firebasecloudstorage.ui.imageListScreen

sealed class ImageListState {
    data object Loading : ImageListState()
    data object EmptyList : ImageListState()
    data class ImageList(val data: List<String>) : ImageListState()

    data class Error(val errorMessage: String) : ImageListState()
}