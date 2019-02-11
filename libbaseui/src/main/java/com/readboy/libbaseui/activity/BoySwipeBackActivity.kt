package com.readboy.libbaseui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jude.swipbackhelper.SwipeBackHelper

/**
 * 滑动退出 Activity
 */
open class BoySwipeBackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SwipeBackHelper.onCreate(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        SwipeBackHelper.onPostCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SwipeBackHelper.onDestroy(this)
    }
}
