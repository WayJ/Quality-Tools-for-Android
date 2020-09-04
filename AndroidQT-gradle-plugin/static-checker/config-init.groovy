
gradle.projectsEvaluated { g ->
    g.rootProject {
        apply plugin: 'de.undercouch.download'
        // rootProject addDownloadConfigTask

        // task downloadFile << {
        //     download {
        //         src 'https://github.com/WayJ/Quality-Tools-for-Android/releases/download/v0.1-alpha/config-default-0.1.zip'
        //         dest buildDir
        //     }
        // }

        task qtConfigClearConfig(type: Delete){
            delete "$rootDir/.gradle/android-qt"
            followSymlinks = true
        }

        task qtConfigDownloadZip(type: Download,dependsOn: qtConfigClearConfig) {
            src 'https://github.com/WayJ/Quality-Tools-for-Android/releases/download/v0.1-alpha/config-default-v0.1.zip'
            dest new File("$rootDir/.gradle/android-qt", 'config-default.zip')
        }

        task qtConfigUnzipFile(dependsOn: qtConfigDownloadZip, type: Copy) {
            from zipTree(qtConfigDownloadZip.dest)
            into "$rootDir/.gradle/android-qt"
        }

        task qtConfigGet(dependsOn: qtConfigUnzipFile){

        }

        
        
        println "AndroidQT --> apply plugin: download"
    }
}