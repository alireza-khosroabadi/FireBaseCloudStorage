package com.example.firebasecloudstorage.ui.home

import androidx.annotation.StringRes
import com.example.firebasecloudstorage.R

enum class ScreenRouts(@StringRes val title: Int , val needFab:Boolean = false) {
    ImageList(R.string.screen_title_image_list, true),
    UploadImage(R.string.screen_title_upload_image);
}