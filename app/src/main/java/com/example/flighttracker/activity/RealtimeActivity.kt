package com.example.flighttracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityRealtimeBinding

class RealtimeActivity : AppCompatActivity() {
    private val realtimeBinding:ActivityRealtimeBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_realtime)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            realtimeBinding.toolBarLayout.name=getString(R.string.realtime_flight)
            realtimeBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
         realtimeBinding.SearchFlighteBtn.setOnClickListener {
             val flight_iata=realtimeBinding.edFlightIatacode.text.toString()
             if (flight_iata.isEmpty()){
                 shortShowToast(getString(R.string.enter_flight_iatacode))
             }else{
                 val intent=Intent(this@RealtimeActivity,RealtimeDetailsActivity::class.java)
                 intent.putExtra("flight_iata",flight_iata)
                 startActivity(intent)
             }
         }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}