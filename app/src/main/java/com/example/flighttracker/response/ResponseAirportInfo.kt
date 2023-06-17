package com.example.flighttracker.response

data class ResponseAirportInfo(
    var name: String="",
    var iata_code: String="",
    var icao_code: String="",
    var lat: Double=0.0,
    var lng: Double=0.0,
    var country_code: String=""
)