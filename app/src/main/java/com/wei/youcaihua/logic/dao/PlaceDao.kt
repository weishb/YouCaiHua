package com.wei.youcaihua.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.wei.youcaihua.MyApplication
import com.wei.youcaihua.logic.model.Place

/**
 * 文件名：PlaceDao
 * 创建者：wei
 * 创建日期：2021/10/8/0008  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
object PlaceDao {
    private fun sharedPreferences() = MyApplication.context.getSharedPreferences("weather", Context.MODE_PRIVATE)
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")
}