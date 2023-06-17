package com.example.flighttracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.flighttracker.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        try {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}