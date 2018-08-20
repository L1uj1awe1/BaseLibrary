# Android组件化 - 基础库

---
## 基础库类型

### 1、LibBaseUI - 资源库
 - 通用的 **Style、Theme**
 - 通用的 **Color、Dimen、String**
 - 通用的 **Layout**
 - 通用的 **Shape, Selector**
 - 通用的 **图片资源**
 - 通用的 **动画资源**
 - 通用的 **自定义 View**
 - 通用的 **第三方 自定义 View**


---
### 2、LibBaseNetwork - 网络库
 - 网络状态变化监听
 - 网络状态判断
 - 网络状态提示布局
 - 网络请求库

---
### 3、LibBaseDatabase - 数据库

---
### 4、LibBasePicture - 图片加载库

---
### 5、LibBaseComponent - 基础组件库
 - 日志库 Logger、Timber
 - 权限管理 RxPermission

## 基础库配置

### 基础库创建
1、新建基础库测试工程 File -> New Project，用于测试Library
2、新建基础库 File -> New Module，选择 Android Library

### 基础库独立运行配置
考虑到Project可能引入了多个Library，编译时间相对编译单个Library较长，所以Library可以选择配置独立运行，减少编译时间。

1、在Project根目录新建 **dependencies.gradle** 文件，添加编译类型判断标识 **isBuildLibrary**

```gradle
ext {
    isBuildLibrary = true
}
```

2、在 Library -> build-gradle 文件添加编译判断
```gradle
if (isBuildLibrary) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```

3、由于application需要application和activity，因此根据不同类型，配置不同的 **AndroidManifest.xml** 文件
```gradle
    sourceSets {
        main {
            if (isBuildLibrary) {
                manifest.srcFile 'src/main/manifest/debug/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/manifest/release/AndroidManifest.xml'
            }
        }
    }
```

4、防止资源同名导致的编译错误，我们可以在Library -> build-gradle -> android{} 中加入资源前缀标识
```gradle
    resourcePrefix "xxx_"
```

在开发阶段，isBuildLibrary 可以恒为 true，当需要在Project app 中依赖引入 library 或者编译 aar 部署 maven，再改为false。

## 基础库部署
