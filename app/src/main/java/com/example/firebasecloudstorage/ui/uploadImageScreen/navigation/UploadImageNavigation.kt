package com.example.firebasecloudstorage.ui.uploadImageScreen.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.firebasecloudstorage.ui.home.ScreenRouts

import com.example.firebasecloudstorage.ui.uploadImageScreen.UploadFileViewModel
import com.example.firebasecloudstorage.ui.uploadImageScreen.UploadImageScreen

fun NavGraphBuilder.uploadImage(snackbarHostState: SnackbarHostState, onNavigateBack: () -> Unit) {
    composable(ScreenRouts.UploadImage.name) {
        val viewModel = hiltViewModel<UploadFileViewModel>()
        val uploadFileState by viewModel.uploadFileStatus.collectAsStateWithLifecycle()
        UploadImageScreen(
            uploadImageState = uploadFileState,
            onNavigateBack = onNavigateBack,
            snackbarHostState= snackbarHostState) { uri, fileName ->
            viewModel.uploadFile(fileName, uri)
        }
    }
}

fun NavController.navigateToUploadImage() {
    this.navigate(ScreenRouts.UploadImage.name)
}