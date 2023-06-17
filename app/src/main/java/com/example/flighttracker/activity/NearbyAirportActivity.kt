package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.NearAriportAdaptar
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.airportNearby
import com.example.flighttracker.databinding.ActivityNearbyAirportBinding
import com.example.flighttracker.utils.GpsTracker
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback


class NearbyAirportActivity : AppCompatActivity() {
    private val nearbyAirportBinding: ActivityNearbyAirportBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_nearby_airport)
    }

    var distance = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            nearbyAirportBinding.toolBarLayout.name = "Near by Airport"
            nearbyAirportBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            getLocation()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    fun getLocation() {
        val gpsTracker = GpsTracker(this)
        if (gpsTracker.canGetLocation()) {
            val lat: Double = gpsTracker.latitude
            val lng: Double = gpsTracker.longitude
            getNearby(BuildConfig.API_KEY, lat, lng, distance)

        } else {
            gpsTracker.showSettingsAlert()
        }
    }
    fun getNearby( api_key: String, lat: Double, lng: Double, distance: Int) {

        val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
        val call: Call<airportNearby> = service!!.getNearby(api_key, lat, lng, distance)
        call.enqueue(object : Callback<airportNearby> {
            override fun onResponse(
                call: Call<airportNearby>, response: retrofit2.Response<airportNearby>
            ) {
                Log.d("airportresponse", response.body().toString())
                val airportlist = response.body()!!.response
                if (response.body()!!.response.airports.isNotEmpty()) {
                    nearbyAirportBinding.rvNearbyAirports.adapter=NearAriportAdaptar(this@NearbyAirportActivity, airportlist)
                } else {
                    visible(nearbyAirportBinding.nodatafound.root)
                }

            }

            override fun onFailure(call: Call<airportNearby>, t: Throwable) {
                Log.d("error", t.toString())
                visible(nearbyAirportBinding.nodatafound.root)

            }
        })
    }



}
