package com.readboy.baselib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BaseLibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baselib_activity_base_library)
    }
}
