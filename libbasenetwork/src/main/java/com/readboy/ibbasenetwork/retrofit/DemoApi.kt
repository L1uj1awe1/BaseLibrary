package com.readboy.ibbasenetwork.retrofit

import com.readboy.network.data.DemoBean
import io.reactivex.Observable
import retrofit2.http.*

interface DemoService {

    /**
     * @aim 根据公交号码获取路线信息
     * @param handlerName GetLineListByLineName
     * @param key 公交号码，例如：8路
     * @param _ 时间戳 System.currentTimeMillis()
     */
    @GET("Handlers/BusQuery.ashx")
    fun demoRequest(
            @Query("handlerName") handlerName: String,
            @Query("key") key: String,
            @Query("_") timestamp: Long
    ): Observable<DemoBean>
}

object DemoApi {
    val server: DemoService = RetrofitManager.instance
            .getRetrofit("http://www.zhbuswx.com/")
            .create(DemoService::class.java)
}