package com.example.firebasecloudstorage.ui.fullScreenImage.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.firebasecloudstorage.ui.fullScreenImage.FullScreenImage
import com.example.firebasecloudstorage.ui.fullScreenImage.FullScreenImageViewModel
import com.example.firebasecloudstorage.ui.home.ScreenRouts
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal const val imageUrlArg = "imageUrl"
fun NavGraphBuilder.fullScreenImage() {
    composable(ScreenRouts.FullScreenImage.rout) {
        val viewModel = hiltViewModel<FullScreenImageViewModel>()
        val imageUrl = viewModel.imageUrlState.collectAsStateWithLifecycle()
        FullScreenImage(imageUrl.value)
    }
}

fun NavController.navigateToFullScreenImage(imageUrl: String) {
    val encodedUrl =  URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
    this.navigate(ScreenRouts.FullScreenImage.rout.replace("{$imageUrlArg}",encodedUrl))
}

internal class FullScreenImageArgs(imageUrl: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        imageUrl = savedStateHandle.get<String>(
            imageUrlArg
        ).toString()
    )
}