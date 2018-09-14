# LibBaseNetwork - 基础网络库

关于网络部分的基础服务配置，包含：

* [Helper](#1)
* [Receiver](#2)
* [Wedget](#3)
* [Socket](#4)
* [Http](#5)

## 使用

```gradle
implementation 'com.readboy.baselibrary:network:x.x.x'
```

---
## <h2 id="1">Helper</h2>

网络相关工具

 - 打开 wifi 设置界面
 - 打开网络设置界面
 - 判断网络是否连接
 - 判断网络是否可用
 - 判断移动数据是否打开
 - 打开或关闭移动数据
 - 判断网络是否是移动数据
 - 判断网络是否是 4G
 - 判断 wifi 是否打开
 - 打开或关闭 wifi
 - 判断 wifi 是否连接状态
 - 判断 wifi 数据是否可用
 - 获取移动网络运营商名称
 - 获取当前网络类型
 - 获取 IP 地址
 - 获取域名 IP 地址
 - 根据 WiFi 获取网络 IP 地址
 - 根据 WiFi 获取网关 IP 地址
 - 根据 WiFi 获取网关 IP 地址
 - 根据 WiFi 获取服务端 IP 地址

---
## <h2 id="2">Receiver</h2>

### ConnectivityManager

 - 当前WI-FI连接可用
 - 当前移动数据网络连接可用
 - 当前没有网络连接，请确保您已经打开网络

### WifiManager

 - 当前WI-FI连接可用
 - 当前WI-FI连接断开

---
## <h2 id="3">Wedget</h2>

 - 4G 流量提示
 - 网络断开提示

---
## <h2 id="4">Socket</h2>

  - WebSocketClient 使用封装

---
## <h2 id="5">Http</h2>

 - HttpURLConnection + AsycnTask
 - OkHttp
 - Retrofit 2.0

---
## Thanks

 感谢以下开源项目的作者，有些功能也用到你们的代码完成。对此如果有什么意见请与我联系！再次感谢！

[Blankj/AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md)

---
标签： Android
