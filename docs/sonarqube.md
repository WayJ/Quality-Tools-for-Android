# SonarQube 使用教程

## 安装

安装方法网上太多了，不多讲。

## 插件

### 离线插件的使用

放到sonarqube目录下的extensions\plugins目录下面，sonar服务重启即可。

**Android Lint 插件**

Sonar版本: **6.7-7.2.1**
https://www.sonarplugins.com/android

Sonar 6.7.7+
https://github.com/jvilya/sonar-android-plugin

**Alibaba p3c 编码规范插件**

实测 soanr 7.4 可用。
https://github.com/purgeteam/sonarqube-start/blob/master/docker-start/plugin/rhinoceros/sonar-pmd-plugin-3.2.0-SNAPSHOT.jar





## 客户端

在Android Studio里使用很简单，引入了AndroidQT后，执行以下命令。

```
gradle sonarqube
```

设置sonar地址

```
sonarqube{

}
```

