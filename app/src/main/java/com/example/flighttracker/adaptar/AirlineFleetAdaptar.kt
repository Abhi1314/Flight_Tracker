package com.example.flighttracker.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flighttracker.R
import com.example.flighttracker.response.ResponseFleet

class AirlineFleetAdaptar(val context: Context,val airlinedetails:ArrayList<ResponseFleet>): RecyclerView.Adapter<AirlineFleetAdaptar.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.airlinefleetlist,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return airlinedetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.airlineiata.text=airlinedetails[position].airline_iata
        holder.airlineicao.text=airlinedetails[position].airline_icao
        holder.manufacture.text=airlinedetails[position].manufacturer
        holder.iata.text=airlinedetails[position].iata
        holder.icao.text=airlinedetails[position].icao
        holder.hexno.text=airlinedetails[position].hex
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val airlineiata:TextView=itemView.findViewById(R.id.airlineIataInput)
        val airlineicao:TextView=itemView.findViewById(R.id.airlineIcaoInput)
        val manufacture:TextView=itemView.findViewById(R.id.airlineManufactureInput)
        val iata:TextView=itemView.findViewById(R.id.iataInput)
        val icao:TextView=itemView.findViewById(R.id.icaoInput)
        val hexno:TextView=itemView.findViewById(R.id.airlineHexInput)
    }

}