package com.example.firebasecloudstorage.ui.uploadImageScreen

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasecloudstorage.data.dataStore.UploadImage
import com.example.firebasecloudstorage.data.model.baseModel.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UploadImageUiState {
    data object Initialize : UploadImageUiState()
    data object Loading : UploadImageUiState()
    data class ImageUploadedSuccess(val message: String) : UploadImageUiState()
    data class ImageUploadFailed(val message: String) : UploadImageUiState()
}

@HiltViewModel
class UploadFileViewModel @Inject constructor(private val uploadImage: UploadImage) : ViewModel() {

    private val _uploadFileState = MutableStateFlow<UploadImageUiState>(UploadImageUiState.Initialize)
    val uploadFileStatus = _uploadFileState.asStateFlow()


    fun uploadFile(fileName: String, uri: Uri) {
        viewModelScope.launch {
            _uploadFileState.value = UploadImageUiState.Loading
            val uploadResult = uploadImage.uploadImage(uri, fileName)
            if (uploadResult is DataModel.Success)
                _uploadFileState.value =
                    UploadImageUiState.ImageUploadedSuccess(uploadResult.data.message)
            else if (uploadResult is DataModel.Error)
                _uploadFileState.value =
                    UploadImageUiState.ImageUploadFailed(uploadResult.error.errorMessage)
        }
    }

}