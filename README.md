# Android 质量工具

这是一个工具，用于提高Android App 的代码质量。

这个项目的目的是完成这个工具。

**为什么要做这个**

Android目前有很多的质量工具，checkstyle、findbugs、android lint等。但是目前没有找到一个方便即用的像瑞士军刀一样的工具。同时，由于目前我部门负责数十个App，作为负责人，期望能够获得一个查看团队内成员的代码质量、能够结合CI、生成有效的APP质量报告的工具。而不仅仅是idea plugin。

所以，本工具的设计思路，是以集成现有的第三方工具为主，自研为辅，尽量以无感知无侵入的方式，为Android项目产出一个质量分析报告并提供相应改进策略。

实现的主要目标有：

* 只需要在gradle中import一个工具/文件，无需太多配置
* 使用gradle将质量分析阶段放入app构建中，打包同时生成html的质量分析报告
* 使用sonar，将分析结果传入到sonar中
* 使用CLI（python）或者idea plugin，为开发阶段提供主动的质量自动优化
* 使用git hook将阻断错误拦截在commit之前

## Todo

[] 





## 参考

* [Quanta](https://github.com/g4s8/Quanta)
* [qulice](https://github.com/teamed/qulice)
* [vb-android-app-quality](https://github.com/vincentbrison/vb-android-app-quality)
* [Quality-Tools-for-Android](https://github.com/stephanenicolas/Quality-Tools-for-Android)
* [sonar-android-lint-plugin](https://github.com/peter-budo/sonar-android-lint-plugin)
* [alibaba-p3c](https://github.com/alibaba/p3c/blob/master/idea-plugin/README_cn.md)



















