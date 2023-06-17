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
import com.example.flighttracker.activity.FlightDetailsActivity
import com.example.flighttracker.response.ResponseRoute

class FlightDetailListAdapter(
    val context: Context,
    val Flightlist: List<ResponseRoute>,
    val sourcecity: String,
    val destcity: String,
    val date: String,
    val arr_terminal: String?,
    val status1: String,

) :
    RecyclerView.Adapter<FlightDetailListAdapter.ViewHolder>() {
    var sourcecityname = ""
    var destcityname = ""
    var airlinenamesource = ""
    var source_code = ""
    var dest_code = ""
    var duration = ""
    var deptime = ""
    var arrtime = ""
    var terminal = ""
    var status = ""
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlightDetailListAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.flight_detail_list, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: FlightDetailListAdapter.ViewHolder, position: Int) {
        holder.flightno.text = Flightlist[position].flight_number
        sourcecityname = sourcecity
        destcityname = destcity
        airlinenamesource = Flightlist[position].name
        source_code = Flightlist[position].dep_iata
        dest_code = Flightlist[position].arr_iata
        duration = Flightlist[position].duration.toString()
        deptime = Flightlist[position].dep_time
        arrtime = Flightlist[position].arr_time
//        terminal =arr_terminal!!
//        status = status1

        holder.flightdetail.setOnClickListener {
            val intent = Intent(context, FlightDetailsActivity::class.java)
            intent.putExtra("flight_no", Flightlist[position].flight_number)
            intent.putExtra("source_city_name", sourcecityname)
            intent.putExtra("dest_city_name", destcityname)
            intent.putExtra("airline_source_name", airlinenamesource)
            intent.putExtra("dept_code", source_code)
            intent.putExtra("arr_code", dest_code)
            intent.putExtra("time", duration)
            intent.putExtra("dep_time", deptime)
            intent.putExtra("arr_time", arrtime)
            intent.putExtra("terminal", terminal)
            intent.putExtra("status", status1)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return Flightlist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flightno: TextView = itemView.findViewById(R.id.flightNo)
        val flightdetail: ImageView = itemView.findViewById(R.id.iv_next)


    }
}