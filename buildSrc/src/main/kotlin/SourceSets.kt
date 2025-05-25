/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.AndroidSourceSet
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

/**
 * An object that includes source sets
 */
private object InternalSourceSet {
    const val MAIN = "main"
    const val TEST = "test"
    const val ANDROID_TEST = "androidTest"
}

/**
 * The common interface to create any source set
 */
@FunctionalInterface
private interface SourceSetCreator {

    /**
     * The val which includes name of the source set from [InternalSourceSet]
     */
    val name: String

    /**
     * Creates the requested source set
     *
     * @param namedDomainObjectContainer The container to create the corresponding source set
     * @param project The project
     *
     * @return The [AndroidSourceSet]
     */
    fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<AndroidSourceSet>,
        project: Project
    ): AndroidSourceSet
}

/**
 * A [SourceSetCreator] implementation to create main Kotlin [AndroidSourceSet]
 */
internal object Main : SourceSetCreator {
    override val name = InternalSourceSet.MAIN

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<AndroidSourceSet>,
        project: Project
    ): AndroidSourceSet {
        return namedDomainObjectContainer.getByName(name).apply {
            java.srcDir("src/main/kotlin")
        }
    }
}

/**
 * A [SourceSetCreator] implementation to create test Kotlin [AndroidSourceSet]
 */
internal object Test : SourceSetCreator {
    override val name = InternalSourceSet.TEST

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<AndroidSourceSet>,
        project: Project
    ): AndroidSourceSet {
        return namedDomainObjectContainer.getByName(name).apply {
            java.srcDir("src/test/kotlin")
            resources.srcDir("src/test/resources")
            assets.srcDir("src/test/assets")
        }
    }
}

/**
 * A [SourceSetCreator] implementation to create android test Kotlin [AndroidSourceSet]
 */
internal object AndroidTest : SourceSetCreator {
    override val name = InternalSourceSet.ANDROID_TEST

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<AndroidSourceSet>,
        project: Project
    ): AndroidSourceSet {
        return namedDomainObjectContainer.getByName(name).apply {
            java.srcDir("src/androidTest/kotlin")
            resources.srcDir("src/androidTest/resources")
            assets.srcDir("src/androidTest/assets")
        }
    }
}
