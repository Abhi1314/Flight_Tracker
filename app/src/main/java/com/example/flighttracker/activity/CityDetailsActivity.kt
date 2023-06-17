package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.dataclass
import com.example.flighttracker.databinding.ActivityCityDetailsBinding
import com.example.flighttracker.utils.gone
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityDetailsActivity : AppCompatActivity() {
    private val cityDetailsBinding: ActivityCityDetailsBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_city_details)
    }
    var isExpanded = false
    lateinit var city_code: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            cityDetailsBinding.toolBarLayout.name = getString(R.string.city_info)
            cityDetailsBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            city_code = intent.getStringExtra("citycode")!!.uppercase()
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<dataclass> = service!!.getCitiesDB(
                BuildConfig.API_KEY, city_code
            )
            call.enqueue(object : Callback<dataclass> {
                override fun onResponse(call: Call<dataclass>, response: Response<dataclass>) {
                    Log.d("airportresponse", response.body().toString())
                    if (response.body()!!.response.isNotEmpty()) {
                        cityDetailsBinding.viewCityResult.cityName.text =
                            response.body()!!.response[0].city_name
                        cityDetailsBinding.viewCityResult.CityCodeInpute.text =
                            response.body()!!.response[0].city_code
                        cityDetailsBinding.viewCityResult.latitudeInput.text =
                            response.body()!!.response[0].lat.toString()
                        cityDetailsBinding.viewCityResult.LongitutedInput.text =
                            response.body()!!.response[0].lng.toString()
                        cityDetailsBinding.viewCityResult.countryCodeInput.text =
                            response.body()!!.response[0].country_code

                        cityDetailsBinding.cityName.text = response.body()!!.response[0].city_name
                    } else {
                        gone(cityDetailsBinding.llDropbox)
                        visible(cityDetailsBinding.nodatafound.root)
                    }
                    cityDetailsBinding.dropResult.setOnClickListener {
                        isExpanded = !isExpanded
                        if (isExpanded) {
                            cityDetailsBinding.dropResult.setImageResource(R.drawable.ic_click)
                            cityDetailsBinding.viewCityResult.root.visibility = View.VISIBLE
                        } else {
                            cityDetailsBinding.dropResult.setImageResource(R.drawable.ic_close_arrow)
                            cityDetailsBinding.viewCityResult.root.visibility = View.GONE
                        }
                    }
                }

                override fun onFailure(call: Call<dataclass>, t: Throwable) {
                    Log.d("error", t.toString())
                    gone(cityDetailsBinding.llDropbox)
                    visible(cityDetailsBinding.nodatafound.root)

                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}