package com.wei.youcaihua

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 文件名：MyApplication
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "KXrWkaUbOLSghsSl"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}