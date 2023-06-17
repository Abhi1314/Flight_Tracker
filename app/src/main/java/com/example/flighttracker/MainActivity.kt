package com.example.flighttracker

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.activity.*
import com.example.flighttracker.databinding.ActivityMainBinding
import com.example.flighttracker.utils.shortShowToast
import com.example.flighttracker.utils.util
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import android.Manifest

class MainActivity : AppCompatActivity() {
    private val mainBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    var sourcecityname: String = ""
    var destinationcityname: String = ""
    var swapcount = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            mainBinding.backImg.setOnClickListener {
                util.source = ""
                util.destination = ""
                mainBinding.edDate.text = ""
                onBackPressedDispatcher.onBackPressed()
            }
            mainBinding.swapImage.setOnClickListener {
                swapcount = !swapcount
                if (swapcount) {
                    if (util.source.isNotEmpty() && util.destination.isNotEmpty()) {
                        mainBinding.edSourceAirport.text = util.source
                        mainBinding.edDestinationAirport.text = util.destination
                    } else {
                        mainBinding.edSourceAirport.hint = "Enter Source"
                        mainBinding.edDestinationAirport.hint = "Enter Destination"
                    }
                } else {
                    if (util.source.isNotEmpty() && util.destination.isNotEmpty()) {
                        mainBinding.edDestinationAirport.text = util.source
                        mainBinding.edSourceAirport.text = util.destination
                    } else {
                        mainBinding.edDestinationAirport.hint = "Enter Source"
                        mainBinding.edSourceAirport.hint = "Enter Destination"
                    }

                }


            }
            mainBinding.source.setOnClickListener {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("isDestination", false)
                startActivity(intent)
            }
            mainBinding.destination.setOnClickListener {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("isDestination", true)
                startActivity(intent)
            }
            val materialDatePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .build()
            materialDatePicker.addOnPositiveButtonClickListener {
                val myFormat = "dd-MMM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                mainBinding.edDate.text = sdf.format(materialDatePicker.selection)
            }
            mainBinding.edDate.setOnClickListener {
                materialDatePicker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

            }
            mainBinding.cvSearch.setOnClickListener {
                SearchFlight()
            }

            mainBinding.realtime.setOnClickListener {
                val intent = Intent(this@MainActivity, RealtimeActivity::class.java)
                startActivity(intent)
            }
            mainBinding.CountryCode.featureImg.setImageResource(R.drawable.ic_countrycode)
            mainBinding.CountryCode.featureName.text = getString(R.string.countrycode_newline)
            mainBinding.CountryCode.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, CountryCodeActivity::class.java)
                startActivity(intent)
            }
            mainBinding.AirportInfo.featureImg.setImageResource(R.drawable.ic_airportinfo)
            mainBinding.AirportInfo.featureName.text = getString(R.string.airportinfo)
            mainBinding.AirportInfo.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, AirportDetailsActivity::class.java)
                startActivity(intent)
            }
            mainBinding.CityInfo.featureImg.setImageResource(R.drawable.ic_cityinfo)
            mainBinding.CityInfo.featureName.text = getString(R.string.cityinfo)
            mainBinding.CityInfo.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, CityInfoActivity::class.java)
                startActivity(intent)

            }
            mainBinding.TimeZone.featureImg.setImageResource(R.drawable.ic_nearbyairport)
            mainBinding.TimeZone.featureName.text = getString(R.string.timezone)
            mainBinding.TimeZone.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, TimeZoneInfoActivity::class.java)
                startActivity(intent)

            }
            mainBinding.AirlineInfo.featureImg.setImageResource(R.drawable.ic_airlineinfo)
            mainBinding.AirlineInfo.featureName.text = getString(R.string.airlineinfo)
            mainBinding.AirlineInfo.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, AirlineInfoActivity::class.java)
                startActivity(intent)

            }
            mainBinding.AirlineFleet.featureImg.setImageResource(R.drawable.ic_airlinefleet)
            mainBinding.AirlineFleet.featureName.text = getString(R.string.airlinefleet)
            mainBinding.AirlineFleet.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, AirlineFleetActivity::class.java)
                startActivity(intent)
            }
            mainBinding.AirlineTax.featureImg.setImageResource(R.drawable.ic_airlinefleet)
            mainBinding.AirlineTax.featureName.text = getString(R.string.airlinetax)
            mainBinding.AirlineTax.rloption.setOnClickListener {
                val intent = Intent(this@MainActivity, TaxActivity::class.java)
                startActivity(intent)
            }
            mainBinding.nearAirport.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                        ), 101
                    )

                } else {
                    val intent = Intent(this@MainActivity, NearbyAirportActivity::class.java)
                    startActivity(intent)
                }


            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    fun SearchFlight() {
        val sourceairport = mainBinding.edSourceAirport.text.toString()
        val destinationairport = mainBinding.edDestinationAirport.text.toString()
        val date = mainBinding.edDate.text.toString()
        if (sourceairport.isEmpty()) {
            shortShowToast(getString(R.string.enter_departure))
        } else if (destinationairport.isEmpty()) {
            shortShowToast(getString(R.string.enter_destination))

        } else if (date.isEmpty()) {
            shortShowToast(getString(R.string.enter_date))
        } else {
            getflightdetails(sourceairport, destinationairport, date)
        }
    }

    private fun getflightdetails(sourceairport: String, destinationairport: String, date: String) {
        val intent = Intent(this@MainActivity, FlightDetailListActivity::class.java)
        intent.putExtra("Dep_iata", sourceairport)
        intent.putExtra("Arr_iata", destinationairport)
        intent.putExtra("cityname1", sourcecityname)
        intent.putExtra("cityname2", destinationcityname)
        intent.putExtra("date", date)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(this@MainActivity, NearbyAirportActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (util.source.isNotEmpty()) {
            mainBinding.edSourceAirport.text = util.source
            sourcecityname = util.sourcecity

        }
        if (util.destination.isNotEmpty()) {
            mainBinding.edDestinationAirport.text = util.destination
            destinationcityname = util.destinationcity
        }


    }


}