package com.wei.youcaihua.logic

import androidx.lifecycle.liveData
import com.wei.youcaihua.logic.model.Weather
import com.wei.youcaihua.logic.network.MyNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * 文件名：Repository
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：仓库层的统一封装入口
 */
/*object Repository {
    *//*liveData函数是lifecycle-livedata-ktx库提供的一个功能，可以自动构建并返回一个LiveData对象，然后再他的代码块中提供一个挂起函数的上下文，这样就可以在liveData（）函数的代码块中调用任意的挂起函数了
    * 这里调用MyNetWork.searchPlaces(query)挂起函数来搜索城市数据，然后判断响应状态，使用Kotlin内置的 Result.success方法来包装数据，最后使用emit方法将包装结果发射出去
    * *//*
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

    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            //coroutineScope函数：任意挂起函数提供协程作用域
            coroutineScope {
                val deferredRealtime = async {
                    MyNetWork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    MyNetWork.getDailyWeather(lng, lat)
                }
                //先协程并发执行，再一起获取结果，提升运行效率
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                } else {
                    Result.failure(RuntimeException("realtimeResponse.status=${realtimeResponse.status}---dailyResponse.status=${dailyResponse.status}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}*/
/*
* todo 上面代码会发现一个问题：由于使用了协程简化网络回调的写法，导致每个封装的网络请求都可能会抛出异常，于是我们必须在仓库层为每个网络请求都进行try catch处理，这无疑增加了仓库层代码的实现复杂度
*  可以在某个统一的入口函数中进行封装，使得只要进行一次try catch处理就行了
* */
object Repository {
    /*liveData函数是lifecycle-livedata-ktx库提供的一个功能，可以自动构建并返回一个LiveData对象，然后再他的代码块中提供一个挂起函数的上下文，这样就可以在liveData（）函数的代码块中调用任意的挂起函数了
    * 这里调用MyNetWork.searchPlaces(query)挂起函数来搜索城市数据，然后判断响应状态，使用Kotlin内置的 Result.success方法来包装数据，最后使用emit方法将包装结果发射出去
    * */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = MyNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        //coroutineScope函数：任意挂起函数提供协程作用域
        coroutineScope {
            val deferredRealtime = async {
                MyNetWork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                MyNetWork.getDailyWeather(lng, lat)
            }
            //先协程并发执行，再一起获取结果，提升运行效率
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtimeResponse.status=${realtimeResponse.status}---dailyResponse.status=${dailyResponse.status}"))
            }
        }
    }

    /*
    * 定义fire函数，这是一个按照liveData函数的参数接收标准定义的一个高阶函数，在fire函数内部先调用一下liveData函数，然后在liveData函数的代码块中统一进行try catch处理，
    * 并在try语句中传入Lambda表达式代码，最终获取执行结果并调用emit发射
    * 内部函数block声明为挂起函数，是为了Lambda表达式中代码拥有挂起函数上下文，可以使用协程
    * */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

}