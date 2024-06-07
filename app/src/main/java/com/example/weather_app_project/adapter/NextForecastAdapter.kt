package com.example.weather_app_project.adapter

import android.widget.TextView
import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseRecyclerViewAdapter
import com.example.weather_app_project.databinding.NextForecastLayoutBinding
import com.example.weather_app_project.objects.response.fivedays.ThreeHours
import com.example.weather_app_project.utils.ChangeIcon
import com.example.weather_app_project.utils.GetDay

class NextForecastAdapter: BaseRecyclerViewAdapter<ThreeHours, NextForecastLayoutBinding>() {
    override fun getLayout(): Int = R.layout.next_forecast_layout

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<NextForecastLayoutBinding>,
        position: Int
    ) {
        val day = GetDay.getDay()
        holder.binding.dayOfWeek = day[position % 7]
        holder.binding.forecast = mListData[position]
        val textMain = mListData[position].weather[0].main
        ChangeIcon.setWeatherIcon(holder.binding.root, textMain, R.id.ig)
    }
}