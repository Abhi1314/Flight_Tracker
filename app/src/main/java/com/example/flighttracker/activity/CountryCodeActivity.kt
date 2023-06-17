package com.example.flighttracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityCountryCodeBinding

class CountryCodeActivity : AppCompatActivity() {
    private val countryCodeBinding:ActivityCountryCodeBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_country_code)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            countryCodeBinding.toolBarLayout.name=getString(R.string.countrycode)
            countryCodeBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            countryCodeBinding.SearchCodeBtn.setOnClickListener {
                val code=countryCodeBinding.edCountry.text.toString()
                if(code.isEmpty()){
                    shortShowToast(getString(R.string.enter_countrycode))
                }else{
                    val intent= Intent(this@CountryCodeActivity,CountrycodeDetalisActivity::class.java)
                    intent.putExtra("CountryCode",code)
                    startActivity(intent)
                }

            }
        }catch (e:Exception){
            e.printStackTrace()
        }


    }
}