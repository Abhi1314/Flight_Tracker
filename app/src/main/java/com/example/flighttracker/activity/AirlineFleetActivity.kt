package com.example.flighttracker.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityAirlineFleetBinding

class AirlineFleetActivity : AppCompatActivity() {
    private val airlineFleetBinding: ActivityAirlineFleetBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_airline_fleet)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            airlineFleetBinding.toolBarLayout.name=getString(R.string.airline_fleet)
            airlineFleetBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            airlineFleetBinding.SearchAirlineBtn.setOnClickListener {
                val iatacode=airlineFleetBinding.edIataCodeAirline.text.toString()
                if(iatacode.isEmpty()){
                    shortShowToast(getString(R.string.enter_airline_iata))                }else{
                    val intent= Intent(this@AirlineFleetActivity,AirlineFleetDetailsActivity::class.java)
                    intent.putExtra("airline_iata",iatacode)
                    startActivity(intent)
                }

            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}