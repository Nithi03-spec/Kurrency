
plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object PluginVersions {
    const val gradle_plugin = "8.1.1"
    const val kotlin_gradle_plugin = "1.9.23"
    const val gradle_version_plugin = "0.42.0"
    const val detekt = "1.23.0"
    const val ktlint = "11.4.2"
    const val spotless = "6.20.0"
    const val junit5 = "1.7.1.1"
}

// Set JVM target for buildSrc to match project
kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersions.gradle_plugin}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin_gradle_plugin}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${PluginVersions.kotlin_gradle_plugin}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersions.gradle_version_plugin}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginVersions.detekt}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginVersions.ktlint}")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginVersions.spotless}")
    implementation("de.mannodermaus.gradle.plugins:android-junit5:${PluginVersions.junit5}")
}
