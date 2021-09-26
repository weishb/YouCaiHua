package com.wei.youcaihua.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wei.youcaihua.logic.Repository
import com.wei.youcaihua.logic.model.Place

/**
 * 文件名：PlaceViewModel
 * 创建者：wei
 * 创建日期：2021/9/26/0026  ⭐凌晨三点⭐(っ•̀ω•́)っ✎⁾⁾ 我爱学习
 * 今日心情：♪（＾∀＾●）ﾉ
 * 描述：
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    //2.searchLiveData值产生变化,就会执行switchMap函数，在里面调用Repository.searchPlaces(it)，将返回的LiveData对象转换成一个可观察的LiveData对象，Activity观察placeLiveData对象即可
    val placeLiveData = Transformations.switchMap(searchLiveData) {
        Repository.searchPlaces(it)
    }

    //1.当外部调用此函数时，searchLiveData值产生变化
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}