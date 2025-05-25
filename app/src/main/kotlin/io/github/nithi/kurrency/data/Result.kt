/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data

import io.github.nithi.kurrency.data.failure.Failure

/**
 * A generic class that holds a value with its data status.
 * @param <T>
 */
sealed class Result<out R> {

    /**
     * Represents success data operation.
     *
     * @param data the data
     *
     * @return the data with [Result] wrapper.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents error operation.
     *
     * @param failure the [Failure]
     *
     * @return [Nothing] with [Result] wrapper.
     */
    data class Error(val failure: Failure) : Result<Nothing>() {
        override fun toString() = "Failure $failure"
    }
}

/**
 * `true` if [Result] is of type [Result.Success] & holds non-null [Result.Success.data].
 */
internal val Result<*>.succeeded
    get() = this is Result.Success && data != null

/**
 * Returns the result of [onSuccess] for encapsulated value if this instance represents success
 * or the result of [onFailure] function for encapsulated exception if it is failure.
 */
fun <R, T> Result<T>.fold(onSuccess: (T) -> R, onFailure: (Failure) -> R): R {
    return if (succeeded) {
        onSuccess((this as Result.Success).data)
    } else {
        onFailure((this as Result.Error).failure)
    }
}

/**
 * Returns the suspend result of [onSuccess] for encapsulated value if this instance represents
 * success or the suspend result of [onFailure] function for encapsulated failure if it is failure.
 */
suspend fun <R, T> Result<T>.foldSuspend(
    onSuccess: suspend (T) -> R,
    onFailure: suspend (Failure) -> R
): R {
    return if (succeeded) {
        onSuccess((this as Result.Success).data)
    } else {
        onFailure((this as Result.Error).failure)
    }
}
