package com.wei.youcaihua.logic.model

/**
 * 文件名：Weather
 * 创建者：wei
 * 创建日期：2021/9/27/0027  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：天气实体类，包括实时天气和未来几天天气
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
