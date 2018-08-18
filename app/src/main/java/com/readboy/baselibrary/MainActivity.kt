package com.readboy.baselibrary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.readboy.libbaseres.LibBaseResActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://www.jianshu.com/p/6c1d2688ed2d
 *
 * https://blog.csdn.net/u010818425/article/details/52441711
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            startActivity(Intent(this, LibBaseResActivity::class.java))
        }
    }
}
