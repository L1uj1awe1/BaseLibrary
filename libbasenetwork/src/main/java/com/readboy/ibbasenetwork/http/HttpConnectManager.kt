package com.readboy.ibbasenetwork.http

import com.readboy.ibbasenetwork.wedget.NetworkWedget
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder

/**
 * 原生网络连接库
 */
object HttpConnectManager {

    /**
     * 获取请求返回数据
     */
    fun connectResult(url: String): String? {

        var result: String?
        var con: HttpURLConnection? = null
        var bufferedReader: BufferedReader? = null

        try {
            con = getNormalCon(URLDecoder.decode(url, "UTF-8"))
            con?.connect()

            bufferedReader = BufferedReader(InputStreamReader(con?.inputStream))
            var size: Int
            val bt = CharArray(1024)
            val sb = StringBuffer()
            size = bufferedReader.read(bt, 0, bt.size)
            while (size != -1) {
                sb.append(String(bt, 0, size))
                size = bufferedReader.read(bt, 0, bt.size)
            }
            result = sb.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            result = null
        } finally {
            bufferedReader?.close()
            con?.disconnect()
        }

        return result
    }

    /**
     * 设置请求头发起请求
     */
    private fun getNormalCon(url: String): HttpURLConnection? {
        val conn: HttpURLConnection? = URL(url).openConnection() as HttpURLConnection
        conn?.connectTimeout = 10000
        conn?.readTimeout = 10000
        conn?.useCaches = false
        conn?.setRequestProperty("Accept", "application/json")
        return conn
    }
}