package com.wei.youcaihua.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wei.youcaihua.logic.Repository
import com.wei.youcaihua.logic.model.Location

/**
 * 文件名：WeatherViewModel
 * 创建者：wei
 * 创建日期：2021/9/27/0027  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    //根据经纬度获取天气信息，并转化为供Activity观察的LiveData对象
    val weatherLiveData = Transformations.switchMap(locationLiveData) {
        Repository.refreshWeather(it.lng, it.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}