apply from: QT_Path +'static-checker/sonar-init.groovy'
apply from: QT_Path +'static-checker/pmd-init.groovy'
apply from: QT_Path +'static-checker/checkstyle-init.groovy'
// apply from: QT_Path +'static-checker/ktlint-init.groovy'
apply from: QT_Path +'static-checker/jacoco-init.groovy'
apply from: QT_Path +'static-checker/config-init.groovy'

// 不小于该版本
ext.CompareGradleVersion =  { targetVersion -> CompareVersion(gradle.gradleVersion,targetVersion)>=0 ;}

// 读取配置
Properties properties = new Properties();
try {
    properties.load(new FileInputStream(new File(rootDir,"gradle.properties")));
    String setfileUrl = properties.getProperty("qt.configZipFileUrl");
    String defaultFileUrl = 'https://github.com/WayJ/Quality-Tools-for-Android/releases/download/v0.2/config-default.zip';
    ext.configZipFileUrl = (setfileUrl == null) ? defaultFileUrl : setfileUrl;
} catch (IOException e) {
    e.printStackTrace();
}


/*
 * Gradke V5.6 移除了 findbugs，使用spotbugs：https://docs.gradle.org/current/userguide/upgrading_version_5.html#changes_6.0
 */
println "AndroidQT --> Running gradle version: $gradle.gradleVersion"
// println "AndroidQT --> Running gradle version: " + CompareGradleVersion('4.6')
boolean useSpotbugs = CompareGradleVersion('5.6');

if(useSpotbugs){
    // apply from: QT_Path +'static-checker/spotbugs-init.groovy'
}else{
    apply from: QT_Path +'static-checker/findbugs-init.groovy'
}



//比对版本方法
Integer CompareVersion(String version1, String version2){
    if (version1.equals(version2)) {
        return 0;
    }
    String[] version1Array = version1.split("\\.");
    String[] version2Array = version2.split("\\.");
    int index = 0;
    //获取最小长度值
    int minLen = Math.min(version1Array.length, version2Array.length);
    int diff = 0;
    
    //循环判断每位的大小
    while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
        index++;
    }
    
    if (diff == 0) {
        //如果位数不一致，比较多余位数
        for (int i = index; i < version1Array.length; i++) {
            if (Integer.parseInt(version1Array[i]) > 0) {
                return 1;
            }
        }

        for (int i = index; i < version2Array.length; i++) {
            if (Integer.parseInt(version2Array[i]) > 0) {
                return -1;
            }
        }
        return 0;
    } else {
        return diff > 0 ? 1 : -1;
    }
}