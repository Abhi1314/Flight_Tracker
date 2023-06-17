package com.example.flighttracker.api


import com.example.flighttracker.response.*
import com.google.gson.annotations.SerializedName

data class dataclass(
    val response: List<Response>,
)

data class schedule(
    @SerializedName("response")
    val response: List<ResponseSchedule>

)

data class route(
    @SerializedName("response")
    val response: ArrayList<ResponseRoute>
)

data class airlinedb(
    @SerializedName("response")
    val response: ArrayList<ResponseAirlineDB>
)

data class airportInfo(
    @SerializedName("response")
    val response: ArrayList<ResponseAirportInfo>
)

data class airportNearby(
    @SerializedName("response")
    val response: ResponseNearbyAirport
)

data class countryDB(
    @SerializedName("response")
    val response: ArrayList<ResponseCountry>
)

data class airlineFleetDB(
    @SerializedName("response")
    val response: ArrayList<ResponseFleet>
)

data class timezoneList(
    @SerializedName("response")
    val response: ArrayList<ResponseTimeZone>
)

data class taxlist(
    @SerializedName("response")
    val response: ArrayList<ResponseTaxlist>
)

data class flightinfo(
    @SerializedName("response")
    val response: ResponseFlightInfo
)