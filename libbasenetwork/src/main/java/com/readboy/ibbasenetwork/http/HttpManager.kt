package com.readboy.ibbasenetwork.http

import android.annotation.SuppressLint
import android.os.AsyncTask

/**
 * @aim 原生网络连接，不需要依赖第三方网络库
 *
 * @param url 请求连接，需要自行拼接请求参数
 * @param listener 网络连接监听，需要继承重写回调接口
 *
 */
class HttpManager {

    private var task: HttpAsyncTask? = null
    private var mUrl = ""
    private var listener: HttpCallback? = null

    interface HttpCallback {
        fun onHttpRequestResult(result: String?)
    }

    /**
     * @aim 开始请求
     *
     * @param url 请求连接，需要自行拼接请求参数
     * @param callback 网络连接监听，需要继承重写回调接口
     */
    fun execute(url: String, callback: HttpCallback? = null) {
        mUrl = url
        listener = callback

        cancel()

        task = HttpAsyncTask().execute() as? HttpAsyncTask
    }

    /**
     * 取消请求
     */
    fun cancel() {
        if (task != null && task?.status == AsyncTask.Status.RUNNING) {
            task?.cancel(true)
            task = null
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class HttpAsyncTask: AsyncTask<String, Int, String>() {

        override fun doInBackground(vararg params: String?): String? {
            return HttpConnectManager.connectResult(mUrl)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            listener?.onHttpRequestResult(result)
        }
    }

}