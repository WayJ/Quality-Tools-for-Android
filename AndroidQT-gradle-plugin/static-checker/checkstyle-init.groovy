import org.gradle.api.Project

ext.CHECKSTYLE = 'checkstyle'
gradle.projectsEvaluated {
    ext.applyCheckStyle = {
        if (project.hasProperty('android')) {
            repositories {
                mavenCentral()
            }
            if (!project.hasProperty(CHECKSTYLE)) {
                addCheckStyleTask project
            }
        }
    }

        println "AndroidQT --> apply plugin: checkstyle"
    if (rootProject.subprojects.isEmpty()) {
        rootProject applyCheckStyle
    } else {
        rootProject.subprojects applyCheckStyle
    }
}
//CheckStyle task
void addCheckStyleTask(final Project project) {
    project.apply plugin:CHECKSTYLE
    project.tasks.create(
            name:CHECKSTYLE,
             type:Checkstyle,
             dependsOn:project.assembleDebug) {
        // final String CONFIG_NAME = 'checkstyle.xml'
        // // Find excludes filter
        // if (rootProject.file(CONFIG_NAME).exists()) {
        //     configFile rootProject.file(CONFIG_NAME)
        // } else {
        //     for (final File dir : startParameter.initScripts) {
        //         if (new File(dir.parentFile, CONFIG_NAME).exists()) {
        //             configFile new File(dir.parentFile, CONFIG_NAME)
        //             break
        //         }
        //     }
        // }
        configFile file("$rootDir/.gradle/android-qt/checkstyle.xml")
        configProperties.checkstyleSuppressionsPath = file("$rootDir/.gradle/android-qt/suppressions.xml").absolutePath

        source = ['main', 'androidTest', 'test'].collect {
            project.android.sourceSets.findByName(it)
        }.find { null != it }.collect { it.java.srcDirs }
        include '**/*.java'
        exclude '**/gen/**'
        classpath = project.configurations.compile +
                    files(project.android.bootClasspath)
        ignoreFailures = true // Don't report error if there are bugs found.
    }
    // Flavourless projects need assembleDebugAndroidTest
    // Flavoured projects need assembleAndroidTest
    final String[] DEVICE_TEST_TASKS =
        ['assembleDebugAndroidTest', 'assembleAndroidTest']
    final String[] HOST_TEST_TASKS =
        ['assembleDebugUnitTest', 'assembleUnitTest']
    for (final String task : DEVICE_TEST_TASKS) {
        if (project.hasProperty(task)) {
            project.tasks.checkstyle.dependsOn task
            break
        }
    }
    for (final String task : HOST_TEST_TASKS) {
        if (project.hasProperty(task)) {
            project.tasks.checkstyle.dependsOn task
            break
        }
    }
    project.check.dependsOn project.tasks.checkstyle
}
