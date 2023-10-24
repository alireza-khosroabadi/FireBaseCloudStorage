package com.example.firebasecloudstorage.ui.imageListScreen.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.firebasecloudstorage.ui.home.ScreenRouts
import com.example.firebasecloudstorage.ui.imageListScreen.ImageListScreen
import com.example.firebasecloudstorage.ui.imageListScreen.ImageListViewModel

fun NavGraphBuilder.imageListScreen(onNavigateToUploadImage: () -> Unit, onNavigateToFullScreenImage: (imageUrl:String) -> Unit) {
    composable(ScreenRouts.ImageList.rout) {
        val viewModel = hiltViewModel<ImageListViewModel>()
        val imageListState by viewModel.imageListState.collectAsStateWithLifecycle()
        ImageListScreen(
            imageListState,
            onNavigateToUploadImage,
            reloadImages = { viewModel.loadImages() },
            onDeleteItemClick = {
                viewModel.deleteItem(it)
            },
            onNavigateToFullScreenImage = {
                onNavigateToFullScreenImage(it)
            }
        )
    }
}