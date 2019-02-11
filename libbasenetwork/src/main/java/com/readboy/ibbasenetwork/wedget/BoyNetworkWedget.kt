package com.readboy.ibbasenetwork.wedget

import android.content.Context
import android.support.v7.app.AlertDialog
import com.readboy.ibbasenetwork.R
import com.readboy.ibbasenetwork.helper.BoyNetworkHelper

/**
 * 常规的网络提示，例如：
 *
 * 1、4G流量提示
 * 2、网络断开提示
 *
 */
object BoyNetworkWedget {

    /**
     * @aim 4G 流量提示框
     */
    fun checkMobileNetwork(context: Context) {
        if (BoyNetworkHelper.is4G()) {
            AlertDialog.Builder(context)
                    .setCancelable(true)
                    .setTitle(R.string.network_dialog_title)
                    .setMessage(R.string.network_dialog_4g_msg)
                    .setNegativeButton(R.string.network_dialog_network_settings) { _, _ ->
                        BoyNetworkHelper.openWifiSettings(context)
                    }
                    .setPositiveButton(R.string.network_dialog_network_ignore, null)
                    .show()
        }
    }

    /**
     * @aim 网络断开 提示框
     */
    fun checkNetworkReachable(context: Context) {
        if (!BoyNetworkHelper.isConnected()) {
            AlertDialog.Builder(context)
                    .setCancelable(true)
                    .setTitle(R.string.network_dialog_title)
                    .setMessage(R.string.network_dialog_network_msg)
                    .setNegativeButton(R.string.network_dialog_network_settings) { _, _ ->
                        BoyNetworkHelper.openWifiSettings(context)
                    }
                    .setPositiveButton(R.string.network_dialog_network_ignore, null)
                    .show()
        }
    }

}