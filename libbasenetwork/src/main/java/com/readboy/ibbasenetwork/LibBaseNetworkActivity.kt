package com.readboy.ibbasenetwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.readboy.ibbasenetwork.retrofit.DemoApi
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.libbasenetwork_activity_lib_base_network.*

class LibBaseNetworkActivity : AppCompatActivity() {

    private val TAG = "LibBaseNetworkActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.libbasenetwork_activity_lib_base_network)

        btn_test.setOnClickListener {
            requestDemoCode()
        }
    }

    private fun requestDemoCode() {
        try {
            DemoApi.server.demoRequest("B9", "前山总站", System.currentTimeMillis())
                    .doOnSubscribe {
                        Log.e(TAG, "doOnSubscribe 请求前回调")
                    }
                    .doOnEach {
                        Log.e(TAG, "doOnEach 请求后回调 2次")
                    }
                    .subscribeOn(Schedulers.io())
                    .retry(2)
                    .subscribeBy ( // onError,onNext都是非UI线程，这里操作UI需要设置在UI线程中，否则会走onError甚至Crash报错
                            onError = {
                                Log.e(TAG, "onError runOnUiThread")
                                runOnUiThread {
                                    btn_test.visibility = View.GONE
                                }
                            },
                            onNext = {
                                Log.e(TAG, "onNext runOnUiThread")
                            }
                    )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
