package io.github.nithi.rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.java
import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin

/**
 * A helper class that contains necessary stubs to test lints.
 */
object Stubs {

    /**
     * [TestFile] containing Timber.
     *
     * This is a hacky workaround for the Timber not being included on the Lint test harness
     * classpath. Ideally, we'd specify ANDROID_HOME as an environment variable.
     */
    val TIMBER_LOG_IMPL_JAVA: TestFile = java(
        """
                package timber.log;

                public final class Timber {
                    public static void d(@NonNls String message, Object... args) {
                        // Stub!
                    }
                }
            """
    ).indented()

    val CUSTOM_LOG_IMPL_KOTLIN: TestFile = kotlin(
        """
                package io.github.nuhkoca.libbra.util.ext

                inline fun d(crossinline message: () -> String) = log { Timber.d(message()) }
        """.trimIndent()
    )
}
