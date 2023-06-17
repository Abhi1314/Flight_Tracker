package com.example.flighttracker.response

data class ResponseSchedule(
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
    var status: String = "",
    var arr_terminal: String = "",
    var flight_iata:String=""
)