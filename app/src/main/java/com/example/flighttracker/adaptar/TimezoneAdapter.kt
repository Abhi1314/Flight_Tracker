package com.example.flighttracker.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flighttracker.R
import com.example.flighttracker.response.ResponseTimeZone

class TimezoneAdapter(val context : Context,val timezoneDetailList:ArrayList<ResponseTimeZone>):
    RecyclerView.Adapter<TimezoneAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.timelist,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  timezoneDetailList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timezone.text=timezoneDetailList[position].timezone
        holder.gmt.text=timezoneDetailList[position].gmt.toString()
        holder.dst.text=timezoneDetailList[position].dst.toString()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val timezone:TextView=itemView.findViewById(R.id.timezoneInput)
      val gmt:TextView=itemView.findViewById(R.id.gmtInput)
      val dst:TextView=itemView.findViewById(R.id.dstInput)
    }
}