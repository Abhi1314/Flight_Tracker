package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.AirlineFleetAdaptar
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.airlineFleetDB
import com.example.flighttracker.databinding.ActivityAirlineFleetBinding
import com.example.flighttracker.databinding.ActivityAirlineFleetDetailsBinding
import com.example.flighttracker.response.ResponseAirlineDB
import com.example.flighttracker.response.ResponseFleet
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirlineFleetDetailsActivity : AppCompatActivity() {
    private val airlineFleetDetailsBinding:ActivityAirlineFleetDetailsBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_airline_fleet_details)
    }
    var airline_iata=""
    var AirlineDetails = ArrayList<ResponseFleet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            airlineFleetDetailsBinding.toolBarLayout.name=getString(R.string.airline_fleet_details)
            airline_iata=intent.getStringExtra("airline_iata").toString()
            airlineFleetDetailsBinding.toolBarLayout.backImage.setOnClickListener {
                finish()
            }
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<airlineFleetDB> = service!!.getAirlineFleet(
                 BuildConfig.API_KEY,airline_iata)
            call.enqueue(object : Callback<airlineFleetDB> {
                override fun onResponse(call: Call<airlineFleetDB>, response: Response<airlineFleetDB>)
                {
                    Log.d("response", response.body().toString())
                    if(response.body()!!.response.isNotEmpty()) {

                    AirlineDetails = response.body()!!.response
                    airlineFleetDetailsBinding.rvFleetDetails.adapter=AirlineFleetAdaptar(this@AirlineFleetDetailsActivity, AirlineDetails)
                    }else{
                        visible(airlineFleetDetailsBinding.nodatafound.root)
                    }
                }

                override fun onFailure(call: Call<airlineFleetDB>, t: Throwable)
                {
                visible(airlineFleetDetailsBinding.nodatafound.root)
                }
            })
           }catch (e:Exception){
            e.printStackTrace()
        }
    }
}