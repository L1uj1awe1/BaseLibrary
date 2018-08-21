package com.readboy.ibbasenetwork.retrofit

/**
 * Created by liujiawei on 18-6-27.
 */
data class BusStatusBean (
        val data: ArrayList<BusStatusListBean>,
        val flag: Int
)

data class BusStatusListBean (
        val BusNumber: String,
        val CurrentStation: String,
        val LastPosition: String
)
