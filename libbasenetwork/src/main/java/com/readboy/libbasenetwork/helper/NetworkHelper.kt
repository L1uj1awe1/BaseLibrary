package com.readboy.libbasenetwork.helper

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.blankj.utilcode.util.NetworkUtils

/**
 * 与网络相关工具
 */
object NetworkHelper {

    /**
     * @aim 打开 wifi 设置界面
     */
    fun openWifiSettings(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    /**
     * @aim 打开网络设置界面
     */
    fun openWirelessSettings() {
        try {
            NetworkUtils.openWirelessSettings()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * @aim 判断网络是否连接
     */
    fun isConnected(): Boolean {
        return NetworkUtils.isConnected()
    }

    /**
     * @aim 判断网络是否可用
     */
    fun isAvailableByPing(): Boolean {
        return NetworkUtils.isAvailableByPing()
    }

    /**
     * @aim 判断移动数据是否打开
     */
    fun getMobileDataEnabled(): Boolean {
        return NetworkUtils.getMobileDataEnabled()
    }

    /**
     * @aim 打开或关闭移动数据
     */
    fun setMobileDataEnabled(enabled: Boolean) {
        NetworkUtils.setMobileDataEnabled(enabled)
    }

    /**
     * @aim 判断网络是否是移动数据
     */
    fun isMobileData(): Boolean {
        return NetworkUtils.isMobileData()
    }

    /**
     * @aim 判断网络是否是 4G
     */
    fun is4G(): Boolean {
        return NetworkUtils.is4G()
    }

    /**
     * @aim 判断 wifi 是否打开
     */
    fun getWifiEnabled(): Boolean {
        return NetworkUtils.getWifiEnabled()
    }

    /**
     * @aim 打开或关闭 wifi
     */
    fun setWifiEnabled(enabled: Boolean) {
        NetworkUtils.setWifiEnabled(enabled)
    }

    /**
     * @aim 判断 wifi 是否连接状态
     */
    fun isWifiConnected(): Boolean {
        return NetworkUtils.isWifiConnected()
    }

    /**
     * @aim 判断 wifi 数据是否可用
     */
    fun isWifiAvailable(): Boolean {
        return NetworkUtils.isWifiAvailable()
    }

    /**
     * @aim 获取移动网络运营商名称
     */
    fun getNetworkOperatorName(): String {
        return NetworkUtils.getNetworkOperatorName()
    }

    /**
     * @aim 获取当前网络类型
     */
    fun getNetworkType(): NetworkUtils.NetworkType {
        return NetworkUtils.getNetworkType()
    }

    /**
     * @aim 获取 IP 地址
     */
    fun getIPAddress(useIPv4: Boolean): String {
        return NetworkUtils.getIPAddress(useIPv4)
    }

    /**
     * @aim 获取域名 IP 地址
     */
    fun getDomainAddress(domain: String): String {
        return NetworkUtils.getDomainAddress(domain)
    }

    /**
     * @aim 根据 WiFi 获取网络 IP 地址
     */
    fun getIpAddressByWifi(): String {
        return NetworkUtils.getIpAddressByWifi()
    }

    /**
     * @aim 根据 WiFi 获取网关 IP 地址
     */
    fun getGatewayByWifi(): String {
        return NetworkUtils.getGatewayByWifi()
    }

    /**
     * @aim 根据 WiFi 获取网关 IP 地址
     */
    fun getNetMaskByWifi(): String {
        return NetworkUtils.getNetMaskByWifi()
    }

    /**
     * @aim 根据 WiFi 获取服务端 IP 地址
     */
    fun getServerAddressByWifi(): String {
        return NetworkUtils.getServerAddressByWifi()
    }

}