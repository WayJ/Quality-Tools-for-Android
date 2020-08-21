import org.gradle.api.Project
import org.gradle.api.Task
// import org.gradle.api.file.ConfigurableFileCollection
//参考 https://www.icode9.com/content-4-552992.html
gradle.projectsEvaluated { g ->
    g.rootProject {
        apply plugin:'com.github.spotbugs'
        
        println "AndroidQT --> apply plugin: spotbugs"

        spotbugs {
            toolVersion = '4.1.2'
        }

        ext.applySonar = {
            if (project.hasProperty('android')) {
                setSpotbugsProperties(project)
                // rootProject.tasks.sonarqube.dependsOn project.tasks.assemble
            }
        }
        if (subprojects.isEmpty()) {
            rootProject applySonar
        } else {
            subprojects applySonar
        }
    }
}

void setSpotbugsProperties(final Project project) {
    project.dependencies {
        spotbugsPlugins 'com.h3xstream.findsecbugs:findsecbugs-plugin:1.10.1'
    }
}