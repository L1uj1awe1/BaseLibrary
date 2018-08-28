package com.readboy.libbaseui

import android.os.Bundle
import com.readboy.libbaseui.activity.SwipeBackActivity

class BaseUIActivity : SwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.libbaseui_activity_base_ui)
    }
}
