/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package extensions

import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.gradle.api.DomainObjectSet

private const val APP_NAME_PREFIX = "kurrency"
private const val OUTPUT_EXTENSION = ".apk"

/**
 * Creates custom naming for apk outputs.
 */
fun DomainObjectSet<ApplicationVariant>.generateApplicationOutputs() {
    all {
        outputs.all {
            val outputImpl = this as BaseVariantOutputImpl

            val fileName = "$APP_NAME_PREFIX-${buildType.name}$OUTPUT_EXTENSION"

            outputImpl.outputFileName = fileName
        }
    }
}
