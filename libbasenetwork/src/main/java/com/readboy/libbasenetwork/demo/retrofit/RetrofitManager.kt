package com.readboy.libbasenetwork.demo.retrofit

import com.readboy.ibbasenetwork.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by liujiawei on 18-6-26.
 */
class RetrofitManager {

    private val okHttp: OkHttpClient

    /**
     * 请求头焊接器
     */
    private var headerInterceptor: Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        chain.proceed(originalRequest.newBuilder().apply {
            header("Content-Type", "application/json")
            header("Accept", "application/json")
            method(originalRequest.method(), originalRequest.body())
        }.build())
    }

    init {
        val okHttpBuilder = OkHttpClient.Builder()

        /**
         * 日志焊接器
         */
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        // 添加公共信息 Header
        okHttpBuilder.addInterceptor(headerInterceptor)
        // 设置网络连接失败时自动重试
        okHttpBuilder.retryOnConnectionFailure(true)
        // 设置连接超时
        okHttpBuilder.connectTimeout(15, TimeUnit.SECONDS)
        // 设置写超时
        okHttpBuilder.writeTimeout(10, TimeUnit.SECONDS)
        // 设置读超时
        okHttpBuilder.readTimeout(10, TimeUnit.SECONDS)

        okHttp = okHttpBuilder.build()
    }

    fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttp)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {

        fun get(): RetrofitManager {
            return RetrofitManager()
        }

        val instance: RetrofitManager = get()
    }
}