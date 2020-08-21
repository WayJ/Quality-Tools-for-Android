import org.gradle.api.Project
// import org.gradle.api.file.FileCollection
// import org.gradle.language.base.compile.CompilerVersion

ext.FINDBUGS = 'findbugs'

gradle.projectsEvaluated {
    ext.applyFindBugs = {
        if (project.hasProperty('android')) {
            repositories {
                mavenCentral()
            }
            if (!project.hasProperty(FINDBUGS)) {
                addFindBugsTask project
            }
        }
    }
    println "AndroidQT --> apply plugin: findbugs"
    if (rootProject.subprojects.isEmpty()) {
        rootProject applyFindBugs
    } else {
        rootProject.subprojects applyFindBugs
    }
}

//Findbugs task
void addFindBugsTask(final Project project) {
    project.apply plugin:FINDBUGS
    project.tasks.create([name:FINDBUGS,type:FindBugs,dependsOn:[project.assembleRelease],]) {
        // Find excludes filter


        // final String CONFIG_NAME = 'findbugs-filter.xml'
        // if (rootProject.file(CONFIG_NAME).exists()) {
        //     excludeFilter rootProject.file(CONFIG_NAME)
        // } else {
        //     for (final File dir : startParameter.initScripts) {
        //         println dir.parentFile
        //          if (new File(dir.parentFile, CONFIG_NAME).exists()) {
        //             excludeFilter new File(dir.parentFile, CONFIG_NAME)
        //             break
        //         }
        //     }
        // }

        excludeFilter new File("$rootDir/.gradle/android-qt/findbugs-filter.xml")


        classes = getDebugSources(project)
        source = ['main', 'androidTest', 'test'].collect {
            project.android.sourceSets.findByName(it)
        }.find { null != it }.collect { it.java.srcDirs }
        classpath = project.configurations.compile + files(project.android.bootClasspath)
        effort = 'max'
        reportLevel = 'high'
        reports {
            html.enabled = true
            // 模板 https://github.com/findbugsproject/findbugs/tree/master/findbugs/src/xsl
            // html.stylesheet resources.text.fromFile("$rootDir/.gradle/android-qt/color.xsl")
            
            xml.enabled = false
            // xml {
            //     //enabled = false
            //     withMessages = true
            // }
        }
        ignoreFailures = true // Don't report error if there are bugs found.
    }
    // Flavourless projects need assembleDebugAndroidTest
    // Flavoured projects need assembleAndroidTest
    for (final String taskProperty : ['assembleDebugAndroidTest',
                                      'assembleAndroidTest',
                                      'assembleDebugUnitTest',
                                      'assembleUnitTest',]) {
        if (project.hasProperty(taskProperty)) {
            project.tasks.findbugs.dependsOn += [taskProperty]
        }
    }
    project.check.dependsOn += [project.tasks.findbugs]
    println "AndroidQT --> degug 3"+project.check.dependsOn
}

FileCollection getDebugSources(final Project project) {
    FileCollection classes = project.files()
    for (final String variantType : ['applicationVariants', 'libraryVariants', 'testVariants']) {
        if (project.android.hasProperty(variantType)) {
            project.android."${variantType}".all { variant ->
                if (variant.buildType.name == 'debug') {
                    if(CompareGradleVersion('4.10.1')){
                        classes += files("${variant.javaCompileProvider.get().destinationDir}")
                    }else{
                        classes += files("${variant.javaCompile.destinationDir}")
                    }
                    
                }
            }
        }
    }
    classes
}

