package com.example.flighttracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.R
import com.example.flighttracker.databinding.ActivityFlightDetailsBinding

class FlightDetailsActivity : AppCompatActivity() {
    private val flightDetailsBinding: ActivityFlightDetailsBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_flight_details)
    }
var sourcecityname = ""
    var destcityname = ""
    var airlinenamesource = ""
    var source_code = ""
    var dest_code = ""
    var duration = ""
    var deptime = ""
    var arrtime = ""
    var terminal = ""
    var status = ""
    var flight_no = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
             source_code = intent.getStringExtra("dept_code").toString()
             dest_code = intent.getStringExtra("arr_code").toString()
            destcityname= intent.getStringExtra("dest_city_name").toString()
            sourcecityname= intent.getStringExtra("source_city_name").toString()
            airlinenamesource=intent.getStringExtra("airline_source_name").toString()
            duration=intent.getStringExtra("time").toString()
            deptime=intent.getStringExtra("dep_time").toString()
            arrtime=intent.getStringExtra("arr_time").toString()
            terminal=intent.getStringExtra("terminal").toString()
            status=intent.getStringExtra("status").toString()
            flight_no=intent.getStringExtra("flight_no").toString()

            flightDetailsBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            flightDetailsBinding.toolBarLayout.name=getString(R.string.flightdetails)
            flightDetailsBinding.flightResult.flightnumber.text=flight_no
            flightDetailsBinding.flightResult.sourceaddress.text=sourcecityname
            flightDetailsBinding.flightResult.destaddress.text=destcityname
            flightDetailsBinding.flightResult.airlineNameSource.text=airlinenamesource
            flightDetailsBinding.flightResult.sourceAirportCode.text=source_code
            flightDetailsBinding.flightResult.destinationAirportCode.text=dest_code
            flightDetailsBinding.flightResult.DepTime.text=deptime
            flightDetailsBinding.flightResult.arrivaltime.text=arrtime
            flightDetailsBinding.flightResult.flightDuration.text=duration
            flightDetailsBinding.flightResult.terminal.text=terminal
            flightDetailsBinding.flightResult.status.text=status

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}