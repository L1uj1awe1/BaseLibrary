package com.readboy.ibbasenetwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.readboy.ibbasenetwork.http.HttpManager
import kotlinx.android.synthetic.main.libbasenetwork_activity_lib_base_network.*

// todo 不知道网络基础库，对于不同header怎么处理，无从下手
// todo 不知道，那些才是最基础的网络依赖库，例如retrofit来说，无从下手
class LibBaseNetworkActivity : AppCompatActivity(), HttpManager.HttpCallback {

    private val TAG = "LibBaseNetworkActivity"

    private var mHttpManager: HttpManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.libbasenetwork_activity_lib_base_network)

        btn_test.setOnClickListener {
            requestForHttp()
        }
    }

    /**
     * http 使用例子
     */
    private fun requestForHttp() {
        mHttpManager = HttpManager()
        mHttpManager?.execute(
                "http://www.zhbuswx.com/RealTime/GetRealTime?id=B9&fromStation=前山总站&_=${System.currentTimeMillis()}",
                this)
    }

    override fun onHttpRequestResult(result: String?) {
        Log.e(TAG, "onHttpRequestResult  result = " + result)
    }

}
