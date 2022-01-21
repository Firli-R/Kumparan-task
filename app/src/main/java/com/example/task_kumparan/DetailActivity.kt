package com.example.task_kumparan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_kumparan.Model.UserAdapter
import com.example.task_kumparan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val TAG = "Detail"
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailViewModel:DetailViewModel
    private lateinit var  userAdapter: UserAdapter
    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_COMPANY = "extra_company"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = TAG

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        val userId = intent.getStringExtra(EXTRA_ID)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val deskripsi = intent.getStringExtra(EXTRA_DESC)
        val name = intent.getStringExtra(EXTRA_NAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val address = intent.getStringExtra(EXTRA_ADDRESS)
        val company = intent.getStringExtra(EXTRA_COMPANY)

        activityDetailBinding.userName.text = name
        activityDetailBinding.userName.setOnClickListener {
            activityDetailBinding.userName.setTextColor(R.color.grey)
            val moveIntent = Intent(this@DetailActivity,UserActivity::class.java)
            moveIntent.putExtra(UserActivity.EXTRA_ID,userId)
            moveIntent.putExtra(UserActivity.EXTRA_NAME,name)
            moveIntent.putExtra(UserActivity.EXTRA_EMAIL,email)
            moveIntent.putExtra(UserActivity.EXTRA_ADDRESS,address)
            moveIntent.putExtra(UserActivity.EXTRA_COMPANY,company)
            startActivity(moveIntent)
        }
        activityDetailBinding.title.text = "title: $title"
        activityDetailBinding.deskripsi.text = "body: $deskripsi"

        setUpRecyclerView()

        detailViewModel.getComments(userId.toString()).observe(this,{
            if (it.isNotEmpty()){
                userAdapter.setList(it)
            }
        })

    }

    fun setUpRecyclerView(){
        userAdapter = UserAdapter()
        activityDetailBinding.apply {
            recyclerview.layoutManager = LinearLayoutManager(this@DetailActivity)
            recyclerview.setHasFixedSize(true)
            recyclerview.adapter = userAdapter
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finishAfterTransition()
        return super.onSupportNavigateUp()
    }
}