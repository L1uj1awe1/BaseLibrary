# LibBaseNetwork - 基础网络库

关于网络部分的基础服务配置，包含：

> - helper
> - receiver
> - wedget
> - socket
> - http
> - retrofit

---
## helper

> - 判断网络是否可用
> - 判断网络类型是否为 4G 网络
> - 判断网络类型是否为 WI-FI 网络

---
## receiver

> - ConnectivityManager 监听
  - 当前WI-FI连接可用
  - 当前移动数据网络连接可用
  - 当前没有网络连接，请确保您已经打开网络

> - WifiManager 监听
  - 当前WI-FI连接可用
  - 当前WI-FI连接断开

---
## wedget

> - 4G 流量提示框
> - 网络断开 提示框

---
## socket

> WebSocketClient 使用封装

---
## http

> HttpURLConnection + AsycnTask 的原生网络库封装，不需要依赖任何第三方网络框架

---
## retrofit

> 关于retrofit快速上手的例子，包含api、bean、使用、依赖

## 目前遇到的困难

> - 不知道在不同header的情况下，如果封装构造基础网络库请求
> - 对于Retrofit2.0，由于Rxjava的参与，不知道是否需要引入Rxjava
> - 需要参考其他开发者优秀的网络基础库，逐步健壮自己。

标签： Android
