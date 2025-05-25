/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import extensions.gitSha
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

/**
 * An object that includes build types
 */
private object InternalBuildType {
    const val RELEASE = "release"
    const val DEBUG = "debug"
}

/**
 * The common interface to create any build type
 */
@FunctionalInterface
private interface BuildTypeCreator {

    /**
     * The val which includes name of the build type from [InternalBuildType]
     */
    val name: String

    /**
     * Creates the requested build type
     *
     * @param namedDomainObjectContainer The container to create the corresponding build type
     * @param project The project
     *
     * @return The [BuildType]
     */
    fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>,
        project: Project
    ): BuildType
}

/**
 * A [BuildTypeCreator] implementation to create debug [BuildType]
 */
internal object Debug : BuildTypeCreator {
    override val name = InternalBuildType.DEBUG

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>,
        project: Project
    ): BuildType {
        return namedDomainObjectContainer.maybeCreate(name).apply {
            versionNameSuffix = "-dev-${project.gitSha}"
            isDebuggable = true
            isMinifyEnabled = false
        }
    }
}

/**
 * A [BuildTypeCreator] implementation to create release [BuildType]
 */
internal object Release : BuildTypeCreator {
    override val name = InternalBuildType.RELEASE

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>,
        project: Project
    ): BuildType {
        return namedDomainObjectContainer.maybeCreate(name).apply {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt",
                    project.layout.buildDirectory
                ),
                "proguard-rules.pro"
            )
        }
    }
}
