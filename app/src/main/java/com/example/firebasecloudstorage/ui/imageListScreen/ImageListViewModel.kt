package com.example.firebasecloudstorage.ui.imageListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasecloudstorage.data.repository.imageList.ImageListRepository
import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import com.example.firebasecloudstorage.data.model.image.ImageModel
import com.example.firebasecloudstorage.data.repository.deleteImage.DeleteImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageListRepository: ImageListRepository,
    private val deleteImageRepository: DeleteImageRepository
) : ViewModel() {
    private val _imageListState = MutableStateFlow<ImageListState>(ImageListState.Loading)
    val imageListState = _imageListState.asStateFlow()

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            _imageListState.value = ImageListState.Loading
            val resultList = imageListRepository.loadImages()
            when (resultList) {
                is DataModel.Error -> _imageListState.value =
                    ImageListState.Error(resultList.error.errorMessage)

                is DataModel.Success -> handleResult(resultList.data)
            }
        }
    }

    private fun handleResult(data: List<ImageModel>) {
        if (data.isEmpty()) {
            _imageListState.value = ImageListState.EmptyList
        } else {
            _imageListState.value = ImageListState.ImageList(data)
        }
    }

    fun deleteItem(imageName:String){
        viewModelScope.launch {
            deleteImageRepository.deleteImage(imageName)
            loadImages()
        }
    }

}