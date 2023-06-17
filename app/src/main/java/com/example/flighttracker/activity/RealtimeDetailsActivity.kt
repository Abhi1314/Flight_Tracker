package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.flightinfo
import com.example.flighttracker.databinding.ActivityRealtimeDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RealtimeDetailsActivity : AppCompatActivity() {
    private val realtimeDetailsBinding: ActivityRealtimeDetailsBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_realtime_details)
    }
    var flight_iata = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            realtimeDetailsBinding.toolBarLayout.name = getString(R.string.realtime_flight_details)
            realtimeDetailsBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            flight_iata = intent.getStringExtra("flight_iata")!!
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<flightinfo> = service!!.getFlightInfo(BuildConfig.API_KEY,flight_iata)
            call.enqueue(object : Callback<flightinfo> {
                override fun onResponse(call: Call<flightinfo>, response: Response<flightinfo>) {
                    Log.d("FlightResponse", response.body().toString())
                    realtimeDetailsBinding.realtimeData.DeptName.text=response.body()!!.response.dep_name
                    realtimeDetailsBinding.realtimeData.ArrName.text=response.body()!!.response.arr_name
                    realtimeDetailsBinding.realtimeData.DepTime.text=response.body()!!.response.dep_time
                    realtimeDetailsBinding.realtimeData.ArrTime.text=response.body()!!.response.arr_time
                    realtimeDetailsBinding.realtimeData.flightIata.text=response.body()!!.response.flight_iata
                    realtimeDetailsBinding.realtimeData.ActdeptimeInput.text=response.body()!!.response.dep_actual
                    realtimeDetailsBinding.realtimeData.ActarrtimeInput.text=response.body()!!.response.arr_actual
                    realtimeDetailsBinding.realtimeData.DurationInput.text=response.body()!!.response.duration.toString()
                    realtimeDetailsBinding.realtimeData.DelayInput.text=response.body()!!.response.delayed

                }

                override fun onFailure(call: Call<flightinfo>, t: Throwable) {
                    Log.d("error", t.toString())
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}