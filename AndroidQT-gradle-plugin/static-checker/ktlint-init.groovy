import org.gradle.api.Project

// https://github.com/pinterest/ktlint

gradle.projectsEvaluated {
    ext.applyKtlint = {
        configurations {
            ktlint
        }
        repositories {
            jcenter()
        }
        dependencies {
            ktlint "com.pinterest:ktlint:0.38.0"
        }

        if (project.hasProperty('android')) {
            addKtlintTask(project)
            project.check.dependsOn project.tasks.ktlint
        }
    }
    if (rootProject.subprojects.isEmpty()) {
        rootProject applyKtlint
    } else {
        rootProject.subprojects applyKtlint
    }
}

void addKtlintTask(final Project project) {
    ext.source = ['main', 'androidTest', 'test'].collect {
        project.android.sourceSets.findByName(it)
    }.find { null != it }.collect { it.java.srcDirs }
        
    project.tasks.create([name:'ktlint', type:JavaExec, group:'verification']) {
        args '--android'
        // classpath files(buildscript.scriptClassPath.asFiles)
        classpath = project.configurations.ktlint
        description 'Check Kotlin code style.'
        ignoreExitValue true
        // main 'com.github.shyiko.ktlint.Main'
        main = "com.pinterest.ktlint.Main"
        // args "src/**/*.kt"
        source.each { args "${it.absolutePath}/**/*.kt" }
    }
}

