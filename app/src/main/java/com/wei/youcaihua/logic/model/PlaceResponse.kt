package com.wei.youcaihua.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 文件名：PlaceResponse
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
data class PlaceResponse(val status: String, val places: List<Place>)

//@SerializedName:由于json中一些字段可能与Kotlin的命名规范不一样，因此使用@SerializedName注解方式，来让json字段和Kotlin字段之间映射关系
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

data class Location(val lng: String, val lat: String)
