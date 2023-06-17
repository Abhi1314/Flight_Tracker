package com.example.flighttracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.countryDB
import com.example.flighttracker.databinding.ActivityCountrycodeDetalisBinding
import com.example.flighttracker.utils.gone
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountrycodeDetalisActivity : AppCompatActivity() {
    private val countrycodeDetalisBinding:ActivityCountrycodeDetalisBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_countrycode_detalis)
    }
    var code:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            countrycodeDetalisBinding.toolBarLayout.name=getString(R.string.country_code_details)
            countrycodeDetalisBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            code=intent.getStringExtra("CountryCode").toString()
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<countryDB> = service!!.getCountryDB(
                BuildConfig.API_KEY, code.uppercase()
            )
            call.enqueue(object : Callback<countryDB> {
                override fun onResponse(call: Call<countryDB>, response: Response<countryDB>) {
                    Log.d("Country",response.body().toString())
                    if(response.body()!!.response.isNotEmpty()){
                        countrycodeDetalisBinding.CountryName.text=response.body()!!.response[0].name
                        countrycodeDetalisBinding.Codeinput.text=response.body()!!.response[0].code
                        countrycodeDetalisBinding.Code3input.text=response.body()!!.response[0].code3
                        Glide.with(this@CountrycodeDetalisActivity)
                            .load(BuildConfig.FLAG_API+response.body()!!.response[0].code.lowercase()+".png")
                            .into(countrycodeDetalisBinding.Img)
                    }else{
                        gone(countrycodeDetalisBinding.llcountryCode)
                        visible(countrycodeDetalisBinding.nodatafound.root)
                    }

                }

                override fun onFailure(call: Call<countryDB>, t: Throwable) {
                    gone(countrycodeDetalisBinding.llcountryCode)
                    visible(countrycodeDetalisBinding.nodatafound.root)
                }
            })
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}