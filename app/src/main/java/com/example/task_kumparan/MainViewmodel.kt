package com.example.task_kumparan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_kumparan.Model.ModelData
import com.example.task_kumparan.Model.ModelPosts
import com.example.task_kumparan.Model.ModelUsers
import com.example.task_kumparan.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewmodel:ViewModel() {
    private val TAG :String ="MainVM"

    fun setDataPosts():LiveData<ArrayList<ModelPosts>> {
        val listUsers = MutableLiveData<ArrayList<ModelPosts>>()
        val arrayList = ArrayList<ModelPosts>()
        ApiConfig.endpoint.getDataPosts().enqueue(object:Callback<List<ModelPosts>>{
            override fun onResponse(
                call: Call<List<ModelPosts>>,
                response: Response<List<ModelPosts>>
            ) {
                val postsList = response.body()
                arrayList.addAll(postsList!!)
                listUsers.postValue(arrayList)
                Log.d(TAG, "onResponse: $listUsers")
            }

            override fun onFailure(call: Call<List<ModelPosts>>, t: Throwable) {
                Log.d(TAG, "onFailure: Data tidak ditemukan")
            }
        })
        return listUsers
    }

    fun getUsers():LiveData<ArrayList<ModelUsers>>{
        val listUsers = MutableLiveData<ArrayList<ModelUsers>>()
        val arrayUsers = ArrayList<ModelUsers>()
        ApiConfig.endpoint.getDataUser().enqueue(object: Callback<List<ModelUsers>>{
            override fun onResponse(
                call: Call<List<ModelUsers>>,
                response: Response<List<ModelUsers>>
            ) {
                val list = response.body()
                arrayUsers.addAll(list!!)
                listUsers.postValue(arrayUsers)
            }

            override fun onFailure(call: Call<List<ModelUsers>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return listUsers
    }

}