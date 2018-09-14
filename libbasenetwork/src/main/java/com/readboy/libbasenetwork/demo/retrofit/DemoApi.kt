package com.readboy.libbasenetwork.demo.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by liujiawei on 18-9-14.
 */
interface DemoServer {

    /**
     * 开始答题
     */
    @GET("xx/xx/xx/xx")
    fun demo(
            @Query("param1") lessonId: String,
            @Query("param2") classId: String
    ): Observable<DemoBean>

}

object DemoApi {
    val server: DemoServer = RetrofitManager.instance
            .getRetrofit(DemoAppConfig.baseUrl)
            .create(DemoServer::class.java)
}