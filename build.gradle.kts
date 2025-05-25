import extensions.applyDefaults
import plugins.BuildPlugins
import tasks.BuildTasks

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins.apply(BuildPlugins.UPDATE_DEPENDENCIES)
plugins.apply(BuildPlugins.GIT_HOOKS)

allprojects {
    repositories.applyDefaults()

  //  plugins.apply(BuildPlugins.DETEKT)
    plugins.apply(BuildPlugins.KTLINT)
    plugins.apply(BuildPlugins.SPOTLESS)
    plugins.apply(BuildTasks.COMMON_TASKS)

    apply {
        from("$rootDir/versions.gradle.kts")
    }
}

tasks.registering(Delete::class) {
    delete(rootProject.buildDir)
}
