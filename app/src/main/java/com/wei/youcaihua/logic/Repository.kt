package com.wei.youcaihua.logic

import androidx.lifecycle.liveData
import com.wei.youcaihua.logic.network.MyNetWork
import kotlinx.coroutines.Dispatchers

/**
 * 文件名：Repository
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：仓库层的统一封装入口
 */
object Repository {
    /*liveData函数是lifecycle-livedata-ktx库提供的一个功能，可以自动构建并返回一个LiveData对象，然后再他的代码块中提供一个挂起函数的上下文，这样就可以在liveData（）函数的代码块中调用任意的挂起函数了
    * 这里调用MyNetWork.searchPlaces(query)挂起函数来搜索城市数据，然后判断响应状态，使用Kotlin内置的 Result.success方法来包装数据，最后使用emit方法将包装结果发射出去
    * */
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = MyNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}