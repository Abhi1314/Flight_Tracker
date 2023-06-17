package com.example.flighttracker.response

data class ResponseNearbyAirport(
    val airports: ArrayList<NearbyResponse>
)

data class NearbyResponse(

    val name: String="",
    val iata_code: String="",
    val icao_code: String="",
    val lat: Double=0.0,
    val lng: Double=0.0,
    val country_code: String="",
    val popularity: Int=0,
    val distance: Double=0.0,
)

