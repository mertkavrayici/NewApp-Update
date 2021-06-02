package com.androiddevs.mvvmnewsapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.androiddevs.mvvmnewsapp.R

class SplashActivity : AppCompatActivity() {
    lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler= Handler()
        handler.postDelayed({
            val intent =Intent(this,NewsActivity::class.java)
            startActivity(intent)
            finish()
        }
        ,2000)
    }
}