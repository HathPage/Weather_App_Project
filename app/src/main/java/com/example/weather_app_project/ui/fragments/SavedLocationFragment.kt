package com.example.weather_app_project.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app_project.R
import com.example.weather_app_project.adapter.NextForecastAdapter
import com.example.weather_app_project.adapter.SavedLocationAdapter
import com.example.weather_app_project.base.BaseFragment
import com.example.weather_app_project.databinding.SavedTodayFragmentBinding
import com.example.weather_app_project.interfaces.OnItemClickListener
import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.repositories.TodayRepository
import com.example.weather_app_project.ui.activities.MainActivity
import com.example.weather_app_project.viewmodels.TodayViewModel
import com.example.weather_app_project.viewmodels.TodayViewModelFactory

class SavedLocationFragment : BaseFragment<SavedTodayFragmentBinding>(), OnItemClickListener {
    private lateinit var todayViewModel: TodayViewModel
    private lateinit var mSavedTodayAdapter: SavedLocationAdapter
    private val dataList = mutableListOf<TodayResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SavedTodayFragmentBinding = SavedTodayFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        val locations = arrayOf("Ha Long", "Hanoi", "Tinh Hung Yen")
        // Loop through the array and get weather data for each location
        for (location in locations) {
            todayViewModel.getToday(location)
            todayViewModel.todayData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.Loading -> {
                        showLoadingDialog()
                    }
                    is ApiResponse.Success -> {
                        hideLoadingDialog()
                        mSavedTodayAdapter = SavedLocationAdapter(this)
                        response.data?.let {
                            if (!dataList.contains(it)) {
                                dataList.add(it)
                                mSavedTodayAdapter.updateData(dataList)
                                setupRecyclerView()
                            }
                        }
                    }
                    is ApiResponse.Fail -> {
                        hideLoadingDialog()
                    }
                    else -> {}
                }
            }
        }

    }
    private fun setupRecyclerView() {
        binding.rvSavedToday.apply {
            layoutManager = LinearLayoutManager(this@SavedLocationFragment.context)
            adapter = mSavedTodayAdapter
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
        fun newInstance() = SavedLocationFragment()
    }

    override fun onItemClick(todayResponse: TodayResponse) {
        val intent = Intent(activity, MainActivity::class.java).apply {
            putExtra("LOCATION_NAME", todayResponse.name)
        }
        startActivity(intent)
    }
}