
plugins {
    `java-library`
    kotlin
}

// Configure JVM target to match the rest of the project
kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }
}

// Ensure Kotlin compilation uses JVM target 17
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
        // Remove deprecated flags
    }
}

dependencies {
    // Use consistent lint API versions - remove duplicates
    compileOnly("com.android.tools.lint:lint-api:31.1.1")
    compileOnly("com.android.tools.lint:lint-checks:31.1.1")

    // Test dependencies - IMPORTANT: Add lint core dependency
    testImplementation("com.android.tools.lint:lint:31.1.1")
    testImplementation("com.android.tools.lint:lint-tests:31.1.1")
    testImplementation("junit:junit:4.13.2")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Lint-Registry-v2"] = "io.github.nithi.rules.IssueRegistry"
    }
}