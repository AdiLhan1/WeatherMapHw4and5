package com.example.weatherapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ic_close.setOnClickListener {
            val fm = activity?.supportFragmentManager
            val transaction = fm?.beginTransaction()
            transaction?.hide(this)?.commit()
            city_name_txt.text = ""
            deg_txt.text = ""
            info_txt.text = ""
            ic_weather_small.setImageDrawable(null)
            humidity_txt.text = ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }
}