package com.example.task_kumparan.network


import com.example.task_kumparan.Model.ModelAlbum
import com.example.task_kumparan.Model.ModelComments
import com.example.task_kumparan.Model.ModelPosts
import com.example.task_kumparan.Model.ModelUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getDataPosts(): Call<List<ModelPosts>>

    @GET("users")
    fun getDataUser(): Call<List<ModelUsers>>

    @GET("posts/{userId}/comments")
    fun getComments(
        @Path("userId") userId:String
    ):Call<List<ModelComments>>

    @GET("photos")
    fun getAlbums():Call<List<ModelAlbum>>
}