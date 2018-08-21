# Android组件化 - 基础库

Android组件化，主要解耦模块包含 **业务组件 Component** 和 **基础库 Library**，本项目主要围绕 **基础库 Library** 的封装。

主要包含：

> - LibBaseUI - UI资源库
> - LibBaseNetwork - 网络库
> - LibBaseDatabase - 数据库
> - LibBasePicture - 图片加载库
> - LibBaseComponent - 基础组件库


---
## 基础库类型

---
### 1、LibBaseUI - UI资源库
 - 通用的 **Style、Theme**
 - 通用的 **Color、Dimen、String**
 - 通用的 **Layout**
 - 通用的 **Shape, Selector**
 - 通用的 **图片资源**
 - 通用的 **动画资源**
 - 通用的 **自定义 View**
 - 通用的 **第三方 自定义 View**

```gradle
implementation 'com.readboy.baselibrary:ui:1.0.0'
```

---
### 2、LibBaseNetwork - 网络库
 - 网络状态变化监听
 - 网络状态判断
 - 网络状态提示布局
 - 网络请求库

```gradle
implementation 'com.readboy.baselibrary:network:1.0.0'
```

---
### 3、LibBaseDatabase - 数据库

```gradle
implementation 'com.readboy.baselibrary:database:1.0.0'
```

---
### 4、LibBasePicture - 图片加载库

```gradle
implementation 'com.readboy.baselibrary:picture:1.0.0'
```

---
### 5、LibBaseComponent - 基础组件库
 - 日志库 Logger、Timber
 - 权限管理 RxPermission

```gradle
implementation 'com.readboy.baselibrary:component:1.0.0'
```

---
## 基础库配置

---
### 基础库创建
1、新建基础库测试工程 File -> New Project，用于测试Library
2、新建基础库 File -> New Module，选择 Android Library

---
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

---
### 基础库部署

1、[创建 Bintray 账号](https://bintray.com/f1ght1n9)
2、配置 gradle

 - rootProject -> build-gradle 增加 Bintray 依赖

```gradle
    dependencies {
        classpath 'com.novoda:bintray-release:0.8.0'
    }
```

 - Library -> build-gradle 配置 Bintray 上传信息
```gradle
 apply plugin: 'com.novoda.bintray-release'
```

```gradle

//    lintOptions {
//        abortOnError false
//    }
//    // javadoc编译失败，可以考虑去除javadoc
//    tasks.withType(Javadoc).all {
//        enabled = false
//    }

    if (!isBuildLibrary) {
        //生成源文件
        task sourcesJar(type: Jar) {
            from android.sourceSets.main.java.srcDirs
            classifier = 'sources'
        }
        //生成Javadoc文档
        task javadoc(type: Javadoc) {
            source = android.sourceSets.main.java.srcDirs
            classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
        }
        //文档打包成jar
        task javadocJar(type: Jar, dependsOn: javadoc) {
            classifier = 'javadoc'
            from javadoc.destinationDir
        }
        //拷贝javadoc文件
        task copyDoc(type: Copy) {
            from "${buildDir}/docs/"
            into "docs"
        }
        //上传到JCenter所需要的源码文件
        artifacts {
            archives javadocJar
            archives sourcesJar
        }
        //解决 JavaDoc 中文注释生成失败的问题
        tasks.withType(Javadoc) {
            options.addStringOption('Xdoclint:none', '-quiet')
            options.addStringOption('encoding', 'UTF-8')
            options.addStringOption('charSet', 'UTF-8')
        }
        //发布到 Bintray
        publish {
            userOrg = 'f1ght1n9' //organization id 企业名称或ID
            groupId = 'com.readboy.baselibrary' //以后访问 jcenter上此项目的路径，一般和库项目的包名一致
            artifactId = 'network' //bintray.com 创建的 Package 名
            publishVersion = '1.0.0' //版本号
            desc = '任何关于网络服务、组件、工具的库' //版本说明，随意
            website = 'https://github.com/L1uj1awe1/BaseLibrary.git' //关于这个开源项目的网站，随意
        }
    }
```

3、执行命令

```gradle
./gradlew clean build generatePomFileForReleasePublication bintrayUpload -PbintrayUser=USER_NAME -PbintrayKey=API_KEY -PdryRun=false
```

USER_NAME: Bintray的用户名
API_KEY: Bintray账号的API KEY

如果成功，如果命令执行成功，会看到 BUILD SUCCESSFUL；否则根据错误提示解决错误，再重新执行命令。

4、登录 Bintray，进入创建的Package 获取 Maven Uri 和 compile url，配置到需要引入library的Project -> root
```gradle
allprojects {
    repositories {
        maven { url 'https://f1ght1n9.bintray.com/maven' }
    }
}
```

在app 或者 Module 中引入
```gradle
    implementation 'com.readboy.baselibrary:network:1.0.0'
```

5、[BaseLibrary Bintray 地址](https://bintray.com/f1ght1n9)
6、[参考博客](https://drprincess.github.io/2018/02/01/Android-%E5%8F%91%E5%B8%83%E9%A1%B9%E7%9B%AE%E5%88%B0%E5%88%B0%20JCenter%20%E4%BB%93%E5%BA%93/)