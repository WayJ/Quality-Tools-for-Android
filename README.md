# Android 质量工具

这是一个用于提高 Android App 的软件质量的工具集。

## 为什么要做这个

期望做一个可能方便使用的工具，设置简单，且可在线更新远程脚本。同时想要能够查看团队内成员的代码质量，结合CI。生成质量分析报告。

所以，本工具的设计思路，是以集成现有的第三方工具为主，自研为辅，尽量以无感知无侵入的方式，为 Android 项目产出一个质量分析报告并提供相应改进策略。

实现的主要目标有：

* 只需要在 gradle 中 添加一行，无需太多配置，必须简单
* 使用 gradle 将质量分析阶段放入 app 构建中，打包同时生成html的质量分析报告
* 使用 sonar，将分析结果传入到 sonar 中
* 使用 CLI（python）或者 idea plugin ，为开发阶段提供主动的质量自动优化
* 使用 git hook 将阻断错误拦截在 commit 之前



本工具还应该配套对应的编码规范、质量管理措施、各环节的质量监控工具，全面的结合起来，才能够达成最终的目标。

或许有空的话，也会把这相关的内容也整理出来。

另外还有其他方面的工具，性能工具、安全工具，也在计划的范围之内。


## 教程

### 引入

1、在 rootProject 的 setting.gradle 中 加入一行

~~~
apply from: 'https://raw.githubusercontent.com/WayJ/Quality-Tools-for-Android/master/AndroidQT-gradle-plugin/android-qt-settings.gradle'
~~~

2、在 rootProject 的 build.gradle中 加入一行 

~~~
apply from: 'https://raw.githubusercontent.com/WayJ/Quality-Tools-for-Android/master/AndroidQT-gradle-plugin/android-qt.gradle'
~~~

### 使用

1、执行sonarqube扫描。（rootProject->gradle tasks->verification->sonarqube）

~~~
./gradlew sonarqube
~~~



## 参考

* [Quanta](https://github.com/g4s8/Quanta)
* [qulice](https://github.com/teamed/qulice)
* [vb-android-app-quality](https://github.com/vincentbrison/vb-android-app-quality)
* [Quality-Tools-for-Android](https://github.com/stephanenicolas/Quality-Tools-for-Android)
* [sonar-android-lint-plugin](https://github.com/peter-budo/sonar-android-lint-plugin)
* [alibaba-p3c](https://github.com/alibaba/p3c/blob/master/idea-plugin/README_cn.md)
* [gradle-init-scripts](https://github.com/ffgiff/gradle-init-scripts)



















