package com.example.flighttracker.response

import com.google.gson.annotations.SerializedName

data class Response(

    val country_code: String = "",
    var iata_code: String = "",
    var icao_code: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    @SerializedName("name")
    var city_name: String = "",

    var city_code: String = ""
)