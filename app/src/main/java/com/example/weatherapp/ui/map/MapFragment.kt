package com.example.weatherapp.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback ,GoogleMap.OnMapClickListener{

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

    @SuppressLint("SetTextI18n")
    private fun getWeather(latLng: LatLng) {
        viewModel.getWeatherData("metric", latLng.latitude, latLng.longitude)
            .observe(activity!!, Observer {
                city_title_tv?.text = it.name
                temp_tv?.text = "${it!!.main.temp.toInt()}°"
                weather_tv?.text = it.weather[0].main
                Glide.with(context!!)
                    .load("http://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                    .into(weather_ic)
                humidity?.text = "${it.main.humidity}% влажности"
            })
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val bishkek = LatLng(42.882004, 74.582748)
        googleMap.addMarker(MarkerOptions().position(bishkek).title("Bishkek"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(bishkek))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bishkek, 10f))
        googleMap.setOnMapClickListener {
            val weatherFragment = WeatherFragment()
            val fm = activity?.supportFragmentManager
            if (fm?.findFragmentByTag("frWeather") != null) {
                fm.beginTransaction().show(fm.findFragmentByTag("frWeather")!!).commit()
            } else {
                fm!!.beginTransaction().add(R.id.mainFragment, weatherFragment, "frWeather")
                    .commit()
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

    override fun onMapClick(p0: LatLng?) {

    }

}
