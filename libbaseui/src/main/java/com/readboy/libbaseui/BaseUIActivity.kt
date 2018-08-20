package com.readboy.libbaseui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BaseUIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.libbaseui_activity_base_ui)
    }
}
