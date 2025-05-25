package io.github.nithi.rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue

import io.github.nithi.rules.Stubs.CUSTOM_LOG_IMPL_KOTLIN
import io.github.nithi.rules.Stubs.TIMBER_LOG_IMPL_JAVA
import org.junit.Test

/**
 * A test class for [TimberLogDetector]
 */
@Suppress("UnstableApiUsage")
class TimberLogDetectorTest : LintDetectorTest() {

    @Test
    fun `test should detect usage of Timber`() {
        val stubFile = kotlin(
            """
            package io.github.nithi.kurrency

            import timber.log.Timber

            class Dog {

                fun bark() {
                    Timber.d("woof! woof!")
                }
            }
        """
        ).indented()

        val lintResult = lint()
            .files(TIMBER_LOG_IMPL_JAVA, stubFile)
            .run()

        lintResult
            .expectWarningCount(1)
            .expect(
                """
             src/io/github/nithi/kurrency/Dog.kt:8: Warning: Directly calling timber.log.Timber usage is not recommended. [TimberLogDetector]
                     Timber.d("woof! woof!")
                     ~~~~~~~~~~~~~~~~~~~~~~~
             0 errors, 1 warnings
         """.trimIndent()
            )
    }

    @Test
    fun `test should not detect if log import is different`() {
        val fileToEvaluate = kotlin(
            """
             package io.github.nithi.kurrency

             import io.github.nithi.kurrency.util.ext.d

             class Dog {
                 fun bark() {
                     d { "woof! woof!" }
                 }
             }
         """
        ).indented()

        val lintResult = lint()
            .files(CUSTOM_LOG_IMPL_KOTLIN, fileToEvaluate)
            .run()

        lintResult
            .expectClean()
    }

    override fun getDetector(): Detector = TimberLogDetector()

   override fun getIssues(): MutableList<Issue> = mutableListOf(TimberLogDetector.ISSUE)
}
