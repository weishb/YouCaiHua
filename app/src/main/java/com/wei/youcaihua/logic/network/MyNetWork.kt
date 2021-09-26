package com.wei.youcaihua.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 文件名：MyNetWork
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
object MyNetWork {
    //利用ServiceCreator构建器创建一个PlaceService接口的动态代理对象
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    //创建searchPlaces函数，并调用placeService中searchPlaces方法，发起搜索城市数据的请求
    //当外部调用MyNetWork.searchPlaces方法时，retrofit会立即发起网络请求，同时当前协程也会阻塞，知道服务器响应请求之后，await函数会将解析出来的数据模型对象取出并返回，同时恢复当前协程执行
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    //使用协程简化回调写法：给Call<T>定义扩展函数await()
    private suspend fun <T> Call<T>.await(): T {
        //suspendCoroutine函数必须在协程作用域或者挂起函数中才能调用，接受一个Lambda表达式，主要作用是将当前协程立即挂起，然后再一个普通线程中执行Lambda表达式中的代码
        //Lambda表达式的参数列表会传入一个 Continuation参数，调用它的resume方法或者resumeWithException可以让协程恢复执行
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        it.resume(body)
                    } else {
                        it.resumeWithException(RuntimeException("response.body()为空"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }

            })
        }
    }
}