package com.example.weather_app_project.adapter

import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseRecyclerViewAdapter
import com.example.weather_app_project.databinding.MainItemBinding
import com.example.weather_app_project.objects.response.fivedays.ThreeHours
import com.example.weather_app_project.utils.ChangeIcon

class ThreeHoursAdapter: BaseRecyclerViewAdapter<ThreeHours, MainItemBinding>() {
    override fun getLayout(): Int = R.layout.main_item

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<MainItemBinding>,
        position: Int
    ) {
        holder.binding.threeHours = mListData[position]
        val textMain = mListData[position].weather[0].main
        ChangeIcon.setWeatherIcon(holder.binding.root, textMain, R.id.ig)
    }
}