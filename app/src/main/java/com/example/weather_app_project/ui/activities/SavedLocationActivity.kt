package com.example.weather_app_project.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseActivity
import com.example.weather_app_project.databinding.ActivitySavedLocationBinding
import com.example.weather_app_project.interfaces.SavedLocationInterface
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.ui.fragments.HomeFragment
import com.example.weather_app_project.ui.fragments.SavedLocationFragment

class SavedLocationActivity: BaseActivity<ActivitySavedLocationBinding>(), SavedLocationInterface {
    override fun inflateBinding(): ActivitySavedLocationBinding = DataBindingUtil.setContentView(this, R.layout.activity_saved_location)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment4(SavedLocationFragment.newInstance(),"savedLocation","")
    }

    override fun onLocationButtonClick() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onItemClick(todayResponse: TodayResponse) {

    }
}