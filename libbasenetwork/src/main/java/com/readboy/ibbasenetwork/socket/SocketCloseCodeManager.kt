package com.readboy.ibbasenetwork.socket

/**
 * Created by liujiawei on 18-9-8.
 */
object SocketCloseCodeManager {

    // 一般来说 1006 的错误码出现的情况比较常见，该错误码一般出现在断网时。

    /**
     * 保留段, 未使用
     */
    //    0–999

    /**
     * 正常关闭; 无论为何目的而创建, 该链接都已成功完成任务.
     */
    const val CODE_CLOSE_NORMAL = 1000
    /**
     * 终端离开, 可能因为服务端错误, 也可能因为浏览器正从打开连接的页面跳转离开.
     */
    const val CODE_CLOSE_GOING_AWAY = 1001
    /**
     * 由于协议错误而中断连接.
     */
    const val CODE_CLOSE_PROTOCOL_ERROR = 1002
    /**
     * 由于接收到不允许的数据类型而断开连接 (如仅接收文本数据的终端接收到了二进制数据).
     */
    const val CODE_CLOSE_UNSUPPORTED = 1003
    /**
     * 保留. 其意义可能会在未来定义.
     */
    //    1004
    /**
     * 保留. 表示没有收到预期的状态码.
     */
    const val CODE_CLOSE_NO_STATUS = 1005
    /**
     * 保留. 用于期望收到状态码时连接非正常关闭 (也就是说, 没有发送关闭帧).
     */
    const val CODE_CLOSE_ABNORMAL = 1006
    /**
     * 由于收到了格式不符的数据而断开连接 (如文本消息中包含了非 UTF-8 数据).
     */
    const val CODE_Unsupported_Data = 1007
    /**
     * 由于收到不符合约定的数据而断开连接. 这是一个通用状态码, 用于不适合使用 1003 和 1009 状态码的场景.
     */
    const val CODE_Policy_Violation = 1008
    /**
     * 由于收到过大的数据帧而断开连接.
     */
    const val CODE_CLOSE_TOO_LARGE = 1009
    /**
     * 客户端期望服务器商定一个或多个拓展, 但服务器没有处理, 因此客户端断开连接.
     */
    const val CODE_Missing_Extension = 1010
    /**
     * 客户端由于遇到没有预料的情况阻止其完成请求, 因此服务端断开连接.
     */
    const val CODE_Internal_Error = 1011
    /**
     * 服务器由于重启而断开连接.
     */
    const val CODE_Service_Restart = 1012
    /**
     * 服务器由于临时原因断开连接, 如服务器过载因此断开一部分客户端连接.
     */
    const val CODE_Try_Again_Later = 1013
    /**
     * 由 WebSocket标准保留以便未来使用.
     */
    //    1014
    /**
     * 保留. 表示连接由于无法完成 TLS 握手而关闭 (例如无法验证服务器证书).
     */
    const val CODE_TLS_Handshake = 1015
    /**
     * 由 WebSocket标准保留以便未来使用.
     */
    //    1016–1999
    /**
     * 由 WebSocket拓展保留使用.
     */
    //    2000–2999
    /**
     * 可以由库或框架使用.? 不应由应用使用. 可以在 IANA 注册, 先到先得.
     */
    //    3000–3999
    /**
     * 可以由应用使用.
     */
    //    4000–4999

}