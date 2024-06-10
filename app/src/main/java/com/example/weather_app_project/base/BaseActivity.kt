package com.example.weather_app_project.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.weather_app_project.R
import java.text.Normalizer
import java.util.regex.Pattern

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
    val cities = arrayOf(
        "Hà Nội", "Tỉnh Hưng Yên", "Hải Phòng", "Tỉnh Vĩnh Phúc", "Tỉnh Bắc Ninh", "Tỉnh Hà Nam",
        "Tỉnh Nam Định", "Tỉnh Ninh Bình", "Tỉnh Thái Bình", "Tỉnh Phú Thọ", "Tỉnh Hà Giang",
        "Tỉnh Tuyên Quang", "Tỉnh Cao Bằng", "Tỉnh Bắc Kạn", "Tỉnh Lạng Sơn", "Tỉnh Quảng Ninh",
        "Tỉnh Yên Bái", "Tỉnh Lào Cai", "Tỉnh Sơn La", "Tỉnh Hòa Bình", "Tỉnh Lai Châu",
        "Tỉnh Điện Biên", "Tỉnh Bắc Giang", "Tỉnh Thanh Hóa", "Tỉnh Nghệ An", "Tỉnh Hà Tĩnh",
        "Tỉnh Quảng Bình", "Tỉnh Quảng Trị", "Tỉnh Thừa Thiên Huế", "Tỉnh Quảng Nam", "Tỉnh Quảng Ngãi",
        "Tỉnh Bình Định", "Tỉnh Phú Yên", "Tỉnh Khánh Hòa", "Tỉnh Ninh Thuận", "Tỉnh Bình Thuận",
        "Kon Tum", "Tỉnh Gia Lai", "Tỉnh Lâm Đồng", "Tỉnh Bình Phước", "Hạ Long",
        "Tỉnh Tây Ninh", "Tỉnh Bình Dương", "Tỉnh Đồng Nai", "Vũng Tàu", "Tỉnh Long An",
        "Tỉnh Tiền Giang", "Tỉnh Bến Tre", "Tỉnh Vĩnh Long", "Tỉnh Trà Vinh", "Tỉnh Hậu Giang",
        "Tỉnh Kiên Giang", "Tỉnh An Giang", "Tỉnh Đồng Tháp", "Cần Thơ", "Tỉnh Sóc Trăng",
        "Tỉnh Bạc Liêu", "Tỉnh Cà Mau", "Đà Nẵng", "Bà Rịa", "Long An", "Thành phố Hồ Chí Minh", "Đà Lạt"
    )
    fun String.toUnaccented(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(temp).replaceAll("")
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