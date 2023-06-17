package com.example.flighttracker.response

data class ResponseTimeZone(
    val country_code: String="",
    val dst: Float= 0.0F,
    val gmt: Float=0.0F,
    val timezone: String=""
)