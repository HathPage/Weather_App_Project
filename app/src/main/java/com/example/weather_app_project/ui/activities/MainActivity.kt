package com.example.weather_app_project.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseActivity
import com.example.weather_app_project.databinding.ActivityMainBinding
import com.example.weather_app_project.interfaces.HomeMainInterface
import com.example.weather_app_project.ui.fragments.HomeFragment
import com.example.weather_app_project.ui.fragments.NextForecastFragment
import com.example.weather_app_project.ui.fragments.SavedLocationFragment
import com.example.weather_app_project.ui.fragments.ThreeHoursFragment
import com.example.weather_app_project.utils.ChangeDate
import com.example.weather_app_project.utils.ChangeIcon
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.Normalizer
import java.util.regex.Pattern


class MainActivity : BaseActivity<ActivityMainBinding>(), HomeMainInterface {
    private lateinit var searchIsland: RelativeLayout
    //private lateinit var savedLocation: FrameLayout
    private lateinit var exitButton: View
    private var homeFragment = HomeFragment.newInstance()
    private var nextForecastFragment = NextForecastFragment.newInstance()
    private var threeHoursFragment = ThreeHoursFragment.newInstance()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat = ""
    private var long = ""

    override fun inflateBinding(): ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchIsland = findViewById(R.id.search_island)
//        savedLocation = findViewById(R.id.container_saved_today)

        val edtSearch = findViewById<EditText>(R.id.edt_search)
        val lnrSuggestions = findViewById<LinearLayout>(R.id.lnr_suggestions)

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().toUnaccented()
                lnrSuggestions.removeAllViews() // Xóa tất cả các TextView hiện tại

                // Tìm kiếm các thành phố phù hợp và tạo TextView tương ứng
                cities.filter { it.toUnaccented().contains(input, ignoreCase = true) }.forEach { city ->
                    val textView = TextView(this@MainActivity).apply {
                        text = city
                        setTextColor(Color.argb(204, 255, 255, 255))
                        setOnClickListener {
                            onCitySelected(city)
                            searchIsland.visibility = View.INVISIBLE
                        }
                    }
                    lnrSuggestions.addView(textView)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
    })

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                1
//            )
//            return
//        }
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location ->
//                // Sử dụng vị trí
//                lat = location?.latitude.toString()
//                long = location?.longitude.toString()
//                addFragment(homeFragment,"home", lat, long)
//                addFragment2(threeHoursFragment,"5days", lat, long)
//                addFragment3(nextForecastFragment,"nextForecast", lat, long)
//            }

        addFragment(homeFragment,"home", "", "")
        addFragment2(threeHoursFragment,"5days", "", "")
        addFragment3(nextForecastFragment,"nextForecast", "", "")

        ChangeDate.setDate(findViewById(R.id.root))
        ChangeIcon.setBackgroundBasedOnTime(findViewById(R.id.root))

        exitButton = findViewById(R.id.btn_exit_search)
        exitButton.setOnClickListener {
            searchIsland.visibility = View.INVISIBLE
        }
    }

    override fun onCitySelected(city: String) {
        //val homeFragment = supportFragmentManager.findFragmentById(R.id.container) as HomeFragment
        homeFragment.reloadData(city)
        //val nextForecastFragment = supportFragmentManager.findFragmentById(R.id.container_nextForecast) as NextForecastFragment
        nextForecastFragment.reloadData(city)
        //val threeHoursFragment = supportFragmentManager.findFragmentById(R.id.container5days) as ThreeHoursFragment
        threeHoursFragment.reloadData(city)
    }
    private fun onUserLocation(lat:String, long:String) {
        homeFragment.reloadUserLocation(lat, long)
        nextForecastFragment.reloadUserLocation(lat, long)
        threeHoursFragment.reloadUserLocation(lat, long)
    }

    override fun onLocationButtonClick() {
        searchIsland.visibility = View.VISIBLE
    }
    override fun onMenuButtonClick() {
        startActivity(Intent(this, SavedLocationActivity::class.java))
        finish()
    }
    override fun onFragmentReady() {
        val locationName = intent.getStringExtra("LOCATION_NAME")
        locationName?.let {
            onCitySelected(locationName)
        }
    }

    override fun onLoadReady() {
        onUserLocation(lat, long)
    }
}