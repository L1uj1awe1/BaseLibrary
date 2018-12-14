package com.readboy.libbasecomponent.helper

/**
 * 防止连续点击
 */
object DelayClick {

    var DELAY_300MS: Long = 300
    var DELAY_600MS: Long = 600
    var DELAY_1200MS: Long = 1200
    var DELAY_2400MS: Long = 2400

    private var lastClickTime: Long = System.currentTimeMillis()

    fun canClickByTime(delayTime: Long): Boolean {
        var ret = false
        val now = System.currentTimeMillis()
        val delay = now - lastClickTime
        if (delay > delayTime || delay < 10) {
            ret = true
        }
        lastClickTime = now
        return ret
    }

}
