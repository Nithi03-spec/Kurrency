/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
import com.android.build.gradle.internal.dsl.SigningConfig
import extensions.getProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import java.io.File

/**
 * An object that includes signing configs
 */
private object InternalConfigType {
    const val RELEASE = "release"
}

/**
 * The common interface to create any signing config
 */
@FunctionalInterface
private interface SigningConfigCreator {

    /**
     * The val which includes name of the signing config from [InternalConfigType]
     */
    val name: String

    /**
     * Creates the requested signing config
     *
     * @param namedDomainObjectContainer The container to create the corresponding signing config
     * @param project The project
     *
     * @return The [SigningConfig]
     */
    fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<SigningConfig>,
        project: Project
    ): SigningConfig
}

/**
 * A [SigningConfigCreator] implementation to create release [SigningConfig]
 */
internal object ReleaseConfig : SigningConfigCreator {
    override val name = InternalConfigType.RELEASE

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<SigningConfig>,
        project: Project
    ): SigningConfig {
        return namedDomainObjectContainer.create(name).apply {
            storeFile = File("${project.rootDir}/${project.getProperty("signing.store.file")}")
            storePassword = project.getProperty("signing.store.password")
            keyAlias = project.getProperty("signing.key.alias")
            keyPassword = project.getProperty("signing.key.password")
        }
    }
}
