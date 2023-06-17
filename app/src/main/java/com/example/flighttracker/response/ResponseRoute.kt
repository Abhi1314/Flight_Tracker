package com.example.flighttracker.response

data class ResponseRoute(
    var flight_number: String = "",
    var dep_iata: String = "",
    var arr_iata: String = "",
    var arr_time: String = "",
    var duration: Int = 0,
    var dep_time: String = "",
    var dep_icao: String = "",
    var arr_icao: String = "",
    var airline_icao: String = "",
    var airline_iata: String = "",
    var days: List<String> = listOf(),
    var name: String = "",
    var flight_iata:String=""


    )