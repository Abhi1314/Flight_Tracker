package com.example.flighttracker.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flighttracker.R
import com.example.flighttracker.response.ResponseNearbyAirport

class NearAriportAdaptar(val context: Context, val nearairportlist:ResponseNearbyAirport):
    RecyclerView.Adapter<NearAriportAdaptar.ViewHolder>() {
    private var isExpanded = false
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NearAriportAdaptar.ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.nearairportlist,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: NearAriportAdaptar.ViewHolder, position: Int) {
        holder.airportname.text=nearairportlist.airports[position].name
        holder.airportnamecard.text=nearairportlist.airports[position].name
        holder.country_code.text=nearairportlist.airports[position].country_code
        holder.latitude.text=nearairportlist.airports[position].lat.toString()
        holder.icao_city.text=nearairportlist.airports[position].icao_code
        holder.langititude.text=nearairportlist.airports[position].lng.toString()
        holder.iata_city.text=nearairportlist.airports[position].iata_code
        holder.popularity.text=nearairportlist.airports[position].popularity.toString()
        holder.distance.text=nearairportlist.airports[position].distance.toString()
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
        return nearairportlist.airports.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val airportname: TextView = itemView.findViewById(R.id.airportName)
        val airportnamecard: TextView = itemView.findViewById(R.id.sourceAirport)
        val country_code: TextView = itemView.findViewById(R.id.CountryCode)
        val latitude: TextView = itemView.findViewById(R.id.latitude_input)
        val icao_city: TextView = itemView.findViewById(R.id.City_ICAO_input)
        val iata_city: TextView = itemView.findViewById(R.id.City_Code_input)
        val langititude: TextView = itemView.findViewById(R.id.Longituted_input)
        val popularity: TextView = itemView.findViewById(R.id.popularity_input)
        val distance: TextView = itemView.findViewById(R.id.Distance_input)
        val dropresult: ImageView = itemView.findViewById(R.id.dropResult)
        val viewResult: View = itemView.findViewById(R.id.viewResult)

    }
}