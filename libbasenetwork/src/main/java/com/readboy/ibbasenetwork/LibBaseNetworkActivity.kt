package com.readboy.ibbasenetwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.readboy.ibbasenetwork.helper.NetworkHelper
import com.readboy.ibbasenetwork.http.HttpManager
import kotlinx.android.synthetic.main.network_activity_lib_base_network.*
import okhttp3.*
import java.io.IOException

class LibBaseNetworkActivity : AppCompatActivity(), HttpManager.HttpCallback {

    private val TAG = "LibBaseNetworkActivity"

    private var mHttpManager: HttpManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.network_activity_lib_base_network)

        Log.e(TAG, "判断网络是否连接 = ${NetworkHelper.isConnected()}")
        Log.e(TAG, "判断网络是否可用 = ${NetworkHelper.isAvailableByPing()}")
        Log.e(TAG, "判断移动数据是否打开 = ${NetworkHelper.getMobileDataEnabled()}")
        Log.e(TAG, "判断网络是否是移动数据 = ${NetworkHelper.isMobileData()}")
        Log.e(TAG, "判断网络是否是 4G = ${NetworkHelper.is4G()}")
        Log.e(TAG, "判断 wifi 是否打开 = ${NetworkHelper.getWifiEnabled()}")
        Log.e(TAG, "判断 wifi 是否连接状态 = ${NetworkHelper.isWifiConnected()}")
        Log.e(TAG, "判断 wifi 数据是否可用 = ${NetworkHelper.isWifiAvailable()}")
        Log.e(TAG, "获取移动网络运营商名称 = ${NetworkHelper.getNetworkOperatorName()}")
        Log.e(TAG, "获取当前网络类型 = ${NetworkHelper.getNetworkType().name}")
        Log.e(TAG, "获取 IP 地址 = ${NetworkHelper.getIPAddress(true)}")
        Log.e(TAG, "根据 WiFi 获取网络 IP 地址 = ${NetworkHelper.getIpAddressByWifi()}")
        Log.e(TAG, "根据 WiFi 获取网关 IP 地址 = ${NetworkHelper.getGatewayByWifi()}")
        Log.e(TAG, "根据 WiFi 获取网关 IP 地址 = ${NetworkHelper.getNetMaskByWifi()}")
        Log.e(TAG, "根据 WiFi 获取服务端 IP 地址 = ${NetworkHelper.getServerAddressByWifi()}")

        btn_test.setOnClickListener {
            requestForHttpURLConnection()
        }

        btn_test2.setOnClickListener {
            Thread({
                requestForOkHttpSync()
            }).start()
        }

        btn_test3.setOnClickListener {
            requestForOkHttpAsync()
        }
    }

    /**
     * HttpURLConnection 使用例子
     */
    private fun requestForHttpURLConnection() {
        mHttpManager = HttpManager()
        mHttpManager?.execute(
                "http://www.zhbuswx.com/RealTime/GetRealTime?id=B9&fromStation=前山总站&_=${System.currentTimeMillis()}",
                this)
    }
    override fun onHttpRequestResult(result: String?) {
        Log.e(TAG, "onHttpRequestResult  result = " + result)
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

}
