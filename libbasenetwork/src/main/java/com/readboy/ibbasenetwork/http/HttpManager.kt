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
class HttpManager(private val url: String, private val listener: HttpListener? = null) {

    private var task: HttpAsyncTask? = null

    interface HttpListener {
        fun onHttpRequestResult(result: String?)
    }

    /**
     * 开始请求
     */
    fun execute() {
        if (task != null && task?.status == AsyncTask.Status.RUNNING) {
            task?.cancel(true)
            task = null
        }
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
            return HttpConnectManager.connectResult(url)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            listener?.onHttpRequestResult(result)
        }
    }

}