package com.example.weather_app_project.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.weather_app_project.R
import com.example.weather_app_project.base.BaseActivity
import com.example.weather_app_project.databinding.ActivitySavedLocationBinding
import com.example.weather_app_project.interfaces.SavedLocationInterface
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.ui.fragments.HomeFragment
import com.example.weather_app_project.ui.fragments.SavedLocationFragment
import com.example.weather_app_project.utils.ChangeIcon
import java.text.Normalizer
import java.util.regex.Pattern

class SavedLocationActivity: BaseActivity<ActivitySavedLocationBinding>(), SavedLocationInterface {
    private lateinit var exitButton: View
    private lateinit var backMenuButton: View
    private lateinit var addButton: View
    private lateinit var deleteButton: View
    private var savedLocationFragment = SavedLocationFragment.newInstance()
    companion object {
        var status: Int = 1
    }
    override fun inflateBinding(): ActivitySavedLocationBinding = DataBindingUtil.setContentView(this, R.layout.activity_saved_location)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment4(savedLocationFragment,"savedLocation","")
        ChangeIcon.setBackgroundBasedOnTime(findViewById(R.id.root_saved_today))

        val searchIsland = findViewById<RelativeLayout>(R.id.search_island)
        val edtSearch = findViewById<EditText>(R.id.edt_search)
        val lnrSuggestions = findViewById<LinearLayout>(R.id.lnr_suggestions)

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().toUnaccented()
                lnrSuggestions.removeAllViews() // Xóa tất cả các TextView hiện tại

                // Tìm kiếm các thành phố phù hợp và tạo TextView tương ứng
                cities.filter { it.toUnaccented().contains(input, ignoreCase = true) }.forEach { city ->
                    val textView = TextView(this@SavedLocationActivity).apply {
                        text = city
                        setTextColor(Color.argb(204, 255, 255, 255))
                        setOnClickListener {
                            Toast.makeText(context, "Location ${city.toUnaccented()} ", Toast.LENGTH_SHORT).show()
                            saveLocationToPreferences(city.toUnaccented())
                            searchIsland.visibility = View.INVISIBLE
                            refreshSavedLocationFragment()
                        }
                    }
                    lnrSuggestions.addView(textView)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        exitButton = findViewById(R.id.btn_exit_search)
        exitButton.setOnClickListener {
            searchIsland.visibility = View.INVISIBLE
        }
        addButton = findViewById(R.id.btn_add_location)
        addButton.setOnClickListener {
            searchIsland.visibility = View.VISIBLE
        }
        backMenuButton = findViewById(R.id.btn_menu_location)
        backMenuButton.setOnClickListener {
            onLocationButtonClick()
        }
        deleteButton = findViewById(R.id.btn_delete_location)
        deleteButton.setOnClickListener {
            status*=-1
            if (status == -1)
                deleteButton.setBackgroundResource(R.drawable.btn_delete_on)
            else
                deleteButton.setBackgroundResource(R.drawable.btn_delete)
        }
    }
    private fun saveLocationToPreferences(location: String) {
        val sharedPreferences = getSharedPreferences("weather_app_prefs", Context.MODE_PRIVATE)
        val locations = sharedPreferences.getStringSet("locations", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        if (!locations.contains(location)) {
            locations.add(location)
            val editor = sharedPreferences.edit()
            editor.putStringSet("locations", locations)
            editor.apply()
        }
    }
    fun removeLocationFromPreferences(location: String) {
        val sharedPreferences = getSharedPreferences("weather_app_prefs", Context.MODE_PRIVATE)
        val locations = sharedPreferences.getStringSet("locations", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        if (locations.contains(location)) {
            Toast.makeText(this, "Location $location removed.", Toast.LENGTH_SHORT).show()
            locations.remove(location)
            val editor = sharedPreferences.edit()
            editor.putStringSet("locations", locations)
            editor.apply()
            refreshSavedLocationFragment()
        }
    }
    private fun refreshSavedLocationFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val newFragment = SavedLocationFragment.newInstance()

        fragmentTransaction.replace(R.id.container_saved_today, newFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    override fun onLocationButtonClick() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onItemClick(todayResponse: TodayResponse) {

    }
}