/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
import Versions
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
//import io.gitlab.arturbosch.detekt.detekt
import utils.javaVersion
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("io.gitlab.arturbosch.detekt")
}



extensions.configure<DetektExtension>("detekt") {
    toolVersion = Versions.detekt
    parallel = false
    input = files(
        "src/main/kotlin",
        "src/main/java"
    )
    config = files("${project.rootDir}/default-detekt-config.yml")

    reports {
        xml {
            enabled = true
            destination = file("${project.buildDir}/reports/detekt/detekt-report.xml")
        }
        html {
            enabled = true
            destination = file("${project.buildDir}/reports/detekt/detekt-report.html")
        }
    }
}

tasks {
    withType<Detekt> {
        include("**/*.kt", "**/*.kts")
        exclude("**/build/**", ".*/resources/.*", ".*test.*,.*/resources/.*,.*/tmp/.*")

        jvmTarget = javaVersion.toString()
    }
}
