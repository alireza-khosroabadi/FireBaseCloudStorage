package com.example.firebasecloudstorage.ui.imageListScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.firebasecloudstorage.R
import com.example.firebasecloudstorage.data.model.image.ImageModel
import com.example.firebasecloudstorage.ui.base.AppAlertDialog


@Composable
fun ImageListScreen(
    imageListState: ImageListState,
    onNavigateToUploadImage: () -> Unit,
    onNavigateToFullScreenImage: (imageUrl: String) -> Unit,
    reloadImages: () -> Unit,
    onDeleteItemClick: (imageName: String) -> Unit
) {
    when (imageListState) {
        ImageListState.EmptyList -> EmptyList(onNavigateToUploadImage)
        is ImageListState.Error -> ShowError(error = imageListState, reloadImages)
        is ImageListState.ImageList -> ShowImageList(
            imageList = imageListState.data,
            onNavigateToUploadImage,
            onDeleteItemClick,
            onNavigateToFullScreenImage
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
fun ShowImageList(
    imageList: List<ImageModel>,
    onNavigateToUploadImage: () -> Unit,
    onDeleteItemClick: (imageName: String) -> Unit,
    onNavigateToFullScreenImage: (imageUrl: String) -> Unit
) {
    var dialogState by remember { mutableStateOf(false) }
    var selectedImageToDelete by remember {
        mutableStateOf<String?>(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(imageList,
                key = { it.imageName }) {
                ImageItem(it, onItemLongClick = {
                    dialogState = true
                    selectedImageToDelete = it.imageName
                }) {
                    onNavigateToFullScreenImage(it.imageUrl)
                }
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

    if (dialogState) {
        AppAlertDialog(
            onConfirmation = {
                dialogState = false
                selectedImageToDelete?.let { onDeleteItemClick(it) }
                selectedImageToDelete = null
            },
            onDismiss = {
                dialogState = false
                selectedImageToDelete = null
            },
            confirmBtnText = stringResource(id = R.string.delete_dialog_confirm_btn),
            dismissBtnText = stringResource(id = R.string.delete_dialog_cancle_btn),
            title = stringResource(id = R.string.delete_dialog_title),
            body = stringResource(id = R.string.delete_dialog_Body)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImageItem(it: ImageModel, onItemLongClick: () -> Unit, onItemClick: () -> Unit) {

    AsyncImage(
        modifier = Modifier
            .padding(8.dp)
            .combinedClickable(
                onClick = onItemClick,
                onLongClick = onItemLongClick
            ),
        model = it.imageUrl,
        contentDescription = null
    )
}