package com.example.flighttracker.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.window.OnBackInvokedCallback
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.example.flighttracker.utils.gone
import com.example.flighttracker.utils.visible
import com.example.flighttracker.BuildConfig
import com.example.flighttracker.R
import com.example.flighttracker.adaptar.SearchAdapter
import com.example.flighttracker.api.ApiInterface
import com.example.flighttracker.api.RetrofitInstance
import com.example.flighttracker.api.dataclass
import com.example.flighttracker.databinding.ActivitySearchBinding
import com.example.flighttracker.response.Response
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class SearchActivity : AppCompatActivity() {
    private val searchBinding: ActivitySearchBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_search)
    }
    private var citylist: ArrayList<Response> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            searchBinding.toolBarLayout.name=getString(R.string.search_flight)
            searchBinding.toolBarLayout.backImage.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            searchBinding.edSearchtxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable) {
                    filter(s.toString().trim())
                }
            })
            val dest = intent.getBooleanExtra("isDestination", false)
            val sharedPreferences: SharedPreferences = getSharedPreferences(
                getString(R.string.app_name),
                MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            val service = RetrofitInstance.getRetrofitInstance()?.create(ApiInterface::class.java)

            if (sharedPreferences.getStringSet("country", null)?.isNotEmpty() == true) {

                Log.d("editor", sharedPreferences.getStringSet("country", null).toString())
                val set: Set<String> = sharedPreferences.getStringSet("country", null)!!
                val citycode: List<String> = ArrayList(set)
                println(citycode)
                for (element in citycode) {
                    val splitle = element.split("/")
                    citylist.add(
                        Response(
                            city_code = splitle[1].trim(),
                            city_name = splitle[0].trim()
                        )
                    )
                }
                Log.d("cityList", citylist.toString())
                searchBinding.rvSearchList.adapter =
                    SearchAdapter(this@SearchActivity, citylist, dest)

            } else {
                val call: Call<dataclass> =
                    service!!.getAllCitiesDB(BuildConfig.API_KEY)
                call.enqueue(object : Callback<dataclass> {
                    override fun onResponse(call: Call<dataclass>, response: retrofit2.Response<dataclass>) {
                        citylist =
                            response.body()!!.response as ArrayList<Response>
                        val cd = citylist.map { it.city_name + "/" + it.city_code }.toList()
                        val set: Set<String> = cd.toSet()
                        editor.putStringSet("country", set)
                        editor.commit()
                        searchBinding.rvSearchList.adapter =
                            SearchAdapter(this@SearchActivity, citylist, dest)

                    }

                    override fun onFailure(call: Call<dataclass>, t: Throwable) {
                        Log.d("error", t.toString())
                    }

                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<Response> = ArrayList()

        for (item in citylist) {

            if (item.city_name.lowercase(Locale.getDefault()).contains(
                    text.lowercase(Locale.getDefault())
                )
            ) {
                filteredlist.add(item)
            }
        }
        searchBinding.rvSearchList.adapter = SearchAdapter(
            this@SearchActivity,
            filteredlist,
            intent.getBooleanExtra("isDestination", false)
        )
        if (filteredlist.isEmpty()) {
            visible(searchBinding.nodatafound.root)
        } else {
            gone(searchBinding.nodatafound.root)
        }
    }

}