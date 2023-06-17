package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.TimezoneAdapter
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.timezoneList
import com.example.flighttracker.databinding.ActivityTimeZoneInfoBinding
import com.example.flighttracker.response.ResponseTimeZone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeZoneInfoActivity : AppCompatActivity() {
    private val timeZoneInfoBinding:ActivityTimeZoneInfoBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_time_zone_info)
    }
    var timezonelist= ArrayList<ResponseTimeZone>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            timeZoneInfoBinding.toolBarLayout.name=getString(R.string.timezone)
            timeZoneInfoBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<timezoneList> = service!!.getTimeZone(BuildConfig.API_KEY)
            call.enqueue(object : Callback<timezoneList> {
                override fun onResponse(
                    call: Call<timezoneList>, response: Response<timezoneList>
                ) {
                    Log.d("response", response.body().toString())
                    timezonelist=response.body()!!.response
                    timeZoneInfoBinding.rvTimeZone.adapter=
                        TimezoneAdapter(this@TimeZoneInfoActivity,timezonelist)
                }

                override fun onFailure(call: Call<timezoneList>, t: Throwable) {
                        Log.d("error",t.toString())
                }
            })
        }catch(e:Exception){
           e.printStackTrace()
        }
    }
}