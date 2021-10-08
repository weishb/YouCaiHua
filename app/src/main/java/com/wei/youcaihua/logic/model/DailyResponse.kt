package com.wei.youcaihua.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 文件名：DailyResponse
 * 创建者：wei
 * 创建日期：2021/9/27/0027  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：未来几天天气信息实体类
 * https://api.caiyunapp.com/v2.5/KXrWkaUbOLSghsSl/121.6544,25.1552/daily.json
 */
//将所有数据模型类都定义在了DailyResponse内部，可以防止出现和其他接口的数据模型类有同名冲突的情况,比如DailyResponse和RealtimeResponse都有Result，定义在内部，各自引用各自的
data class DailyResponse(val status: String, val result: Result) {
    data class Result(val daily: Daily)
    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)
    data class Temperature(val max: Float, val min: Float)
    data class Skycon(val value: String, val date: Date)
    data class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>, val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)
    data class LifeDescription(val desc: String)
}
