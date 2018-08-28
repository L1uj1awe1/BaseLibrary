package com.readboy.ibbasenetwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.readboy.ibbasenetwork.helper.NetworkHelper
import com.readboy.ibbasenetwork.http.HttpManager
import kotlinx.android.synthetic.main.libbasenetwork_activity_lib_base_network.*

// todo 不知道网络基础库，对于不同header怎么处理，无从下手
// todo 不知道，那些才是最基础的网络依赖库，例如retrofit来说，无从下手
class LibBaseNetworkActivity : AppCompatActivity(), HttpManager.HttpCallback {

    private val TAG = "LibBaseNetworkActivity"

    private var mHttpManager: HttpManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.libbasenetwork_activity_lib_base_network)

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
            requestForHttp()
        }

        btn_test2.setOnClickListener {
            NetworkHelper.openWifiSettings(this)
        }

        btn_test3.setOnClickListener {
            NetworkHelper.setWifiEnabled(true)
        }
    }

    /**
     * http 使用例子
     */
    private fun requestForHttp() {
        mHttpManager = HttpManager()
        mHttpManager?.execute(
                "http://www.zhbuswx.com/RealTime/GetRealTime?id=B9&fromStation=前山总站&_=${System.currentTimeMillis()}",
                this)
    }

    override fun onHttpRequestResult(result: String?) {
        Log.e(TAG, "onHttpRequestResult  result = " + result)
    }

}
