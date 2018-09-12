package com.readboy.libbasecomponent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * 基础组件库
 *
 * 日志库    https://github.com/orhanobut/logger
 *
 * 权限管理库    https://github.com/tbruyelle/RxPermissions
 *
 * 工具库    https://github.com/Blankj/AndroidUtilCode
 *
 */

class BaseComponentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.libbasecomponent_activity_base_component)
    }
}
