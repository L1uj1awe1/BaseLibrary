package com.readboy.libbaseui.toast

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast

/**
 * Created by liujiawei on 18-8-28.
 */
object ToastHelper {

    private var mToast: Toast? = null

    @SuppressLint("ShowToast")
    fun show(context: Context?, msg: String, delay: Int = Toast.LENGTH_SHORT) {
        try {
            if (context != null) {
                if (context is Activity && context.isFinishing) {
                    return
                } else {
                    if (mToast != null) {
                        mToast?.cancel()
                        mToast = null
                        mToast = Toast.makeText(context, msg, delay)
                    } else {
                        mToast = Toast.makeText(context, msg, delay)
                    }
                    mToast?.show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("ShowToast")
    fun show(context: Context?, msg: Int, delay: Int = Toast.LENGTH_SHORT) {
        try {
            if (context != null) {
                if (context is Activity && context.isFinishing) {
                    return
                } else {
                    if (mToast != null) {
                        mToast?.cancel()
                        mToast = null
                        mToast = Toast.makeText(context, msg, delay)
                    } else {
                        mToast = Toast.makeText(context, msg, delay)
                    }
                    mToast?.show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}