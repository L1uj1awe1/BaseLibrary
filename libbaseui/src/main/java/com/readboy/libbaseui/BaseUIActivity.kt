package com.readboy.libbaseui

import android.os.Bundle
import com.readboy.libbaseui.activity.SwipeBackActivity
import com.readboy.libbaseui.toast.ToastHelper
import kotlinx.android.synthetic.main.ui_activity_base_ui.*

/**
 * 基础 UI 库
 *
 * 1、下拉刷新 https://github.com/scwang90/SmartRefreshLayout
 *
 * 2、banner轮播 https://github.com/youth5201314/banner
 *
 * 3、RecycleView
 *
 * 4、系统 bar 管理
 *
 * 5、Activity 侧滑推出
 *
 *
 */
class BaseUIActivity : SwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_activity_base_ui)

        btn.setOnClickListener {
            ToastHelper.show(this, "aaaaaa")
        }
    }
}
