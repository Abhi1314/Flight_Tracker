package com.example.flighttracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityAirlineInfoBinding

class AirlineInfoActivity : AppCompatActivity() {
    private val airlineInfoBinding:ActivityAirlineInfoBinding by lazy{
        DataBindingUtil.setContentView(this,R.layout.activity_airline_info)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            airlineInfoBinding.toolBarLayout.name=getString(R.string.airline_info)
            airlineInfoBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            airlineInfoBinding.SearchAirlineBtn.setOnClickListener {
                val airline_name=airlineInfoBinding.edAirlineName.text.toString()
                if(airline_name.isEmpty()){
                    shortShowToast(getString(R.string.enter_airline_iata))
                }else{
                val intent= Intent(this@AirlineInfoActivity,AirlineDetailsActivity::class.java)
                intent.putExtra("airline_name",airline_name)
                startActivity(intent)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }


    }
}