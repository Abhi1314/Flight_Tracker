package com.example.flighttracker.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.AirportInfoAdaptar
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.airportInfo
import com.example.flighttracker.databinding.ActivityAirportDetailsBinding
import com.example.flighttracker.response.ResponseAirportInfo
import com.example.flighttracker.utils.hideKeyBoard
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirportDetailsActivity : AppCompatActivity() {

    private  val airportDetailsBinding: ActivityAirportDetailsBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_airport_details)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            airportDetailsBinding.toolBarLayout.name=getString(R.string.airportInfo)
            airportDetailsBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            airportDetailsBinding.SearchAirportBtn.setOnClickListener {
                val airportname=airportDetailsBinding.edNameAirport.text.toString()
                hideKeyBoard(this@AirportDetailsActivity)
                if(airportname.isEmpty()){
                    shortShowToast(getString(R.string.enter_airportcode))
                }
                else{
                    val intent=Intent(this@AirportDetailsActivity,AirportInfoActivity::class.java)
                        intent.putExtra("airportname",airportname)
                        startActivity(intent)

                }

            }


        }catch (e:Exception){
            e.printStackTrace()
        }


    }

}