package com.example.flighttracker.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flighttracker.R
import com.example.flighttracker.response.ResponseTaxlist

class TaxAdaptar(val context: Context,val taxlist:ArrayList<ResponseTaxlist>): RecyclerView.Adapter<TaxAdaptar.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.taxlist,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taxlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=taxlist[position].name
        holder.code.text=taxlist[position].code

    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name:TextView=itemView.findViewById(R.id.taxInput)
        val code:TextView=itemView.findViewById(R.id.codeInput)

    }
}