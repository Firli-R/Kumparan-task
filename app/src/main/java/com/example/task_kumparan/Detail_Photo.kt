package com.example.task_kumparan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.task_kumparan.databinding.ActivityDetailPhotoBinding
import com.squareup.picasso.Picasso

class Detail_Photo : AppCompatActivity() {
    private lateinit var binding:ActivityDetailPhotoBinding
    companion object{
        const val TAG = "Photo"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_PHOTO = "extra_photo"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = TAG

        val title = intent.getStringExtra(EXTRA_TITLE)
        val photo_img = intent.getStringExtra(EXTRA_PHOTO)
        Log.d(TAG, "ty: ")

        binding.title.text = title
        Picasso.get().load(photo_img).into(binding.photo)
    }
    override fun onSupportNavigateUp(): Boolean {
        finishAfterTransition()
        return super.onSupportNavigateUp()
    }
}