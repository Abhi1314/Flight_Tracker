package com.example.flighttracker.response

data class ResponseAirlineDB(
    var name: String = "",
    var iata_code: String = "",
    var icao_code: String? = "",
    var iata_prefix: Int=0,
    var iata_accounting: Int=0,
    var callsign: String="",
    var country_code: String="",
    var iosa_registered: Int=0,
    var is_scheduled: Int=0,
    var is_passenger: Int=0,
    var is_cargo: Int=0,
    var is_international: Int=0,
    var total_aircrafts: Int=0,
    var average_fleet_age: Int=0,
    var accidents_last_5y: Int=0,
    var crashes_last_5y: Int=0,
)