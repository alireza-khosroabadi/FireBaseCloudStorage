package com.example.firebasecloudstorage.ui.home

import androidx.annotation.StringRes
import com.example.firebasecloudstorage.R

enum class ScreenRouts(val rout:String, @StringRes val title: Int, val needFab:Boolean = false) {
    ImageList(rout = "ImageList",R.string.screen_title_image_list, true),
    UploadImage(rout = "UploadImage",R.string.screen_title_upload_image),
    FullScreenImage(rout = "FullScreenImage/{imageUrl}",R.string.screen_title_upload_image);

    companion object{
        fun findByRout(rout: String):ScreenRouts?{
            return when(rout){
                ImageList.rout -> ImageList
                UploadImage.rout -> UploadImage
                FullScreenImage.rout -> FullScreenImage
                else ->null
            }
        }
    }
}