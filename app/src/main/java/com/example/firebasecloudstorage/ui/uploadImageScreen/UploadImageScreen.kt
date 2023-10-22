package com.example.firebasecloudstorage.ui.uploadImageScreen

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firebasecloudstorage.utils.toBitmap
import kotlinx.coroutines.launch


@Composable
fun UploadImageScreen(
    uploadImageState: UploadImageUiState,
    onNavigateBack: () -> Unit,
    snackbarHostState : SnackbarHostState = remember { SnackbarHostState() },
    uploadImage: (uri: Uri, fileName: String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    bitmap = imageUri.toBitmap(context)

    if (uploadImageState is UploadImageUiState.ImageUploaded) {
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message = uploadImageState.message)
            }
            if (uploadImageState.status == UploadStatus.SUCCESS)
                imageUri = null
        }
    }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(border = BorderStroke(2.dp, Color.Black))
                    .align(alignment = Alignment.TopCenter)
            ) {
                bitmap?.asImageBitmap()?.let { it1 ->
                    Image(
                        bitmap = it1,
                        contentDescription = "search",
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 50.dp),
                    onClick = { launcher.launch("image/*") }) {
                    Text(text = "Select Image")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    if (uploadImageState is UploadImageUiState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(50.dp)
                                .align(alignment = Alignment.Center),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            trackColor = MaterialTheme.colorScheme.secondary,
                        )
                    } else {
                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 50.dp),
                            enabled = imageUri != null,
                            onClick = {
                                uploadImage(imageUri!!, imageUri!!.pathSegments.last())
                            }) {
                            Text(text = "Upload Image")
                        }
                    }
            }
        }
    }

}