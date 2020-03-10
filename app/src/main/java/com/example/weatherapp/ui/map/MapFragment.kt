package com.example.weatherapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModel()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    private fun getWeather(latLng: LatLng) {
        viewModel.getWeatherData("metric", latLng.latitude, latLng.longitude)
            .observe(activity!!, Observer {
                city_name_txt?.text = it.name
                deg_txt?.text = "${it!!.main.temp.toInt()}°"
                info_txt?.text = it.weather[0].main
                Glide.with(context!!)
                    .load("http://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                    .into(ic_weather_small)
                humidity_txt?.text = "${it.main.humidity}% влажности"
            })
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMapClickListener {
            val weatherFragment = WeatherFragment()
            val fm = activity?.supportFragmentManager
            if (fm?.findFragmentByTag("weatherF") != null) {
                fm.beginTransaction().show(fm.findFragmentByTag("weatherF")!!).commit()
            } else {
                fm!!.beginTransaction().add(R.id.mainFragment, weatherFragment, "weatherF").commit()
            }
            getWeather(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        gmap.onCreate(savedInstanceState)
        gmap.onResume()
        gmap.getMapAsync(this)
    }

}
