package com.example.task_kumparan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_kumparan.Model.ModelData
import com.example.task_kumparan.Model.ModelUsers
import com.example.task_kumparan.Model.UserAdapter
import com.example.task_kumparan.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mainViewmodel: MainViewmodel
    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var userAdapter : UserAdapter
    var data = ArrayList<ModelUsers>()
    var listData = ArrayList<ModelData>()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportActionBar?.title = TAG

        mainViewmodel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewmodel::class.java)
        setUpRecyclerView()

        userAdapter.setClickCallback(object: UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ModelData) {
                intent = Intent(this@MainActivity,DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID,data.userId)
                intent.putExtra(DetailActivity.EXTRA_TITLE,data.title)
                intent.putExtra(DetailActivity.EXTRA_DESC,data.deskripsi)
                intent.putExtra(DetailActivity.EXTRA_NAME,data.name)
                intent.putExtra(DetailActivity.EXTRA_EMAIL,data.email)
                intent.putExtra(DetailActivity.EXTRA_ADDRESS,data.address)
                intent.putExtra(DetailActivity.EXTRA_COMPANY,data.companyName)
                startActivity(intent)
            }

        })

        mainViewmodel.getUsers().observe(this,{
            data = it
            Log.d(TAG, "tul:$data ")
        })
        mainViewmodel.setDataPosts().observe(this,{
            if (it.isNotEmpty()) {
                setLoading(false)
                var items: ModelUsers
                for (item in data) {
                    items = ModelUsers(
                        item.name, item.username, item.email,
                        item.address, item.company
                    )

                    for (posts in it) {
                        val list = ModelData(
                            posts.userId, posts.id, posts.title,
                            posts.deskripsi, items.name, items.username, items.email,
                            "${items.address.street} ${items.address.suite}",
                            items.company.companyName
                        )
                        listData.addAll(listOf(list))
                        userAdapter.setList(listData)
                        Log.d(TAG, "lul:$listData ")
                    }

                }
            }else{
                setLoading(true)
            }
        })



    }

    fun setUpRecyclerView(){
        userAdapter = UserAdapter()
        activityMainBinding.apply {
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerview.setHasFixedSize(true)
            recyclerview.adapter = userAdapter
        }
    }
    private fun setLoading(state: Boolean) {
        if (state) {
            activityMainBinding.progressBar.visibility = View.VISIBLE
        } else {
            activityMainBinding.progressBar.visibility = View.GONE
        }
    }
}