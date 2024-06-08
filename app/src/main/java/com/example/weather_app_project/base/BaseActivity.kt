package com.example.weather_app_project.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.weather_app_project.R

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    private var _binding: VB? = null
    private val binding: VB
        get() = _binding!!

    abstract fun inflateBinding():VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun addFragment(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
    fun addFragment2(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container5days, fragment)
            .commit()
    }

    fun replaceFragment2(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container5days, fragment)
            .commit()
    }
    fun addFragment3(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_nextForecast, fragment)
            .commit()
    }

    fun replaceFragment3(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_nextForecast, fragment)
            .commit()
    }
    fun addFragment4(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_saved_today, fragment)
            .commit()
    }

    fun replaceFragment4(fragment: Fragment, fragmentTag: String, stack: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_saved_today, fragment)
            .commit()
    }
}