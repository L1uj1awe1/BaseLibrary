package com.readboy.libbasecomponent.helper

/**
 * 防止快速点击
 */
object BoyDelayClick {

    var DELAY_300MS: Long = 300
    var DELAY_600MS: Long = 600
    var DELAY_900MS: Long = 900
    var DELAY_1200MS: Long = 1200
    var DELAY_2400MS: Long = 2400
    var DELAY_4800MS: Long = 4800

    private var lastClickTime: Long = System.currentTimeMillis()

    fun canClickByTime(delayTime: Long): Boolean {
        try {
            var ret = false
            val now = System.currentTimeMillis()
            val delay = now - lastClickTime
            if (delay > delayTime || delay < 10) {
                ret = true
            }
            lastClickTime = now
            return ret
        } catch (e: Exception) {
            e.printStackTrace()
            return true
        }
    }

}
