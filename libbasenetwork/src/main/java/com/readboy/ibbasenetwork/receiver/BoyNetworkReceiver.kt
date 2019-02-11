package com.readboy.ibbasenetwork.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.net.NetworkInfo
import android.os.Parcelable
import android.util.Log

/**
 * 监听网络状态变化
 */
class BoyNetworkReceiver: BroadcastReceiver() {

    private val TAG_WifiManager = "WifiManager"
    private val TAG_ConnectivityManager = "ConnectivityManager"

    interface NetworkChangedOnConnectivityListener{
        fun onNetworkWiFiEnable()
        fun onNetworkMobileEnable()
        fun onNetworkDisable()
    }

    interface NetworkChangedOnWiFiListener{
        fun onWiFiEnable()
        fun onWiFiDiaable()
    }

    var onConnectivityListener: NetworkChangedOnConnectivityListener? = null
    var onWifiListener: NetworkChangedOnWiFiListener? = null

    override fun onReceive(context: Context, intent: Intent) {

        when(intent.action) {
            // 和WI-FI开启/关闭有关，与WI-FI连接无关
            WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
                when(state) {
                    WifiManager.WIFI_STATE_DISABLED -> Log.i(TAG_WifiManager, "WIFI_STATE_DISABLED")
                    WifiManager.WIFI_STATE_DISABLING -> Log.i(TAG_WifiManager, "WIFI_STATE_DISABLING")
                    WifiManager.WIFI_STATE_ENABLING -> Log.i(TAG_WifiManager, "WIFI_STATE_ENABLING")
                    WifiManager.WIFI_STATE_ENABLED -> Log.i(TAG_WifiManager, "WIFI_STATE_ENABLED")
                    WifiManager.WIFI_STATE_UNKNOWN -> Log.i(TAG_WifiManager, "WIFI_STATE_UNKNOWN")
                }
            }

            // 这个监听wifi的连接状态，即连接上一个有效的无线路由
            // 当上边广播的状态是WifiManager.WIFI_STATE_DISABLED或者WifiManager.WIFI_STATE_DISABLING的时候，根本不会接到这个广播
            // 在上边广播接到是WifiManager.WIFI_STATE_ENABLED，同时也会接到这个广播
            // 当然刚打开wifi肯定是没有连接到有效wifi的
            // 这个监听最大的弊端就是会重复接收多条广播，可能导致提示或者操作重复执行多次
            WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                val parcelableExtra: Parcelable = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO)
                val networkInfo: NetworkInfo = parcelableExtra as NetworkInfo
                val state = networkInfo.state
                val isConnected = state == NetworkInfo.State.CONNECTED
                if (isConnected) {
                    Log.i(TAG_WifiManager, "当前WI-FI连接可用")
                    onWifiListener?.onWiFiEnable()
                } else {
                    Log.i(TAG_WifiManager, "当前WI-FI连接断开")
                    onWifiListener?.onWiFiDiaable()
                }
            }

            // 这个监听连接网络的设置，包括wifi和移动数据的打开和关闭
            // 最好用的还是这个监听，wifi如果打开，关闭，以及连接上可用的连接都会接到监听
            // 这个广播的弊端是比上面的监听要慢
            // 如果只是监听wifi还是用上边两个监听配合比较合适
            // 由于上边的广播会重复接收，所以这个虽然慢，但是还不推荐使用，
            ConnectivityManager.CONNECTIVITY_ACTION -> {
                val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = manager.activeNetworkInfo
                if (activeNetwork != null) {
                    if (activeNetwork.isConnected) {
                        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                            Log.i(TAG_ConnectivityManager, "当前WI-FI连接可用")
                            onConnectivityListener?.onNetworkWiFiEnable()
                        } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                            Log.i(TAG_ConnectivityManager, "当前移动数据网络连接可用")
                            onConnectivityListener?.onNetworkMobileEnable()
                        }
                    } else {
                        Log.i(TAG_ConnectivityManager, "当前没有网络连接，请确保您已经打开网络")
                        onConnectivityListener?.onNetworkDisable()
                    }
                } else {
                    Log.i(TAG_ConnectivityManager, "当前没有网络连接，请确保您已经打开网络")
                    onConnectivityListener?.onNetworkDisable()
                }
            }
        }
    }

    /**
     * 动态注册
     */
    fun register(context: Context){
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        context.registerReceiver(this, filter)
    }

    /**
     * 动态解除注册
     */
    fun unregister(context: Context){
        context.unregisterReceiver(this)
    }
}
