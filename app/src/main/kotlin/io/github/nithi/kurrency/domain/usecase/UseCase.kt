/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.domain.usecase

import io.github.nithi.kurrency.data.Result
import kotlinx.coroutines.flow.Flow

/**
 * An intermediate interface between repository and UI layers.
 */
interface UseCase {

    /**
     * An intermediate interface to execute Coroutines calls.
     *
     * @param P The [Params]
     * @param T The data class
     */
    @FunctionalInterface
    interface FlowUseCase<in P, out T> : UseCase where P : Params {

        /**
         * Executes the call with the given parameters.
         *
         * @param params The [Params]
         *
         * @return result within [Flow] builder
         */
        fun execute(params: P): Flow<Result<T>>
    }
}

/**
 * An abstract class to create parameters in order to hit the service. Any kind of param class
 * should be derived from this.
 */
abstract class Params
