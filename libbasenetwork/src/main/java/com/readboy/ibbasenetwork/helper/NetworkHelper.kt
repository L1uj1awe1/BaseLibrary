package com.readboy.ibbasenetwork.helper

import android.content.Context
import android.net.ConnectivityManager

/**
 * 与网络相关工具，例如
 *
 * 1、判断网络连接状态
 * 2、判断网络连接类型
 *
 */
object NetworkHelper {

    /**
     * @aim 判断网络是否可用
     */
    fun isNetworkReachable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val current = cm.activeNetworkInfo ?: return false
        return current.isAvailable
    }

    /**
     * @aim 判断网络类型是否为 4G 网络
     */
    fun isMobileNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo ?: return false
        return isNetworkReachable(context) && info.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * @aim 判断网络类型是否为 WI-FI 网络
     */
    fun isWIFINetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo ?: return false
        return isNetworkReachable(context) && info.type == ConnectivityManager.TYPE_WIFI
    }

}