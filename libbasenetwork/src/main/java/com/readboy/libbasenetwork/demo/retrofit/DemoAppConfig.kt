package com.readboy.libbasenetwork.demo.retrofit

import com.readboy.ibbasenetwork.BuildConfig

/**
 * Created by liujiawei on 18-9-14.
 */
object DemoAppConfig {

    val baseUrl: String

    init {

        if (BuildConfig.DEBUG) {
            baseUrl = "I ma the base URL of debug"
        } else {
            baseUrl = "I ma the base URL of release"
        }
    }

}

