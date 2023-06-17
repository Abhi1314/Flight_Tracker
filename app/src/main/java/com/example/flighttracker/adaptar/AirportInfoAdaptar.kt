package com.example.flighttracker.adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flighttracker.R
import com.example.flighttracker.activity.AirportInfoActivity
import com.example.flighttracker.response.ResponseAirportInfo

class AirportInfoAdaptar(val context: Context, val airportList: ArrayList<ResponseAirportInfo>) :
    RecyclerView.Adapter<AirportInfoAdaptar.ViewHolder>() {

    private var isExpanded = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AirportInfoAdaptar.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.airportlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AirportInfoAdaptar.ViewHolder, position: Int) {
        holder.airportname.text=airportList[position].name
        holder.airportnamecard.text=airportList[position].name
        holder.country_code.text=airportList[position].country_code
        holder.latitude.text=airportList[position].lat.toString()
        holder.icao_city.text=airportList[position].icao_code
        holder.langititude.text=airportList[position].lng.toString()
        holder.iata_city.text=airportList[position].iata_code
        holder.dropresult.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                holder.dropresult.setImageResource(R.drawable.ic_click)
                holder.viewResult.visibility = View.VISIBLE
            } else {
                holder.dropresult.setImageResource(R.drawable.ic_close_arrow)
                holder.viewResult.visibility = View.GONE
            }

        }
    }

    override fun getItemCount(): Int {
        return airportList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val airportname: TextView = itemView.findViewById(R.id.airportName)
        val airportnamecard: TextView = itemView.findViewById(R.id.sourceAirport)
        val country_code: TextView = itemView.findViewById(R.id.CountryCode)
        val latitude: TextView = itemView.findViewById(R.id.latitude_input)
        val icao_city: TextView = itemView.findViewById(R.id.City_ICAO_input)
        val iata_city: TextView = itemView.findViewById(R.id.City_Code_input)
        val langititude: TextView = itemView.findViewById(R.id.Longituted_input)
        val dropresult: ImageView = itemView.findViewById(R.id.dropResult)
        val viewResult: View = itemView.findViewById(R.id.viewResult)
    }
}