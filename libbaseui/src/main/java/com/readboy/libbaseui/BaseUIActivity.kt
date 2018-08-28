package com.readboy.libbaseui

import android.os.Bundle
import com.readboy.libbaseui.activity.SwipeBackActivity
import com.readboy.libbaseui.toast.ToastHelper
import kotlinx.android.synthetic.main.ui_activity_base_ui.*

class BaseUIActivity : SwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_activity_base_ui)

        btn.setOnClickListener {
            ToastHelper.show(this, "aaaaaa")
        }
    }
}
