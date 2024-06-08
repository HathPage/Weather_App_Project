package com.example.weather_app_project.adapter

import android.view.View
import android.widget.AdapterView
import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseRecyclerViewAdapter
import com.example.weather_app_project.databinding.SavedTodayLayoutBinding
import com.example.weather_app_project.interfaces.OnItemClickListener
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.ui.activities.SavedLocationActivity
import com.example.weather_app_project.utils.ChangeIcon

class SavedLocationAdapter(
    private val itemClickListener: OnItemClickListener
): BaseRecyclerViewAdapter<TodayResponse, SavedTodayLayoutBinding>() {
    override fun getLayout(): Int = R.layout.saved_today_layout

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<SavedTodayLayoutBinding>,
        position: Int
    ) {
        holder.binding.savedLocation = mListData[position]
        val textMain = mListData[position].weather[0].main
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(mListData[position])
        }
        ChangeIcon.setWeatherIcon(holder.binding.root, textMain, R.id.img_main_saved_today)
    }

}