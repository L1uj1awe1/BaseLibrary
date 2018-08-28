package com.readboy.libbaseui.bar

import android.app.Activity
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.blankj.utilcode.util.BarUtils

/**
 * 状态栏、通知栏、导航栏 控制管理
 */
object BarHelper {

    /**
     * 设置全屏
     */
    fun setFullScreen(activity: Activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        var flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            flags = flags or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        activity.window.decorView.systemUiVisibility = flags
    }

    /**
     * 设置状态栏是否可见
     */
    fun setStatusBarVisibility(activity: Activity, isVisible: Boolean) {
        BarUtils.setStatusBarVisibility(activity, isVisible)
    }

    /**
     * 设置状态栏是否为浅色模式
     */
    fun setStatusBarLightMode(activity: Activity, isVisible: Boolean) {
        BarUtils.setStatusBarLightMode(activity, isVisible)
    }

    /**
     * 设置状态栏颜色
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        BarUtils.setStatusBarColor(activity, color)
    }

    /**
     * 设置状态栏透明度
     */
    fun setStatusBarAlpha(activity: Activity, alpha: Int) {
        BarUtils.setStatusBarAlpha(activity, alpha)
    }

    /**
     * 为 DrawerLayout 设置状态栏颜色
     */
    fun setStatusBarColor4Drawer(activity: Activity, drawerLayout: DrawerLayout, fakeStatusBar: View, color: Int, isTop: Boolean) {
        BarUtils.setStatusBarColor4Drawer(activity, drawerLayout, fakeStatusBar, color, isTop)
    }

    /**
     * 为 DrawerLayout 设置状态栏透明度
     */
    fun setStatusBarAlpha4Drawer(activity: Activity, drawerLayout: DrawerLayout, fakeStatusBar: View, alpha: Int, isTop: Boolean) {
        BarUtils.setStatusBarAlpha4Drawer(activity, drawerLayout, fakeStatusBar, alpha, isTop)
    }

    /**
     * 获取 ActionBar 高度
     */
    fun getActionBarHeight(): Int {
        return BarUtils.getActionBarHeight()
    }

    /**
     * 设置通知栏是否可见
     */
    fun setNotificationBarVisibility(isVisible: Boolean) {
        BarUtils.setNotificationBarVisibility(isVisible)
    }

    /**
     * 获取导航栏高度
     */
    fun getNavBarHeight(): Int {
        return BarUtils.getNavBarHeight()
    }

    /**
     * 设置导航栏沉浸式
     */
    fun setNavBarImmersive(activity: Activity) {
        BarUtils.setNavBarImmersive(activity)
    }

    /**
     * 设置导航栏颜色
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setNavBarColor(activity: Activity, color: Int) {
        BarUtils.setNavBarColor(activity, color)
    }

    /**
     * 获取导航栏颜色
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getNavBarColor(activity: Activity): Int {
        return BarUtils.getNavBarColor(activity)
    }

    /**
     * 判断导航栏是否可见
     */
    fun isNavBarVisible(activity: Activity): Boolean {
        return BarUtils.isNavBarVisible(activity)
    }

    /**
     * 为 view 增加 MarginTop 为状态栏高度
     */
    fun addMarginTopEqualStatusBarHeight(view: View) {
        BarUtils.addMarginTopEqualStatusBarHeight(view)
    }

    /**
     * 为 view 减少 MarginTop 为状态栏高度
     */
    fun subtractMarginTopEqualStatusBarHeight(view: View) {
        BarUtils.subtractMarginTopEqualStatusBarHeight(view)
    }

    /**
     * 判断状态栏是否可见
     */
    fun isStatusBarVisible(activity: Activity): Boolean {
        return BarUtils.isStatusBarVisible(activity)
    }

    /**
     * 获取状态栏高度（px）
     */
    fun getStatusBarHeight(): Int {
        return BarUtils.getStatusBarHeight()
    }

    /**
     * 设置导航栏是否可见
     */
    fun setNavBarVisibility(activity: Activity, isVisible: Boolean) {
        BarUtils.setNavBarVisibility(activity, isVisible)
    }

}