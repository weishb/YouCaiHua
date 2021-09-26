package com.wei.youcaihua.logic.network

import com.wei.youcaihua.MyApplication
import com.wei.youcaihua.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 文件名：PlaceService
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
interface PlaceService {
    //https://api.caiyunapp.com/v2/place?query=北京&token=KXrWkaUbOLSghsSl&lang=zh_CN
    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}