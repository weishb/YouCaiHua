package com.wei.youcaihua.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 文件名：RealtimeResponse
 * 创建者：wei
 * 创建日期：2021/9/27/0027  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：实时天气信息实体类
 */
/*简化后数据
https://api.caiyunapp.com/v2.5/KXrWkaUbOLSghsSl/121.6544,25.1552/realtime.json
{
    "result": {
        "realtime": {
            "air_quality": {
                "aqi": {"chn": 14}
            },
            "skycon": "PARTLY_CLOUDY_DAY",
            "temperature": 29.16
        }
    },
    "status": "ok"
}
* */
//将所有数据模型类都定义在了RealtimeResponse内部，可以防止出现和其他接口的数据模型类有同名冲突的情况,比如DailyResponse和RealtimeResponse都有Result，定义在内部，各自引用各自的
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)
    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}
