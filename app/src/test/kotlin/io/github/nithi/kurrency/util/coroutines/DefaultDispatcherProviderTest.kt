/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.coroutines

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

/**
 * A parameterized test for [DefaultDispatcherProvider]
 */
@SmallTest
class DefaultDispatcherProviderTest {

    /**
     * A parameterized test function that iterates and performs test on all [CoroutineDispatcher].
     *
     * @param dispatcher represents any [CoroutineDispatcher]
     */
    @ParameterizedTest
    @ArgumentsSource(DispatcherArgumentsProvider::class)
    fun `any dispatcher should not be null`(dispatcher: CoroutineDispatcher) {
        assertThat(dispatcher).isNotNull()
    }
}

/**
 * A custom [ArgumentsProvider] to have any [CoroutineDispatcher] parameterized in tests.
 */
private class DispatcherArgumentsProvider : ArgumentsProvider {

    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()

    /**
     * Provides a stream of arguments to be passed to a [ParameterizedTest] method.
     *
     * @param extensionContext the current extension context
     *
     * @return a stream of arguments
     */
    @Throws(Exception::class)
    override fun provideArguments(extensionContext: ExtensionContext?): Stream<out Arguments?>? {
        return Stream.of(
            dispatcherProvider.main,
            dispatcherProvider.io,
            dispatcherProvider.default,
            dispatcherProvider.unconfined
        ).map { dispatcher -> Arguments.of(dispatcher) }
    }
}
