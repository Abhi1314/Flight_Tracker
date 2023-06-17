package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.AirportInfoAdaptar
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.airportInfo
import com.example.flighttracker.api.airportNearby
import com.example.flighttracker.databinding.ActivityAirportInfoBinding
import com.example.flighttracker.response.ResponseAirportInfo
import com.example.flighttracker.response.ResponseNearbyAirport
import com.example.flighttracker.utils.gone
import com.example.flighttracker.utils.visible
import com.google.android.gms.common.util.ArrayUtils.contains
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirportInfoActivity : AppCompatActivity() {
    private val airportInfoBinding: ActivityAirportInfoBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_airport_info)
    }
     var airportname=""
    var airportlist=ArrayList<ResponseAirportInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            airportInfoBinding.toolBarLayout.name = getString(R.string.airportInfo)
            airportInfoBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
                airportname=intent.getStringExtra("airportname").toString()
                    val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
                    val call: Call<airportInfo> = service!!.getAirportInfo(
                        BuildConfig.API_KEY
                    )
                    call.enqueue(object : Callback<airportInfo> {
                        override fun onResponse(call: Call<airportInfo>, response: Response<airportInfo>) {
                            Log.d("airportresponse", response.body().toString())
                            if (response.body()!!.response.isNotEmpty()) {
                                for ( element in response.body()!!.response){
                                    if (element.name.contains(airportname,true)){
                                        airportlist.addAll(
                                            listOf(
                                                ResponseAirportInfo(name = element.name,
                                                    country_code = element.country_code, icao_code = element.icao_code, lat = element.lat, lng = element.lng)
                                            )
                                        )
                                    }
                                }
                                airportInfoBinding.rvAirportresult.adapter=AirportInfoAdaptar(this@AirportInfoActivity,airportlist)
                            }
                            else {
                                visible(airportInfoBinding.nodatafound.root)
                            }
                        }

                        override fun onFailure(call: Call<airportInfo>, t: Throwable) {
                            Log.d("error", t.toString())
                            visible(airportInfoBinding.nodatafound.root)
                        }
                    })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

