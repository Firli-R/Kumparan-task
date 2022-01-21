package com.example.task_kumparan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_kumparan.Model.ModelData
import com.example.task_kumparan.Model.ModelComments
import com.example.task_kumparan.network.ApiConfig
import retrofit2.Call
import retrofit2.Response


class DetailViewModel:ViewModel() {
    private val TAG :String ="DetailVM"

    fun getComments(id:String):LiveData<ArrayList<ModelData>>{
        val listComments = MutableLiveData<ArrayList<ModelData>>()
        val arrayList = ArrayList<ModelData>()
        ApiConfig.endpoint.getComments(id).enqueue(object : retrofit2.Callback<List<ModelComments>>{
            override fun onResponse(
                call: Call<List<ModelComments>>,
                response: Response<List<ModelComments>>
            ) {
                val list = response.body()
                for (item in list!!){
                    val items = ModelData(item.postId,item.id,item.name,item.deskripsi,"","","","","")
                    arrayList.add(items)
                    Log.d(TAG, "hh: ")
                }
                listComments.postValue(arrayList)
            }

            override fun onFailure(call: Call<List<ModelComments>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return listComments
    }
//    fun getUsers():LiveData<ModelUsers>{
//        val listUsers = MutableLiveData<ModelUsers>()
//        var items : ModelUsers
//        ApiConfig.endpoint.getDataUser().enqueue(object: Callback<List<ModelUsers>>{
//            override fun onResponse(
//                call: Call<List<ModelUsers>>,
//                response: Response<List<ModelUsers>>
//            ) {
//                val data = response.body()
//                for (item in data!!){
//                    items = ModelUsers(item.username,item.company)
//                    listUsers.postValue(items)
//                }
//            }
//
//            override fun onFailure(call: Call<List<ModelUsers>>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message}")
//            }
//        })
//        return listUsers
//    }
}