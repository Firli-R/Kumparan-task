package com.example.task_kumparan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_kumparan.Model.ModelAlbum
import com.example.task_kumparan.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivityViewModel: ViewModel() {
    private val TAG :String ="UserVM"

    fun getAlbums():LiveData<ArrayList<ModelAlbum>>{
        val listAlbum = MutableLiveData<ArrayList<ModelAlbum>>()
        val arrayAlbum = ArrayList<ModelAlbum>()
        ApiConfig.endpoint.getAlbums().enqueue(object :Callback<List<ModelAlbum>>{
            override fun onResponse(
                call: Call<List<ModelAlbum>>,
                response: Response<List<ModelAlbum>>
            ) {
                val data = response.body()
                if (data != null) {
                    arrayAlbum.addAll(data)
                }
                listAlbum.postValue(arrayAlbum)
                Log.d(TAG, "lol:$arrayAlbum ")
            }

            override fun onFailure(call: Call<List<ModelAlbum>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return listAlbum
    }
}