package com.example.weather_app_project.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_app_project.R
//import com.example.weather_app_project.adapter.WeatherAdapter
import com.example.weather_app_project.base.BaseFragment
import com.example.weather_app_project.databinding.TodayFragmentBinding
import com.example.weather_app_project.interfaces.HomeMainInterface
import com.example.weather_app_project.viewmodels.TodayViewModel
import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.repositories.TodayRepository
import com.example.weather_app_project.utils.ChangeIcon
import com.example.weather_app_project.viewmodels.TodayViewModelFactory
import kotlin.math.roundToInt

class HomeFragment : BaseFragment<TodayFragmentBinding>() {
    private lateinit var locationButton: View
    private var listener: HomeMainInterface? = null
    private lateinit var todayViewModel: TodayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeMainInterface) {
            listener = context
        } else {
            throw RuntimeException("$context must implement HomeMainInterface")
        }
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): TodayFragmentBinding = TodayFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        locationButton = view.findViewById(R.id.location)
        locationButton.setOnClickListener { listener?.onLocationButtonClick() }

        todayViewModel.getToday()
        todayViewModel.todayData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    showLoadingDialog()
                }
                is ApiResponse.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        val temp = (it.main.temp - 273.15).roundToInt()
                        binding.tempTextView.text = getString(R.string.temperature_format, temp)
                        val tempMax = (it.main.feelsLike - 273.15).roundToInt()
                        binding.tvFeelNum.text = getString(R.string.temperature_format, tempMax)
                        val windSpeed = (it.wind.speed *3.6)
                        binding.tvWindSpeed.text = getString(R.string.wind_format, windSpeed)
                        val humidity = it.main.humidity
                        binding.tvHumidityPercent.text = getString(R.string.percent, humidity)
                        val description = it.weather.first().description.capitalizeFirstLetter()
                        binding.tvDescription.text = description
                        val location = it.name
                        binding.tvLocation.text= location
                        val textMain = it.weather.first().main
                        binding.tvMainWt.text = textMain
                        val rain = it.wind.speed
                        binding.tvRain1h.text = getString(R.string.rain_format, rain)
                        ChangeIcon.setWeatherIcon(binding.root, textMain, R.id.img_main)
                    }
                }
                is ApiResponse.Fail -> {
                    hideLoadingDialog()
                }
            }
        }
    }

    private fun initViewModel() {
        val application = activity?.application
        val todayRepository = TodayRepository()
        val todayViewModelFactory = TodayViewModelFactory(application!!,todayRepository)
        todayViewModel = ViewModelProvider(this, factory = todayViewModelFactory)[TodayViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
    private fun String.capitalizeFirstLetter(): String {
        return this.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}