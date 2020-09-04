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
./gradlew sonarqube
```

配置SonarQube

配置方式是在 rootProject 的 gradle.properties 文件中加入配置参数。

```
# gradle.properties
#------------------- Android QT 工具配置
#----- 代码校验规则配置文件的下载地址，用于提供自定规则，必须是zip，文件目录和文件名要参考我提供的
qt.configZipFileUrl = https://github.com/WayJ/Quality-Tools-for-Android/releases/download/v0.1-alpha/config-default-v0.1.zip
#------------------- 以下为Sonar配置，更多可以查看sonar插件官方文档
#----- 配置SonarQube的Url地址，执行sonarqube时需要用到。***** 这个如果要用到sonar，就会是必须要配置的
systemProp.sonar.host.url=http://localhost:9000
#----- Sonar的项目key 标识
systemProp.sonar.projectKey = AndroidQTSampleKey
#----- 显示在Sonar上的Project名
systemProp.sonar.projectName = AndroidQTSampleDemo
```

更多sonar的配置可以看Sonar官方文档： [sonarscanner-for-gradle](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/) , [Sonar-analysis-parameters](https://docs.sonarqube.org/latest/analysis/analysis-parameters/)