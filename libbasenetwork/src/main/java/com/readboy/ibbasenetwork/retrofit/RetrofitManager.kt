package com.readboy.ibbasenetwork.retrofit

import android.os.Build
import com.readboy.ibbasenetwork.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 使用Retrofit，可以参考 DemoApi、DemoBean、RetrofitManager 和 Activity 的调用例子进行构建应用的 api 框架
 */
class RetrofitManager {

    //设置头
    private var headerInterceptor: Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        chain.proceed(originalRequest.newBuilder().apply {
            header("AppType", "Android")
            header("Content-Type", "application/json")
            header("Accept", "application/json")
            header("randKey", "${System.currentTimeMillis()}")
            header("Device", Build.MODEL)
            header("Version-Name", BuildConfig.VERSION_NAME)
            header("Version-Code", BuildConfig.VERSION_CODE.toString())
            method(originalRequest.method(), originalRequest.body())
        }.build())
    }

    private val okHttp: OkHttpClient

    init {
        val okHttpBuilder = OkHttpClient.Builder()
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
        // 添加 cache
//        okHttpBuilder.cache(Cache(context.cacheDir, cacheSize)) // 不知道为什么会crash

        okHttp = okHttpBuilder.build()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .client(okHttp)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
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