plugins {
    @Suppress("UnstableApiUsage")
    `gradle-enterprise`
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

include(":app", ":rules")

rootProject.name = "kurrency"
rootProject.buildFileName = "build.gradle.kts"
