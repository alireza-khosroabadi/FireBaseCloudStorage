package com.example.firebasecloudstorage.ui.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.firebasecloudstorage.ui.fullScreenImage.navigation.fullScreenImage
import com.example.firebasecloudstorage.ui.fullScreenImage.navigation.navigateToFullScreenImage
import com.example.firebasecloudstorage.ui.imageListScreen.navigation.imageListScreen
import com.example.firebasecloudstorage.ui.uploadImageScreen.navigation.navigateToUploadImage
import com.example.firebasecloudstorage.ui.uploadImageScreen.navigation.uploadImage

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState
) {
    NavHost(navController = navController, startDestination = ScreenRouts.ImageList.rout) {
        imageListScreen(
            onNavigateToUploadImage = { navController.navigateToUploadImage() },
            onNavigateToFullScreenImage = { navController.navigateToFullScreenImage(imageUrl = it) }
        )
        uploadImage(snackbarHostState = snackbarHostState)

        fullScreenImage()
    }
}