package com.example.task_kumparan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class SplashScreenActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        val botAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        val btv: TextView = findViewById(R.id.botTextView)

        btv.startAnimation(botAnimation)

        val timeout = 4000
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        },timeout.toLong())

    }
}