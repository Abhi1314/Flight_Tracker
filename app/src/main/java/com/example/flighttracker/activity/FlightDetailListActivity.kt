package com.example.flighttracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.FlightDetailListAdapter
import com.example.flighttracker.api.*
import com.example.flighttracker.databinding.ActivityFlightDetailListBinding
import com.example.flighttracker.response.ResponseRoute
import com.example.flighttracker.response.ResponseSchedule
import com.example.flighttracker.utils.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FlightDetailListActivity : AppCompatActivity() {
    private val FlightDetailListBinding: ActivityFlightDetailListBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_flight_detail_list)
    }
    var destcity: String = ""
    var sourcecity: String = ""
    val upcomingFightList = hashSetOf<ResponseRoute>()

    var status: String = ""
    var arr_terminal: String? = ""
    lateinit var name: String
    lateinit var Dep_Location_Iata: String
    lateinit var Arr_Location_Iata: String
    lateinit var dateTxt: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Dep_Location_Iata = intent.getStringExtra("Dep_iata").toString()
            Arr_Location_Iata = intent.getStringExtra("Arr_iata").toString()
            destcity = intent.getStringExtra("cityname2").toString()
            sourcecity = intent.getStringExtra("cityname1").toString()
            FlightDetailListBinding.toolBarLayout.name = getString(R.string.Flight_list)
            FlightDetailListBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
            val call: Call<schedule> = service!!.getFlightDetails(
                Dep_Location_Iata, BuildConfig.API_KEY, Arr_Location_Iata
            )
            call.enqueue(object : Callback<schedule> {
                override fun onResponse(call: Call<schedule>, response: Response<schedule>) {
                    Log.d("response", response.body().toString())
                    if (response.body()!!.response.isNotEmpty()) {
                        val api_key = BuildConfig.API_KEY
                        getRoute(
                            api_key,
                            Dep_Location_Iata,
                            Arr_Location_Iata,
                            response.body()!!.response
                        )

                    } else {
                        visible(FlightDetailListBinding.nodatafound.root)
                    }
                }

                override fun onFailure(call: Call<schedule>, t: Throwable) {
                    visible(FlightDetailListBinding.nodatafound.root)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    private fun getAirlineName(
//        api_key: String,
//        element: ResponseRoute,
//        airlineIata: String,
//        responseScheduleList: List<ResponseSchedule>
//    ) {
//        val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
//        val call: Call<airlinedb> = service!!.getAirlineDB(api_key, airlineIata)
//        Log.d("airlineiata", airlineIata)
//        call.enqueue(object : Callback<airlinedb> {
//            override fun onResponse(call: Call<airlinedb>, response: Response<airlinedb>) {
//                Log.d("AirlineName", response.body().toString())
//                val se = response.body()!!.response.filter {
//                    it.icao_code?.contains(airlineIata) == true
//                }
//                for (responeElement in responseScheduleList){
//                    if (responeElement.flight_iata==element.flight_iata){
//                        upcomingFightList.add(
//                            ResponseRoute(
//                                element.flight_number,
//                                element.dep_iata,
//                                element.arr_iata,
//                                element.arr_time,
//                                element.duration,
//                                element.dep_time,
//                                element.dep_icao,
//                                element.arr_icao,
//                                element.airline_icao,
//                                element.airline_iata,
//                                element.days,
//                                if (se.isEmpty()) {
//                                    ""
//                                } else {
//                                    se[0].name
//
//                                }
//
//                            ))
//                    }
//                }
//                Log.d("Size",upcomingFightList.size.toString())
//
//                FlightDetailListBinding.rvFlightDetailsList.adapter = FlightDetailListAdapter(
//                    this@FlightDetailListActivity,
//                    upcomingFightList.toList(),
//                    sourcecity,
//                    destcity,
//                    dateTxt,arr_terminal,status
//                )
//
//            }
//
//            override fun onFailure(call: Call<airlinedb>, t: Throwable) {
//                visible(FlightDetailListBinding.nodatafound.root)
//            }
//        })
//    }

    private fun getRoute(
        api_key: String,
        depIata: String,
        arrIata: String,
        responseScheduleList: List<ResponseSchedule>

    ) {
        val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)
        dateTxt = intent.getStringExtra("date").toString()
        val call: Call<route> = service!!.getRouteDetails(
            api_key,
            depIata,
            arrIata
        )
        call.enqueue(object : Callback<route> {
            override fun onResponse(call: Call<route>, response: Response<route>) {
                if (response.body()!!.response.isNotEmpty()) {
                    val day = SimpleDateFormat("EEE").format(Date(dateTxt))
                    Log.d("day", day)
                    for (element in responseScheduleList) {
                                Log.d("responseScheduleList", responseScheduleList.size.toString())
                                for (responeElement in response.body()!!.response) {
                        val upcomingflightsdate = responeElement.days
                        if (upcomingflightsdate.contains(day.lowercase())) {
//                            if (!upcomingFightList.contains(responeElement)) {
                                    if (element.flight_iata == responeElement.flight_iata) {
                                        upcomingFightList.add(
                                            ResponseRoute(
                                                element.flight_number,
                                                element.dep_iata,
                                                element.arr_iata,
                                                element.arr_time,
                                                element.duration,
                                                element.dep_time,
                                                element.dep_icao,
                                                element.arr_icao,
                                                element.airline_icao,
                                                element.airline_iata
                                            )
                                        )
                                    }
//                                }
                                Log.d("Size", upcomingFightList.size.toString())

                              }
                            Log.d("route", response.body().toString())
                        }

                    }
                    FlightDetailListBinding.rvFlightDetailsList.adapter =
                        FlightDetailListAdapter(
                            this@FlightDetailListActivity,
                            upcomingFightList.toList(),
                            sourcecity,
                            destcity,
                            dateTxt, arr_terminal, status
                        )

                } else {
                    visible(FlightDetailListBinding.nodatafound.root)
                }

            }
            override fun onFailure(call: Call<route>, t: Throwable) {
                t.printStackTrace()
                visible(FlightDetailListBinding.nodatafound.root)
            }
        })
    }

}