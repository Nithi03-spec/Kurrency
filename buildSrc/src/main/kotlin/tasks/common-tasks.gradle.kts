/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package tasks

import extensions.shouldTreatCompilerWarningsAsErrors
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.javaVersion
import utils.parallelForks

tasks {
    withType<JavaCompile> {
        options.isIncremental = true
        allprojects {
            options.compilerArgs.addAll(
                arrayOf(
                    "-Xlint:-unchecked",
                    "-Xlint:deprecation",
                    "-Xdiags:verbose"
                )
            )
        }
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
            // Updated compiler args - removed deprecated flags
            kotlinOptions.freeCompilerArgs = listOf(
                "-progressive",
                "-Xdisable-default-scripting-plugin",
                // Removed: "-Xuse-experimental=kotlin.Experimental" (deprecated)
                // Removed: "-Xskip-runtime-version-check" (deprecated)
                "-opt-in=kotlin.RequiresOptIn"
            )
            kotlinOptions.allWarningsAsErrors = project.shouldTreatCompilerWarningsAsErrors()
        }
    }

    withType<Test> {
        testLogging {
            // set options for log level LIFECYCLE
            events = setOf(
                TestLogEvent.FAILED,
                TestLogEvent.STARTED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT
            )
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }

        maxParallelForks = parallelForks
    }
}