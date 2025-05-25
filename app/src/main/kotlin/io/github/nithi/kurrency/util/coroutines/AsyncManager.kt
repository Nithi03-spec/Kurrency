/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.coroutines

import io.github.nithi.kurrency.data.Result
import kotlinx.coroutines.flow.Flow

/**
 * A main interface to manage asynchronous calls and catch error accordingly. [DefaultAsyncManager]
 * implements this interface and this interface shouldn't be implemented directly by any repository.
 * Instead, [DefaultAsyncManager] reference should be specified according to the class delegation.
 */
@FunctionalInterface
interface AsyncManager {

    /**
     * Handles any asynchronous call and waits for its result. This wrapper also catches errors and
     * delivers to upper layer.
     *
     * @param body The suspend body to be called
     *
     * @return [T] within [Flow] builder.
     */
    fun <T> handleAsyncWithTryCatch(body: suspend () -> T): Flow<Result<T>>
}
