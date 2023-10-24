package com.example.firebasecloudstorage.ui.fullScreenImage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.firebasecloudstorage.ui.fullScreenImage.navigation.imageUrlArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullScreenImageViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
    val imageUrlState = savedStateHandle.getStateFlow(imageUrlArg, "")
}