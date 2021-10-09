package com.wei.youcaihua

import android.util.Log

/**
 * 文件名：log
 * 创建者：wei
 * 创建日期：2021/10/9/0009  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */

inline fun <reified T> String.log() {
    Log.d(T::class.java.simpleName, this)
}

inline fun <reified T> Int.log() {
    Log.d(T::class.java.simpleName, this.toString())
}