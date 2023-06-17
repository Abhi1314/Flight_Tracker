package com.example.flighttracker.adaptar

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flighttracker.R
import com.example.flighttracker.response.Response
import com.example.flighttracker.utils.util


class SearchAdapter(
    val activity: Activity,
    val citylist: ArrayList<Response>,
    val destination: Boolean
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =LayoutInflater.from(parent.context).inflate(R.layout.searchitem, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityname.text = citylist[position].city_name
        holder.citycode.text = citylist[position].city_code
        holder.layout.setOnClickListener {
            if (destination) {
                util.destination = citylist[position].city_code
                util.destinationcity = citylist[position].city_name
            } else {
                util.source = citylist[position].city_code
                util.sourcecity = citylist[position].city_name

            }
            activity.finish()

        }

    }

    override fun getItemCount(): Int {
        return citylist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityname = itemView.findViewById<TextView>(R.id.cityName)
        val citycode = itemView.findViewById<TextView>(R.id.cityCode_Name)
        val layout = itemView.findViewById<LinearLayout>(R.id.mainLinear1)

    }
}