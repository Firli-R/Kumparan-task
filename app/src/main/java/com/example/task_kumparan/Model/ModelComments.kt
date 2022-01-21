package com.example.task_kumparan.Model

import com.google.gson.annotations.SerializedName

data class ModelComments(
    var postId : String,
    var id : String,
    var name : String,
    @SerializedName("body")
    var deskripsi : String
)
