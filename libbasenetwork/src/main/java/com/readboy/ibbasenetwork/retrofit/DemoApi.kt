package com.readboy.ibbasenetwork.retrofit

import io.reactivex.Observable
import retrofit2.http.*

interface DemoService {

    /**
     * @aim 根据公交号码获取路线信息
     * @param id 线路id
     * @param fromStation 方向
     * @param _ 时间戳 System.currentTimeMillis()
     */
    @GET("RealTime/GetRealTime")
    fun demoRequest(
            @Query("id") id: String,
            @Query("fromStation") fromStation: String,
            @Query("_") timestamp: Long
    ): Observable<BusStatusBean>
}

object DemoApi {
    val server: DemoService = RetrofitManager.instance
            .getRetrofit("http://www.zhbuswx.com/")
            .create(DemoService::class.java)
}