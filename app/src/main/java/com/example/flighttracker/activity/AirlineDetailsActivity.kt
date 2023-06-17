package com.example.flighttracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.airlinedb
import com.example.flighttracker.api.countryDB
import com.example.flighttracker.databinding.ActivityAirlineDetailsBinding
import com.example.flighttracker.databinding.ActivityAirlineInfoBinding
import com.example.flighttracker.databinding.ActivityCountrycodeDetalisBinding
import com.example.flighttracker.utils.gone
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirlineDetailsActivity : AppCompatActivity() {
    private val airlineDetalisBinding: ActivityAirlineDetailsBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_airline_details)
    }
    var airline_name:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            airlineDetalisBinding.toolBarLayout.name=getString(R.string.airline_details)
            airlineDetalisBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            airline_name=intent.getStringExtra("airline_name")!!
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<airlinedb> = service!!.getAirlineDATA(BuildConfig.API_KEY)
            call.enqueue(object : Callback<airlinedb> {
                override fun onResponse(call: Call<airlinedb>, response: Response<airlinedb>) {
                    Log.d("airlinedb",response.body().toString())
                    if(response.body()!!.response.isNotEmpty()){
                        for(element in response.body()!!.response){
                            if (element.name.contains(airline_name,true)){
                                airlineDetalisBinding.airlineName.text=element.name
                                airlineDetalisBinding.iataCodeInput.text=element.iata_code
                                airlineDetalisBinding.icaoCodeInput.text=element.icao_code
                            }else{
                                gone(airlineDetalisBinding.llAirlineDetail)
                                visible(airlineDetalisBinding.nodatafound.root)
                            }
                        }

                    }else{
                        gone(airlineDetalisBinding.llAirlineDetail)
                        visible(airlineDetalisBinding.nodatafound.root)
                    }

                }

                override fun onFailure(call: Call<airlinedb>, t: Throwable) {
                    Log.d("error",t.toString())
                    gone(airlineDetalisBinding.llAirlineDetail)
                    visible(airlineDetalisBinding.nodatafound.root)

                }
            })
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}