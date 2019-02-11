package com.readboy.ibbasenetwork.socket

import android.os.Handler
import android.os.Looper
import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft_6455
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

/**
 * WebSocket 长连接
 *
 * 1、实例化 BoySocketManager(serverUrl)
 * 2、继承接口 WebSocketListener，重写回调
 *
 */
class BoySocketManager(serverUrl: String) {

    private val TAG = "BoySocketManager"

    private var mWebSocketClient: WebSocketClient? = null
    private val mHandler = Handler(Looper.getMainLooper())

    interface WebSocketListener{
        fun onOpen(serverHandshake: ServerHandshake)
        fun onMessage(message: String)
        fun onClose(code: Int, reason: String, remote: Boolean)
        fun onError(e: Exception)
    }
    var listener: WebSocketListener? = null

    init {
        try {
            mWebSocketClient = object : WebSocketClient(URI(serverUrl), Draft_6455()) {

                /**
                 * socket 连接开启
                 */
                override fun onOpen(serverHandshake: ServerHandshake) {
                    Log.w(TAG, "WebSocketClient onOpen")
                    listener?.onOpen(serverHandshake)
                }

                /**
                 * 接收服务端发送的socket信息
                 *
                 * 若为json格式信息，可以通过引入Gson，二次封装的回调接口参数直接以对象形式返回
                 *
                 */
                override fun onMessage(message: String) {
                    Log.w(TAG, "WebSocketClient onMessage")
                    listener?.onMessage(message)
                }

                /**
                 * socket 连接关闭
                 *
                 * 考虑到 错误关闭、主动关闭 两种形式，再重写回调中可添加判断，如果是错误关闭，可以尝试 reconnect 重连
                 */
                override fun onClose(code: Int, reason: String, remote: Boolean) {
                    Log.w(TAG, "WebSocketClient onClose code = $code , reason = $reason")
                    if (code != BoySocketCodeManager.CODE_CLOSE_NORMAL && mWebSocketClient != null) {
                        Log.w(TAG, "WebSocketClient reconnect")
                        mHandler.post {
                            reconnect()
                        }
                    } else {
                        listener?.onClose(code, reason, remote)
                    }
                }

                /**
                 * socket 连接错误
                 */
                override fun onError(e: Exception) {
                    Log.w(TAG, "WebSocketClient onError")
                    e.printStackTrace()
                    listener?.onError(e)
                }
            }
            (mWebSocketClient as WebSocketClient).connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * WebSocketClient 实例
     *
     * 当已有方法尚未满足需求的时候，可以获取client
     *
     */
    fun client(): WebSocketClient? {
        return mWebSocketClient
    }

    /**
     * 重连
     */
    fun reconnect() {
        mWebSocketClient?.reconnect()
    }

    /**
     * 关闭
     */
    fun close() {
        if (mWebSocketClient != null && mWebSocketClient?.isOpen ?: false) {
            mWebSocketClient?.close()
            mWebSocketClient = null
        }
    }
}