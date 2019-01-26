package com.readboy.libbasecomponent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BaseComponentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comp_activity_base_component)
    }
}
