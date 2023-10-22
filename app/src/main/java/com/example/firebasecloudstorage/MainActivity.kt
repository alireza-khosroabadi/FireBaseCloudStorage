package com.example.firebasecloudstorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebasecloudstorage.ui.home.HomeAppScreen
import com.example.firebasecloudstorage.ui.theme.FireBaseCloudStorageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireBaseCloudStorageTheme {
                HomeAppScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FireBaseCloudStorageTheme {
        //UploadImageScreen()
    }
}