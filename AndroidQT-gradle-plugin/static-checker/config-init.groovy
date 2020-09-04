
gradle.projectsEvaluated { g ->
    g.rootProject {
        apply plugin: 'de.undercouch.download'

        task qtConfigClearConfig(type: Delete){
            delete "$rootDir/.gradle/android-qt"
            followSymlinks = true
        }

        task qtConfigDownloadZip(type: Download,dependsOn: qtConfigClearConfig) {
            src configZipFileUrl
            dest new File("$rootDir/.gradle/android-qt", 'config-default.zip')

            doLast {
                copy {
                    from zipTree(dest)
                    into "$rootDir/.gradle/android-qt"
                }
            }
        }

        // task qtConfigUnzipFile(dependsOn: qtConfigDownloadZip, type: Copy) {
        //     from zipTree(qtConfigDownloadZip.dest)
        //     into "$rootDir/.gradle/android-qt"
        // }

        task qtInit(dependsOn: qtConfigDownloadZip){
            doLast{
                println "AndroidQT --> 下载配置文件：success"
            }
        }
    
    }
}
