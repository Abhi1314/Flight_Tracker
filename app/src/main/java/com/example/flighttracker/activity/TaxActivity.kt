package com.example.flighttracker.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.TaxAdaptar
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.taxlist
import com.example.flighttracker.databinding.ActivityTaxBinding
import com.example.flighttracker.response.ResponseTaxlist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaxActivity : AppCompatActivity() {
    private val taxBinding:ActivityTaxBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_tax)
    }
    var taxlist=ArrayList<ResponseTaxlist>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            taxBinding.toolBarLayout.name=getString(R.string.airlinetax)
            taxBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<taxlist> = service!!.getTaxes(BuildConfig.API_KEY)
            call.enqueue(object : Callback<taxlist> {
                override fun onResponse(
                    call: Call<taxlist>, response: Response<taxlist>
                ) {
                    Log.d("response", response.body().toString())
                    taxlist=response.body()!!.response
                    taxBinding.rvTaxlist.adapter=
                        TaxAdaptar(this@TaxActivity,taxlist)
                }

                override fun onFailure(call: Call<taxlist>, t: Throwable) {
                    Log.d("error",t.toString())
                }
            })
        }catch (e:Exception){
            e.printStackTrace()
        }


    }
}