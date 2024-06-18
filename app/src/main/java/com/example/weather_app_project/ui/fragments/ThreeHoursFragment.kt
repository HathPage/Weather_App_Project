package com.example.weather_app_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app_project.R
import com.example.weather_app_project.adapter.ThreeHoursAdapter
import com.example.weather_app_project.base.BaseFragment
import com.example.weather_app_project.databinding.FiveMainItemFragmentBinding
import com.example.weather_app_project.databinding.MainItemBinding
import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.repositories.FiveDaysRepository
import com.example.weather_app_project.ui.activities.MainActivity
import com.example.weather_app_project.utils.ChangeIcon
import com.example.weather_app_project.viewmodels.FiveDaysViewModel
import com.example.weather_app_project.viewmodels.FiveDaysViewModelFactory

class ThreeHoursFragment : BaseFragment<FiveMainItemFragmentBinding>() {
    private lateinit var fiveDaysViewModel: FiveDaysViewModel
    private lateinit var mThreeHoursAdapter: ThreeHoursAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun reloadData(location: String){
        fiveDaysViewModel.getFiveDays(location)
    }
    fun reloadUserLocation(lat: String, long:String){
        fiveDaysViewModel.getUserFiveDays(lat, long)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FiveMainItemFragmentBinding = FiveMainItemFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

//        val lat = arguments?.getString("lat")
//        val long = arguments?.getString("long")
//        if(lat != null && long != null && MainActivity.locationStatus == 1){
//            MainActivity.locationStatus = 2
//            Toast.makeText(context, "lat: $lat, long: $long", Toast.LENGTH_SHORT).show()
//            reloadUserLocation(lat, long)
//        } else {
//            reloadData("Ha Noi")
//        }
        fiveDaysViewModel.getFiveDays("Ha Noi")

        fiveDaysViewModel.fiveDaysData.observe(viewLifecycleOwner){response->
            when(response){
                is ApiResponse.Loading->{showLoadingDialog()}
                is ApiResponse.Success->{hideLoadingDialog()
                    mThreeHoursAdapter = ThreeHoursAdapter()
                    response.data?.let {
                        val limitedData = it.list.take(8)
                        mThreeHoursAdapter.updateData(limitedData)
                        binding.rvFiveDays.apply {
                            layoutManager = LinearLayoutManager(this@ThreeHoursFragment.context)
                            adapter = mThreeHoursAdapter
                            val recyclerView: RecyclerView = view.findViewById(R.id.rv_five_days)
                            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            recyclerView.layoutManager = layoutManager
                            //ChangeIcon.setWeatherIcon(binding.root, textMain)
                        }
                    }

                }
                is ApiResponse.Fail->{hideLoadingDialog()}
                else -> {}
            }
        }
    }

    private fun initViewModel() {
        val application = activity?.application
        val fiveDaysRepository = FiveDaysRepository()
        val fiveDaysViewModelFactory = FiveDaysViewModelFactory(application!!,fiveDaysRepository)
        fiveDaysViewModel = ViewModelProvider(this, factory = fiveDaysViewModelFactory)[FiveDaysViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThreeHoursFragment()
    }
}