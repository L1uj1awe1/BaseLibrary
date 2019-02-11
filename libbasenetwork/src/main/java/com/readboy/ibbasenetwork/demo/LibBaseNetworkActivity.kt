package com.readboy.ibbasenetwork.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.readboy.ibbasenetwork.R
import com.readboy.ibbasenetwork.demo.retrofit.DemoApi
import com.readboy.ibbasenetwork.helper.BoyNetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.network_activity_lib_base_network.*
import okhttp3.*
import java.io.IOException

class LibBaseNetworkActivity : AppCompatActivity() {

    private val TAG = "LibBaseNetworkActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.network_activity_lib_base_network)

        Log.e(TAG, "判断网络是否连接 = ${BoyNetworkHelper.isConnected()}")
        Log.e(TAG, "判断网络是否可用 = ${BoyNetworkHelper.isAvailableByPing()}")
        Log.e(TAG, "判断移动数据是否打开 = ${BoyNetworkHelper.getMobileDataEnabled()}")
        Log.e(TAG, "判断网络是否是移动数据 = ${BoyNetworkHelper.isMobileData()}")
        Log.e(TAG, "判断网络是否是 4G = ${BoyNetworkHelper.is4G()}")
        Log.e(TAG, "判断 wifi 是否打开 = ${BoyNetworkHelper.getWifiEnabled()}")
        Log.e(TAG, "判断 wifi 是否连接状态 = ${BoyNetworkHelper.isWifiConnected()}")
        Log.e(TAG, "判断 wifi 数据是否可用 = ${BoyNetworkHelper.isWifiAvailable()}")
        Log.e(TAG, "获取移动网络运营商名称 = ${BoyNetworkHelper.getNetworkOperatorName()}")
        Log.e(TAG, "获取当前网络类型 = ${BoyNetworkHelper.getNetworkType().name}")
        Log.e(TAG, "获取 IP 地址 = ${BoyNetworkHelper.getIPAddress(true)}")
        Log.e(TAG, "根据 WiFi 获取网络 IP 地址 = ${BoyNetworkHelper.getIpAddressByWifi()}")
        Log.e(TAG, "根据 WiFi 获取网关 IP 地址 = ${BoyNetworkHelper.getGatewayByWifi()}")
        Log.e(TAG, "根据 WiFi 获取网关 IP 地址 = ${BoyNetworkHelper.getNetMaskByWifi()}")
        Log.e(TAG, "根据 WiFi 获取服务端 IP 地址 = ${BoyNetworkHelper.getServerAddressByWifi()}")


        btn_test2.setOnClickListener {
            Thread {
                requestForOkHttpSync()
            }.start()
        }

        btn_test3.setOnClickListener {
            requestForOkHttpAsync()
        }
    }

    /**
     * OkHttp Sync 使用例子
     */
    private fun requestForOkHttpSync(): String? {
        try {
            val url =  "http://www.zhbuswx.com/RealTime/GetRealTime?id=B9&fromStation=前山总站&_=${System.currentTimeMillis()}"
            val okHttpClient = OkHttpClient.Builder().build()
            val request = Request.Builder()
                    .url(url)
                    .build()

            val response = okHttpClient.newCall(request).execute()
            Log.e(TAG, "sync response = ${response.body()?.string()}")
            return response.body()?.string()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "sync response = null")
            return null
        }
    }

    /**
     * OkHttp Async 使用例子
     */
    private fun requestForOkHttpAsync() {
        try {
            val url =  "http://www.zhbuswx.com/RealTime/GetRealTime?id=B9&fromStation=前山总站&_=${System.currentTimeMillis()}"
            val okHttpClient = OkHttpClient.Builder().build()
            val request = Request.Builder()
                    .url(url)
                    .build()

            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e(TAG, "async response = onFailure")
                }

                override fun onResponse(call: Call?, response: Response?) {
                    Log.e(TAG, "async response = ${response?.body()?.string()}")
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun requestForRetrofit() {
        DemoApi.server.demo("param1", "param2")
                .retry(2)
                .unsubscribeOn(Schedulers.io()) //被观察者在子线程中处理
                .subscribeOn(Schedulers.io()) //被观察者在子线程中处理
                .observeOn(AndroidSchedulers.mainThread()) //将结果发送到主线程中处理，需要引入RxAndroid
                .subscribe({
                    // TODO 请求成功，直接返回 DemoBean类 型
                }, {
                    // TODO 请求失败
                })
    }

}
