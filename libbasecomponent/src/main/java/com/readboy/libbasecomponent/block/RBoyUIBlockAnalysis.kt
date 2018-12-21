package com.readboy.libbasecomponent.block

import android.os.Looper

/**
 * UI 阻塞检查
 *
 * https://blog.csdn.net/lmj623565791/article/details/58626355
 *
 * Application -> onCreate() 初始化
 *
 * if (BuildConfig.DEBUG) {
 *     RBoyUIBlockAnalysis.start()
 *  }
 */
object RBoyUIBlockAnalysis {

    private val TAG_START = ">>>>> Dispatching"
    private val TAG_END = "<<<<< Finished"

    fun start(daley: Long = LogMonitor.instance.TIME_BLOCK) {

        /**
         * Android UI线程中有个Looper，在其loop方法中会不断取出Message，调用其绑定的Handler在UI线程进行执行
         *
         * 执行开始 打印 >>>>> Dispatching
         * 执行结束 打印 <<<<< Finished
         *
         * 检测到开始时候，开启指定时间的延迟打印
         * 如果在延迟时间内，loop()方法还未执行到结束打印 <<<<< Finished的步骤，则会输出自定义打印，找到 UI 卡顿的地方
         */
        Looper.getMainLooper().setMessageLogging { x ->

            if (x.startsWith(TAG_START)) {
                LogMonitor.instance.startMonitor(daley)
            }
            if (x.startsWith(TAG_END)) {
                LogMonitor.instance.removeMonitor()
            }
        }

    }
}