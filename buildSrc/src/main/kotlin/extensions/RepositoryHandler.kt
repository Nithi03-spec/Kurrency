/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package extensions

import org.gradle.api.artifacts.dsl.RepositoryHandler

/**
 * Applies default plugins for repository
 */
fun RepositoryHandler.applyDefaults() {
    google()
    mavenCentral()
    jcenter {
        content {
            // Only download the 'kotlinx-html-jvm' module from JCenter, but nothing else.
            // detekt needs 'kotlinx-html-jvm' for the HTML report.
            includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
        }
    }
}
