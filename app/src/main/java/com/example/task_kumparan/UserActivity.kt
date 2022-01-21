package com.example.task_kumparan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_kumparan.Model.ModelAlbum
import com.example.task_kumparan.Model.UserAdapter
import com.example.task_kumparan.Model.UserAdapterAlbum
import com.example.task_kumparan.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var userActivityBinding:ActivityUserBinding
    private lateinit var userAdapter: UserAdapterAlbum
    private lateinit var userViewModel:UserActivityViewModel
    private val TAG = "Detail User"
    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_COMPANY = "extra_company"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userActivityBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userActivityBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = TAG

        val userId = intent.getStringExtra(DetailActivity.EXTRA_ID)
        val name = intent.getStringExtra(DetailActivity.EXTRA_NAME)
        val email = intent.getStringExtra(DetailActivity.EXTRA_EMAIL)
        val address = intent.getStringExtra(DetailActivity.EXTRA_ADDRESS)
        val company = intent.getStringExtra(DetailActivity.EXTRA_COMPANY)

        userActivityBinding.userName.text = name
        userActivityBinding.email.text = email
        userActivityBinding.address.text = address
        userActivityBinding.companyName.text = company

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserActivityViewModel::class.java)
        setUpRecyclerView()
        userAdapter.setClickCallback(object : UserAdapterAlbum.OnItemClickCallback{
            override fun onItemClicked(data: ModelAlbum) {
                val intent = Intent(this@UserActivity,Detail_Photo::class.java)
                intent.putExtra(Detail_Photo.EXTRA_TITLE,data.title)
                intent.putExtra(Detail_Photo.EXTRA_PHOTO,data.thumbnailUrl)
                startActivity(intent)
            }

        })

        userViewModel.getAlbums().observe(this,{
            userAdapter.setList(it)
        })

    }

    fun setUpRecyclerView(){
        userAdapter = UserAdapterAlbum()
        userActivityBinding.apply {
            recyclerview.layoutManager = LinearLayoutManager(this@UserActivity)
            recyclerview.setHasFixedSize(true)
            recyclerview.adapter = userAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finishAfterTransition()
        return super.onSupportNavigateUp()
    }
}