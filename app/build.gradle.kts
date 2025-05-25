@file:Suppress("UNCHECKED_CAST")

import common.addJUnit5TestDependencies
import common.addOkHttpBom
import common.addTestDependencies
import dependencies.Dependencies
import extensions.configureAndroidTests
import extensions.generateApplicationOutputs
import extensions.getSemanticAppVersionName

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.kotlinSerialization)
    id(Plugins.junit5)
}

val baseUrl: String by project

android {
    namespace = "io.github.nithi.kurrency"

    // Updated to use compileSdk instead of compileSdkVersion (deprecated)
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.nithi.kurrency"
        // Updated to use minSdk instead of minSdkVersion (deprecated)
        minSdk = extra["minSdkVersion"] as Int
        // Updated to use targetSdk instead of targetSdkVersion (deprecated)
        targetSdk = 34
        versionCode = 1
        versionName = getSemanticAppVersionName()

        vectorDrawables.useSupportLibrary = true
        testApplicationId = "io.github.nithi.kurrency.test"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument(Config.JUNIT5_KEY, Config.JUNIT5_VALUE)
        testInstrumentationRunnerArgument(Config.ORCHESTRATOR_KEY, Config.ORCHESTRATOR_VALUE)

        configureAndroidTests()

        signingConfig = signingConfigs.getByName("debug")

        // All supported languages should be added here. It tells all libraries that we only want to
        // compile these languages into our project -> Reduces .APK size
        resourceConfigurations.addAll(listOf("en"))
    }

    // Updated packaging options syntax
    packaging {
        resources {
            excludes += "META-INF/LICENSE*"
        }
    }

    signingConfigs {
        // createReleaseConfig(this)
    }

    buildTypes {
        // createRelease(this)
        // createDebug(this)
        generateApplicationOutputs(applicationVariants)

        forEach { type ->
            if (type.name == "release") {
                // type.signingConfig = signingConfigs.getByName(type.name)
            }

            type.buildConfigField("String", "BASE_URL", baseUrl)
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    // Use JVM Toolchain for consistent JVM version across all tasks
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

    // Updated from lintOptions to lint
    lint {
        // Apply lint defaults manually since setDefaults() expects LintOptions
        abortOnError = false
        ignoreWarnings = true
        checkReleaseBuilds = false
        // Add other lint configurations as needed
    }

    testOptions {
        // Apply test defaults manually since applyDefault() expects old TestOptions type
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
        animationsDisabled = true
        // Add other test configurations as needed
    }
}

// Configure KAPT to use JVM target 17
kapt {
    javacOptions {
        option("-source", "17")
        option("-target", "17")
    }
}

// Ensure all Kotlin compilation tasks use JVM target 17
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    lintPublish(project(Modules.lintRules))

    implementation(Dependencies.Core.kotlin)
    implementation(Dependencies.Core.coroutines)
    implementation(Dependencies.Core.serialization_json)

    implementation(Dependencies.UI.material)
    implementation(Dependencies.UI.core_ktx)
    implementation(Dependencies.UI.appcompat)
    implementation(Dependencies.UI.fragment_ktx)
    implementation(Dependencies.UI.activity_ktx)
    implementation(Dependencies.UI.recylerview)
    implementation(Dependencies.UI.constraint_layout)
    implementation(Dependencies.UI.swipe_refresh_layout)

    implementation(Dependencies.Navigation.nav_fragment_ktx)
    implementation(Dependencies.Navigation.nav_ui_ktx)

    // implementation(Dependencies.Lifecycle.lifecycle_extensions)
    implementation(Dependencies.Lifecycle.viewmodel_ktx)
    implementation(Dependencies.Lifecycle.livedata_ktx)
    implementation(Dependencies.Lifecycle.runtime_ktx)
    implementation(Dependencies.Lifecycle.common_java)
    kapt(Dependencies.Lifecycle.compiler)

    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofit_serialization_adapter)

    addOkHttpBom()

    implementation(Dependencies.Other.lottie)
    implementation(Dependencies.Other.timber)
    implementation(Dependencies.Other.coil)

    addTestDependencies()
    addJUnit5TestDependencies()
}
