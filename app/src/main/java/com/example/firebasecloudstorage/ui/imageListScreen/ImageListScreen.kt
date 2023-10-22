package com.example.firebasecloudstorage.ui.imageListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ImageListScreen(imageListState: ImageListState, onNavigateToUploadImage: () -> Unit, reloadImages: () -> Unit) {
    when (imageListState) {
        ImageListState.EmptyList -> EmptyList(onNavigateToUploadImage)
        is ImageListState.Error -> ShowError(error = imageListState, reloadImages)
        is ImageListState.ImageList -> ShowImageList(
            imageList = imageListState.data,
            onNavigateToUploadImage
        )

        ImageListState.Loading -> ImageListLoading()
    }
}

@Composable
fun ImageListLoading() {

    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(50.dp)
                .align(alignment = Alignment.Center),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun EmptyList(onAddNewItem: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "EmptyList"
        )

        Button(onClick = onAddNewItem) {
            Text(text = "Upload New Image")
        }
    }
}

@Composable
fun ShowError(error: ImageListState.Error, reloadImages: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error.errorMessage
        )

        Button(onClick = reloadImages) {
            Text(text = "Upload New Image")
        }
    }
}

@Composable
fun ShowImageList(imageList: List<String>, onNavigateToUploadImage: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
            items(imageList,
                key = { it }) {
                AsyncImage(
                    modifier = Modifier.padding(8.dp),
                    model = it,
                    contentDescription = null,
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.BottomEnd),
            shape = CircleShape,
            onClick = onNavigateToUploadImage,
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
    }
}