package com.example.task_kumparan.Model

import com.google.gson.annotations.SerializedName

data class ModelAlbum (
    val albumId:String,
    val title :String,
    @SerializedName("url")
    val url :String,
    val thumbnailUrl :String
        )