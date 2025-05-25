/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.coroutines

import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.failure.Failure
import io.github.nithi.kurrency.data.failure.Failure.UnhandledFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

/**
 * The default implementation of [AsyncManager]. This class is a generic handler and will cover any
 * asynchronous calls accordingly. This class should be provided by the class delegation in Kotlin.
 *
 * @param dispatcher The [DispatcherProvider] to run calls under a specific context
 */
internal class DefaultAsyncManager(private val dispatcher: DispatcherProvider) : AsyncManager {

    /**
     * Handles any asynchronous cal and waits for its result. This wrapper also catches errors and
     * delivers to upper layer.
     *
     * @param body a suspend body
     *
     * @return [T] with [Result] wrapper.
     */
    @ExperimentalCoroutinesApi
    override fun <T> handleAsyncWithTryCatch(body: suspend () -> T): Flow<Result<T>> {
        return channelFlow<Result<T>> {
            while (!isClosedForSend) {
                delay(DELAY_IN_MS)
                send(Result.Success(body()))
            }
        }.catch { e ->
            when (e) {
                is Failure -> emit(Result.Error(e))
                else -> emit(Result.Error(UnhandledFailure(e.message.toString())))
            }
        }.flowOn(dispatcher.io)
    }

    private companion object {
        private const val DELAY_IN_MS = 1000L
    }
}
