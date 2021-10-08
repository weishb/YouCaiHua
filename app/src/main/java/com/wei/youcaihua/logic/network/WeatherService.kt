package com.wei.youcaihua.logic.network

import com.wei.youcaihua.MyApplication
import com.wei.youcaihua.logic.model.DailyResponse
import com.wei.youcaihua.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 文件名：WeatherService
 * 创建者：wei
 * 创建日期：2021/9/27/0027  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
interface WeatherService {
    //获取实时天气
    //https://api.caiyunapp.com/v2.5/KXrWkaUbOLSghsSl/121.6544,25.1552/realtime.json
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    //获取未来天气
    //https://api.caiyunapp.com/v2.5/KXrWkaUbOLSghsSl/121.6544,25.1552/daily.json
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}