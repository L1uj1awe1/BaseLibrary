package com.readboy.ibbasenetwork.wedget

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.support.v7.app.AlertDialog
import com.readboy.ibbasenetwork.helper.NetworkHelper

/**
 * 常规的网络提示，例如：
 *
 * 1、4G流量提示
 * 2、网络断开提示
 *
 */
object NetworkWedget {

    /**
     * @aim 4G 流量提示框
     */
    fun checkMobileNetwork(context: Context) {
        if (NetworkHelper.is4G()) {
            AlertDialog.Builder(context)
                    .setCancelable(true)
                    .setTitle("网络提示")
                    .setMessage("您正在使用移动数据网络，继续使用可能会消耗大量流量")
                    .setNegativeButton("网络设置", { _, _ ->
                        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    })
                    .setPositiveButton("继续使用", null)
                    .show()
        }
    }

    /**
     * @aim 网络断开 提示框
     */
    fun checkNetworkReachable(context: Context) {
        if (!NetworkHelper.isConnected()) {
            AlertDialog.Builder(context)
                    .setCancelable(true)
                    .setTitle("网络提示")
                    .setMessage("当前没有网络连接，请确保您已经打开网络")
                    .setNegativeButton("网络设置", { _, _ ->
                        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    })
                    .setPositiveButton("知道了", null)
                    .show()
        }
    }

}