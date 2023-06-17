package com.example.flighttracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.MainActivity
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private val startBinding:ActivityStartBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_start)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            startBinding.tvStart.setOnClickListener {
                val intent= Intent(this@StartActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }

    }
}