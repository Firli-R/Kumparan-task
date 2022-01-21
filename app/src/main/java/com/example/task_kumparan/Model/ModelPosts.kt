package com.example.task_kumparan.Model

import com.google.gson.annotations.SerializedName

data class ModelPosts(
    var userId : String,
    var id : String,
    var title : String,
    @SerializedName("body")
    var deskripsi : String
)
