package com.example.weather_app_project.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseActivity
import com.example.weather_app_project.databinding.ActivityMainBinding
import com.example.weather_app_project.interfaces.HomeMainInterface
import com.example.weather_app_project.ui.fragments.HomeFragment
import com.example.weather_app_project.ui.fragments.NextForecastFragment
import com.example.weather_app_project.ui.fragments.ThreeHoursFragment
import com.example.weather_app_project.utils.ChangeDate
import com.example.weather_app_project.utils.ChangeIcon


class MainActivity : BaseActivity<ActivityMainBinding>(), HomeMainInterface {
    private lateinit var searchIsland: RelativeLayout
    private lateinit var exitButton: View
    override fun inflateBinding(): ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchIsland = findViewById(R.id.search_island)

        addFragment(HomeFragment.newInstance(),"home","")
        ChangeDate.setDate(findViewById(R.id.root))

        addFragment2(ThreeHoursFragment.newInstance(),"5days","")
        ChangeIcon.setBackgroundBasedOnTime(findViewById(R.id.root))

        addFragment3(NextForecastFragment.newInstance(),"nextForecast","")

        exitButton = findViewById(R.id.btn_exit_search)
        exitButton.setOnClickListener { searchIsland.visibility = View.INVISIBLE }
    }

    override fun onLocationButtonClick() {
        searchIsland.visibility = View.VISIBLE
    }
}