package com.example.weather_app_project.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.weather_app_project.R
import java.util.Calendar

class ChangeIcon {
    companion object {
        fun setBackgroundBasedOnTime(view: View) {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
//            val minute = calendar.get(Calendar.MINUTE)
            val backgroundRes = if ((hour >= 14) || (hour <= 6)) {
                R.drawable.night_bg
            } else {
                R.drawable.day_bg
            }
            view.setBackgroundResource(backgroundRes)
        }
        fun setSavedBackgroundBasedOnTime(view: View, iconViewId: Int) {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
//            val minute = calendar.get(Calendar.MINUTE)
            val backgroundRes = if ((hour >= 14) || (hour <= 6)) {
                R.drawable.bg_save_night
            } else {
                R.drawable.icon_saved
            }
            val bg: RelativeLayout = view.findViewById(iconViewId)
            bg.setBackgroundResource(backgroundRes)
        }
        fun setWeatherIcon(view: View, textMain: String?, iconViewId: Int){
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val iconRes: Int
            if ((hour >= 14) || (hour <= 6)) {
                 iconRes = when (textMain?.lowercase()) {
                     "clear" -> R.drawable.moon
                     "clouds" -> R.drawable.moon_star_cloud
                     "rain" -> R.drawable.moon_rain
                     "snow" -> R.drawable.moon_rain
                     "thunderstorm" -> R.drawable.thunder
                     else -> R.drawable.moon_star_cloud
                }
            } else {
                 iconRes = when (textMain?.lowercase()) {
                     "clear" -> R.drawable.sun
                     "clouds" -> R.drawable.sun_clouds
                     "rain" -> R.drawable.rain
                     "snow" -> R.drawable.noun_rain
                     "thunderstorm" -> R.drawable.thunder
                     else -> R.drawable.sun_and_little_clouds
                }
            }
            val iconView: ImageView = view.findViewById(iconViewId)
            iconView.setImageResource(iconRes)
        }
    }
}