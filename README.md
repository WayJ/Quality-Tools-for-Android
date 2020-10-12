# Android 质量工具

这是一个用于提高 Android App 的软件质量的工具集。

## Introduction

​		期望做一个可能方便使用的工具，设置简单，且可在线更新远程脚本。同时想要能够查看团队内成员的代码质量，结合CI。生成质量分析报告。
​		所以，本工具的设计思路，是以集成现有的第三方工具为主，自研为辅，尽量以无感知无侵入的方式，为 Android 项目产出一个质量分析报告并提供相应改进策略。
​		实现的主要目标有：

* 只需要在 gradle 中 添加一行，无需太多配置，必须简单
* 使用 gradle 将质量分析阶段放入 app 构建中，打包同时生成html的质量分析报告
* 使用 sonar，将分析结果传入到 sonar 中
* 使用 CLI（python）或者 idea plugin ，为开发阶段提供主动的质量自动优化
* 使用 git hook 将阻断错误拦截在 commit 之前

​		本工具还应该配套对应的编码规范、质量管理措施、各环节的质量监控工具，全面的结合起来，才能够达成最终的目标。
​		或许有空的话，也会把这相关的内容也整理出来。
​		另外还有其他方面的工具，性能工具、安全工具，也在计划的范围之内。


## Start

### 使用gradle插件分析代码质量，生成代码质量报告。

#### 导入

1、在 rootProject 的 setting.gradle 中 加入一行

~~~
apply from: 'https://raw.githubusercontent.com/WayJ/Quality-Tools-for-Android/master/AndroidQT-gradle-plugin/android-qt-settings.gradle'
//镜像地址:
//apply from: 'https://gitee.com/wayj59/Quality-Tools-for-Android/raw/master/AndroidQT-gradle-plugin/android-qt-settings.gradle'
~~~

2、在 rootProject 的 build.gradle中 加入一行 

~~~
apply from: 'https://raw.githubusercontent.com/WayJ/Quality-Tools-for-Android/master/AndroidQT-gradle-plugin/android-qt.gradle'
//镜像地址
//apply from:'https://gitee.com/wayj59/Quality-Tools-for-Android/raw/master/AndroidQT-gradle-plugin/android-qt-gitee.gradle'
~~~


>[Gitee 镜像地址](https://gitee.com/wayj59/Quality-Tools-for-Android)：你懂的，国内加载上面2个gradle文件使用镜像能更快


#### 初始化

目前初始化过程做了以下操作：

* 下载代码扫描规则配置：主要是 FindBugs 和 CheckStyle 的配置文件，执行会覆盖之前的配置文件。

~~~
./gradlew qtInit
~~~

下载的配置文件在 rootProject 的 ./gradle/android-qt 文件夹下。

#### 线上扫描：结合Sonar

执行sonarqube扫描，并上传到sonar服务端。（rootProject->gradle tasks->verification->sonarqube）

~~~
./gradlew sonarqube
~~~

SonarQube配置和使用可以 [看这里](docs/sonarqube.md)

#### 线下扫描：导出报告

~~~
./gradlew check
~~~

开发中可以使用 [Alibaba Java Code Guidelines插件](https://github.com/alibaba/p3c/blob/master/idea-plugin/README_cn.md) 来实时检测代码是否符合规范

## 更多

#### FindBugs 自 Gradle v5.6 开始移除

gradle 5.6 开始，去除了自带的 FindBugs 插件。所以如果使用gradle 5.6 及以上，不会导入 FindBugs。目前正在尝试集成FaceBook/Infer 代码扫描和Spotbugs 做为替代。

#### 配置参数

工具提供了以下可选配置项，以下都是 **可选配置**，非必须

配置方式是在 rootProject 的 gradle.properties 文件中加入配置参数。

```
# gradle.properties
#------------------- Android QT 工具配置
#----- 代码校验规则配置文件的下载地址，用于提供自定规则，必须是zip，文件目录和文件名要参考我提供的
qt.configZipFileUrl = https://github.com/WayJ/Quality-Tools-for-Android/releases/download/v0.1-alpha/config-default-v0.1.zip
#----- sonarOnly 设置为ture后，仅依赖sonar，不会加入findbugs等其他工具
qt.sonarOnly = false
#------------------- 以下为Sonar配置，更多可以查看sonar插件官方文档
#----- 配置SonarQube的Url地址，执行sonarqube时需要用到。***** 这个如果要用到sonar，就会是必须要配置的
systemProp.sonar.host.url=http://localhost:9000
#----- Sonar的项目key 标识
systemProp.sonar.projectKey = AndroidQTSampleKey
#----- 显示在Sonar上的Project名
systemProp.sonar.projectName = AndroidQTSampleDemo
```

更多sonar的配置可以看Sonar官方文档： [sonarscanner-for-gradle](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/) ,  [Sonar-analysis-parameters]( https://docs.sonarqube.org/latest/analysis/analysis-parameters/)

#### 生成测试率覆盖报告

需要开启覆盖率统计，开启后会自动导入jacoco。

1、开启：在模块的build.gradle里的buildTypes闭包里添加开启覆盖率统计，如下。

~~~
debug {    
    testCoverageEnabled true
}
~~~

2、使用：使用命令 **jacocoTestReport** 来计算覆盖率

~~~
./gradlew jacocoTestReport
~~~




## 参考


* [stephanenicolas/Quality-Tools-for-Android](https://github.com/stephanenicolas/Quality-Tools-for-Android)
* [sonar-android-lint-plugin](https://github.com/peter-budo/sonar-android-lint-plugin)
* [alibaba-p3c](https://github.com/alibaba/p3c/blob/master/idea-plugin/README_cn.md)
* [ffgiff/gradle-init-scripts](https://github.com/ffgiff/gradle-init-scripts)
* [michel-kraemer/gradle-download-task](https://github.com/michel-kraemer/gradle-download-task)


















