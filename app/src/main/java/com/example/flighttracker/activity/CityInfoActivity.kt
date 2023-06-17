package com.example.flighttracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityCityInfoBinding

class CityInfoActivity : AppCompatActivity() {
    private val cityInfoBinding: ActivityCityInfoBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_city_info)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            cityInfoBinding.toolBarLayout.name = getString(R.string.city_info)
            cityInfoBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            cityInfoBinding.SearchAirlineBtn.setOnClickListener {
                val citycode = cityInfoBinding.edCityCode.text.toString()
                if (citycode.isEmpty()) {
                    shortShowToast(getString(R.string.enter_citycode))
                } else {
                    val intent = Intent(this@CityInfoActivity, CityDetailsActivity::class.java)
                    intent.putExtra("citycode", citycode)
                    startActivity(intent)
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}