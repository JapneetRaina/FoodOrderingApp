package com.japneet.fooddeliveryapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.japneet.fooddeliveryapp.Activities.MainActivity
import com.japneet.fooddeliveryapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            Runnable {
                     startActivity(Intent(this@SplashActivity , MainActivity::class.java))
                finish()
            },1500)
    }
}