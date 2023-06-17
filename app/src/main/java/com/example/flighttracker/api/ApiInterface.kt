package com.example.flighttracker.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v9/airports")
    fun getAllAirportDB(@Query("iata_code") iata_code: String,@Query("api_key") api_key: String):Call<dataclass>

    @GET("api/v9/cities")
    fun getAllCitiesDB(@Query("api_key") api_key: String):Call<dataclass>

    @GET("api/v9/cities")
    fun getCitiesDB(@Query("api_key") api_key: String,@Query("city_code") city_code: String):Call<dataclass>

    @GET("api/v9/schedules")
    fun getFlightDetails(@Query("dep_iata") dep_iata: String,@Query("api_key") api_key: String,@Query("arr_iata") arr_iata: String):Call<schedule>

    @GET("api/v9/routes")
    fun getRouteDetails(@Query("api_key") api_key: String,@Query("dep_iata") dep_iata: String,@Query("arr_iata") arr_iata: String):Call<route>

    @GET("api/v9/airlines")
    fun getAirlineDB(@Query("api_key") api_key: String,@Query("airline_iata") airline_iata: String):Call<airlinedb>

    @GET("api/v9/airports")
    fun getAirportInfo(@Query("api_key") api_key: String):Call<airportInfo>

    @GET("api/v9/nearby")
    fun getNearby(@Query("api_key") api_key: String,@Query("lat") lat: Double,@Query("lng") lng: Double,@Query("distance") distance: Int):Call<airportNearby>

    @GET("api/v9/countries")
    fun getCountryDB(@Query("api_key") api_key: String,@Query("code") code: String):Call<countryDB>

    @GET("api/v9/airlines")
    fun getAirlineDATA(@Query("api_key") api_key: String):Call<airlinedb>

    @GET("api/v9/fleets")
    fun getAirlineFleet(@Query("api_key") api_key: String,@Query("iata_code") airline_iata: String):Call<airlineFleetDB>

    @GET("api/v9/timezones")
    fun getTimeZone(@Query("api_key") api_key: String): Call<timezoneList>

    @GET("api/v9/taxes")
    fun getTaxes(@Query("api_key") api_key: String): Call<taxlist>

    @GET("/api/v9/flight")
    fun getFlightInfo(@Query("api_key") api_key: String,@Query("flight_iata") flight_iata: String):Call<flightinfo>

}