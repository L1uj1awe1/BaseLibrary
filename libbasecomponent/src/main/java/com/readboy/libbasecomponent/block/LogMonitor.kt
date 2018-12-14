package com.readboy.libbasecomponent.block

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log

class LogMonitor {

    /**
     * 卡顿触发的时间阈值
     */
    val TIME_BLOCK = 1000L

    private val mLogThread = HandlerThread("log")
    private val mIoHandler: Handler

    /**
     * 打印当前线程执行位置，即 UI 卡顿的位置
     */
    private val mLogRunnable = Runnable {
        val sb = StringBuilder()
        val stackTrace = Looper.getMainLooper().thread.stackTrace
        for (s in stackTrace) {
            sb.append(s.toString() + "\n")
        }
        Log.e("UIBlockAnalysis", sb.toString())
    }

    init {
        mLogThread.start()
        mIoHandler = Handler(mLogThread.looper)
    }

    fun startMonitor(daley: Long = TIME_BLOCK) {
        mIoHandler.postDelayed(mLogRunnable, daley)
    }

    fun removeMonitor() {
        mIoHandler.removeCallbacks(mLogRunnable)
    }

    companion object {

        fun get(): LogMonitor {
            return LogMonitor()
        }

        val instance: LogMonitor = get()
    }
}