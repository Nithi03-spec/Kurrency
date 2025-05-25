/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.failure

import io.github.nithi.kurrency.data.failure.Failure.CancellationFailure
import io.github.nithi.kurrency.data.failure.Failure.SerializationFailure
import io.github.nithi.kurrency.data.failure.Failure.ServerFailure
import io.github.nithi.kurrency.data.failure.Failure.UnhandledFailure
import okhttp3.OkHttpClient
import java.io.IOException

/**
 * Failure class to hold any kind of errors from server. [ServerFailure] represents error response
 * e.g. parameters are incorrect, no result. [SerializationFailure] represents exceptional cases
 * e.g. parse exception, serialization exception. [CancellationFailure] represents cancellation
 * exception from Coroutines. [UnhandledFailure] represents unknown errors.
 *
 * @param message the error message
 *
 * @throws IOException as [OkHttpClient] is only able to consume [IOException]
 * @see [Corresponding exception issue](https://github.com/square/retrofit/issues/3110)
 *
 * @author Nuh Koca
 * @since 2020-03-05
 */
sealed class Failure(override val message: String) : IOException(message) {

    /**
     * Represents the error response itself.
     *
     * @param message the error message
     */
    data class ServerFailure(override val message: String) : Failure(message)

    /**
     * Represents other type of errors.
     *
     * @param message the error message
     */
    data class SerializationFailure(override val message: String) : Failure(message = message)

    /**
     * Represents cancellation errors from coroutines.
     *
     * @param message the error message
     */
    data class CancellationFailure(override val message: String) : Failure(message = message)

    /**
     * Represents unhandled errors by error handling mechanism.
     *
     * @param message the error message
     */
    data class UnhandledFailure(override val message: String) : Failure(message = message)
}
